package com.shark.common.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.shark.common.utils.Config;
import com.shark.common.utils.DateUtil;
import com.shark.common.utils.FileTool;
import com.shark.common.utils.ResponseUtils;
import com.shark.common.utils.StringUtil;

@Controller
@RequestMapping("/attach/ueditor")
public class UeditorController {
	
    public static final String IMAGEPATH = "ueditor/jsp/upload/image/";

	@ResponseBody
	@RequestMapping(value="/UEController")
	public void UEController(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String actionName = request.getParameter("action");
		if("config".equals(actionName)){
			String config= "/ueditorConfig.json";
			String configContent = readFile(config);  
			ResponseUtils.renderJson(response, configContent);
		}
		else if("uploadimage".equals(actionName)){
           this.uploadImage(request, response);
	
		}else if("listimage".equals(actionName)){
			this.listImage(request, response);
		}
	        
	}
	
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/uploadImage")
    public void uploadImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = new JSONObject();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map fileMap = multipartRequest.getFileMap();     
        for (Object objf : fileMap.entrySet()) {
        	  Map.Entry entry = (Map.Entry) objf;
        	  CommonsMultipartFile file = (CommonsMultipartFile) entry.getValue();
        	  if (StringUtil.isNotEmpty(file.getOriginalFilename())) {
        		  String name = file.getOriginalFilename();
                  String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), name.length());
                  String fileName =UUID.randomUUID() + type;//uuid文件名称
        		  String pathname ="/" + IMAGEPATH + DateUtil.getUserDate("yyyyMMdd") + "/";
        		  Boolean flage=FileTool.uploadFile(pathname, fileName, file.getInputStream());
        		  if(flage){
        			  String url = Config.getParam("attach.host.server.url")+ "/" + IMAGEPATH + DateUtil.getUserDate("yyyyMMdd") + "/"+fileName;
        			  jsonObject.put("url",url);
        			  jsonObject.put("state", "SUCCESS");
        		  }
        	  }
        }
        ResponseUtils.renderJson(response, jsonObject);
    }
    
    private String readFile(String path) throws IOException {
		StringBuilder builder = new StringBuilder();
		try {
			InputStreamReader reader = new InputStreamReader(
					new FileInputStream(Config.class.getResource(path).getPath()), "UTF-8");
			BufferedReader bfReader = new BufferedReader(reader);

			String tmpContent = null;

			while ((tmpContent = bfReader.readLine()) != null) {
				builder.append(tmpContent);
			}

			bfReader.close();
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}

		return filter(builder.toString());
	}
    
    public void listImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String[] str = {"png", "jpg", "jpeg", "gif", "bmp"};
        String attr = Config.getParam("ATTACH_FILE_PATH");
        int start = StringUtil.isNull(request.getParameter("start")) ? 0 : Integer.parseInt(request.getParameter("start"));
        int size = StringUtil.isNull(request.getParameter("size")) ? 20 : Integer.parseInt(request.getParameter("size"));
        int p = StringUtil.isNull(start) ? 0 : start / size + 1;
        File dir = new File(attr + "/" + IMAGEPATH);
        List<File> filesList = new ArrayList<File>();
        filesList = getAllFiles(filesList,dir);
        File[] fileTemp= new File[filesList.size()];
        File[] files = filesList.toArray(fileTemp);
        List<LinkedHashMap<String, Object>> fileList = new ArrayList<LinkedHashMap<String, Object>>();
        LinkedHashMap<String, Object> item = null;
        int allTotal = files.length;//获取总页数
        int startRow = size * p;//最大记录
        int endRow = Math.min(allTotal, startRow);//结束行
        int nStartRow = startRow - size;//重新开始行
        String dateStr ="";
        for (int i = nStartRow; i < endRow; i++) {
            //过滤非图片
            String fileType = files[i].getName().substring(files[i].getName().lastIndexOf('.') + 1, files[i].getName().length());
            for (int t = 0; t < str.length; t++) {
                if (str[t].equals(fileType.toLowerCase())) {
                    item = new LinkedHashMap<String, Object>();
                    dateStr = files[i].getParentFile().getName();
                    String url = request.getContextPath()+"/attath/ueditor/showListImage.do?param=" + dateStr + "$" +files[i].getName();
                    item.put("url", url);
                    item.put("mtime", "1400203383");
                    fileList.add(item);
                }
            }
        }
        
        jsonObject.put("state", "SUCCESS");
        jsonObject.put("list", fileList);
        jsonObject.put("start", size * (p - 1) + 1 + "");
        jsonObject.put("total", allTotal);
        ResponseUtils.renderJson(response, jsonObject);
    }
    
    private String filter(String input) {
		return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");
	}
    
    public  List<File> getAllFiles(List<File> files,File dir) throws Exception{
	       
        if(dir.isDirectory()){//如果是目录
            File[] documentArr = dir.listFiles();//取目录下的所有文件
            if(documentArr!=null){
                //遍历目录下所有文件 执行递归
                for(File document:documentArr){
                	if(document.isDirectory() == false){
                	 files.add(document);
                	}
                	getAllFiles(files,document);
                }
            }
        }
        return files;
    }

    @RequestMapping("/showImage")
    public String showImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String imgUrl = request.getParameter("imgUrl");
        if(!StringUtil.isNull(imgUrl)){
            return "redirect:"+imgUrl;
        }
        String dateStr = request.getParameter("dateStr");
        String name = request.getParameter("name");
        return "redirect:http://images.fotuozi.com/ueditor/jsp/upload/image/"+dateStr+"/"+name;
    }
}
