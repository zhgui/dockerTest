/**
 * Insert the type's description here.
 * <br>
 * Creator: Ngokai <br>
 * Date: 2004-8-16 <br>
 * Time: 11:20:43 <br>
 * @author : Ngokai <br>
 */
package com.shark.common.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;

import com.shark.common.web.validate.AjaxResponse;
import com.shark.pcf.type.SubImagUrlType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public class StringUtils {

    // -------------------------- STATIC METHODS --------------------------

    /**
     * Count a char in the whole String.
     * 
     * @param sToSearch
     * @param cSearchFor
     * @return int 0 means not found.
     */
    public static int countCharInString(String sToSearch, char cSearchFor) {
        if (sToSearch == null)
            return 0;
        int curBegIndex = 0, iLength = 1, iCounter = 0;
        while ((curBegIndex = sToSearch.indexOf(cSearchFor, curBegIndex) + iLength) > 0) {

            iCounter++;
        }
        return iCounter;
    }

    public static String formatNO(String no, int length) {
        if (no == null || no.length() == length)
            return no;
        for (int i = no.length(); i < length; i++) {
            no = "0" + no;
        }
        return no;
    }

    /**
     * @return
     */
    public static String formatNOaddZeroInAfter(String no, int length) {
        if (no == null || no.length() == length)
            return no;
        for (int i = no.length(); i < length; i++) {
            no = no + "0";
        }
        return no;
    }

    /**
     * replace a sub in the whole String.
     * 
     * @return int 0 means not found.
     */
    public static String replace(String str, String pattern, String replace) {
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();

        while ((e = str.indexOf(pattern, s)) >= 0) {
            result.append(str.substring(s, e));
            result.append(replace);
            s = e + pattern.length();
        }
        result.append(str.substring(s));
        return result.toString();
    }

    public static String getStringNoBlank(String str) {
        if (str != null && !"".equals(str)) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            String strNoBlank = m.replaceAll("");
            return strNoBlank;
        } else {
            return str;
        }
    }

    public static String getStringNoSpace(String str) {
        if (str != null && !"".equals(str)) {
            Pattern p = Pattern.compile("\\s|\t|\r|\n");
            Matcher m = p.matcher(str);
            String strNoSpace = m.replaceAll("%");
            return strNoSpace;
        } else {
            return str;
        }
    }

    /**
     * "  " and null return Dft, else trimed string.
     * 
     * @param Dft
     * @return
     */
    public static String strangeFunc(String str, String Dft) {
        String string = str;

        if (string != null)
            string = string.trim();
        string = (null == string || "".equals(string)) ? (Dft) : (string.trim());
        return string;
    }

    /**
     * null return Dft, else trimed string.
     * 
     * @param string
     * @param Dft
     * @return
     */
    public static String trimString(String string, String Dft) {
        string = (null == string) ? (Dft) : (string.trim());
        return string;
    }

    public static String trimString(String p_sOri, String sSameAsNull, String Dft) {
        String sRet = p_sOri;
        if (sRet != null)
            sRet = sRet.trim();
        sRet = (null == sRet || sSameAsNull.equals(sRet)) ? (Dft) : (sRet.trim());
        return sRet;
    }

    public static boolean isBlank(String p_sStringToChk) {
        return (trimString(p_sStringToChk, "").length() == 0);
    }

    /**
     * 构造一定单位的指定的unit字符串.
     * 
     * @param unit
     * @param number
     * @return
     */
    public static String constructStringOfAUnit(String unit, int number) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < number; i++) {
            sb.append(unit);
        }
        return sb.toString();
    }

    /**
     * 查找字符串toSearch中chr第sequence出现处的index
     * 
     * @param toSearch
     * @param chr
     * @param sequence
     *            1 base
     * @return index
     */
    public static int indexOf(String toSearch, char chr, int sequence) {
        int curBeginIdx = 0;
        for (int i = 0; i < sequence; i++) {
            curBeginIdx = toSearch.indexOf(chr, (curBeginIdx == 0) ? 0 : (curBeginIdx + 1));
            if (curBeginIdx < 0)
                break;
        }
        return curBeginIdx;
    }

    // 判断字符串是否为数字
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    // 判断字符串为空
    public static boolean isEmpty(String str) {
        return (str == null) || (str.length() == 0);
    }

    // 判断字符串不为空
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null ? false : str2 == null ? true : str1.equalsIgnoreCase(str2);
    }

    /**
     * 截取String 中的 第一个<image> url
     * 
     * @param str
     * @param start
     *            "<img src=\""
     * @param end
     *            "\" title"
     *            从字串中截取的位数 由于 <img src=\" 是10位, ins=10 则刚好返回不含""的url.
     * @return
     */
    public static String subStringCheck(String str, String start,String flag, String end) {
        String strOne = "";
        String strTwo = "";
        if (str.indexOf(start) > -1) {
            strOne = str.substring(str.indexOf(start));
            strTwo = strOne.substring(strOne.indexOf(flag));
            strTwo = strTwo.substring(0,strTwo.indexOf(end));
        } else {
            strTwo = "";
        }
        return strTwo;
    }
    
    public static String subFirstImgTagSrc(String str) {
    	//String strImagerUrl = Config.getConfigProperty(SubImagUrlType.subImageUrlPrefix, "/ueditor/showImage");
    	String strOne = "";
        String strTwo = "";
    	String stt = "";
    	List<String> sttList = new ArrayList<String>();
        Document doc = Jsoup.parse(str);   
        doc.select("img").removeAttr("height").removeAttr("width").removeAttr("style"); 
        Elements elemS =doc.getElementsByTag("img");
        for(Element elemTmp : elemS){
        	String linkHref = elemTmp.attr("src");
            sttList.add(linkHref);
        }
        if(sttList.size()>0){
        	 strOne = sttList.get(0);
        	 if(StringUtils.isEmpty(strOne)){
        		 stt="";
        	 }else{
        		 //strTwo = strOne.substring(strOne.indexOf(strImagerUrl));
            	 stt = strOne;
        	 }
        }
        return stt;  
    }

    /**
     * 获取图片列表的数量
     * @param str
     * @return
     */
    public static String checkImgTagListSrc(String str) {
    	String stt = "";
    	List<String> sttList = new ArrayList<String>();
        Document doc = Jsoup.parse(str);   
        doc.select("img").removeAttr("height").removeAttr("width").removeAttr("style"); 
        Elements elemS =doc.getElementsByTag("img");
        for(Element elemTmp : elemS){
        	String linkHref = elemTmp.attr("src");
            sttList.add(linkHref);
        }
        if(sttList.size()>0){
        	 stt = ""+sttList.size();
        }
        return stt;  
    }
    
    
    
    public static String subStringCheckSrc(String str, String start,String flag) {
        String strOne = "";
        String strTwo = "";
        if (str.indexOf(start) > -1) {
            strOne = str.substring(str.indexOf(start));
            strTwo = strOne.substring(strOne.indexOf(flag));
        } else {
            strTwo = "";
        }
        return strTwo;
    }
    
    
    /**
     * 过滤Img标签的样式 1.去除原有img标签的样式 2.新增样式:最大宽度 100% 高度:自适应 margin:0 auto; display:block
     * 3.若新闻中含有表格.则分别对表格的table 和 td 增加样式,并且使 table的 width:100% 自适应.
     * @param imgString
     * @return
     */  
    public static String filterImgSrc(String imgString) { 
        Document doc = Jsoup.parse(imgString);   
        doc.select("img").removeAttr("height").removeAttr("width").removeAttr("style");
        doc.getElementsByTag("table").attr(
                "style",
                "border-left:1px solid #000;border-top:1px solid #000;width:100%");
        doc.getElementsByTag("td").attr(
                "style",
                "border-right:1px solid #000;border-bottom:1px solid #000;");
        Elements elemS =doc.getElementsByTag("img");
        for(Element elemTmp : elemS){
            elemTmp.parent().removeAttr("style");
            elemTmp.attr("style", "display:block;margin:0 auto;max-width:100%;height:auto;");
        }
        return doc.html();  
    }

    /**
     * 对html中的视频进行预处理.增加视频中的第一帧图片显示.
     * 1.给视频增加第一帧图像的显示
     * //
     * @param imgString
     * @return
     */
    public static String filterVideoTag(String imgString,ServletRequest req) throws Exception{
        Document doc = Jsoup.parse(imgString);
        try{
            doc
                .select("video")
                .removeAttr("height")
                .removeAttr("width")
                .attr("preload", "none");
            Elements elemT =doc.getElementsByTag("video");
            for(Element elemTmp : elemT){
                elemTmp
                    .attr(
                        "style",
                        "display:block;margin:0 auto;max-width:100%;height:auto;");
                String linkHref = elemTmp.attr("src");
                // 截取视频的第一帧图像,并把图像的URL存入video标签的 poster 属性中.
                linkHref = VideoPictureUtil.videoFirstPicture(linkHref,req);
                elemTmp.attr("poster",linkHref);
                elemTmp.attr("controls", "controls");
                elemTmp.attr("width", "100%");
                elemTmp.attr("height", "auto");
            }
        }catch (Exception e){
            return e.getMessage();
        }

        return doc.html();
    }


    /**
     * 抽取内容中的所有图片的src
     * @param imgString
     * @return
     */
    public static List<String> filterImgSrcList(String imgString) { 
    	List<String> sttList = new ArrayList<String>();
        Document doc = Jsoup.parse(imgString);   
        doc.select("img").removeAttr("height").removeAttr("width").removeAttr("style"); 
        Elements elemS =doc.getElementsByTag("img");
        for(Element elemTmp : elemS){
            elemTmp.parent().removeAttr("style");
            elemTmp.attr("style", "max-width:100%;height:auto;");
            String linkHref = elemTmp.attr("src"); 
            sttList.add(linkHref);
        }
        return sttList;  
    }
    
    /**
     * 从Html代码中提取所有img标签的src内容
     * List 返回
     * @param imgString
     * @return
     */
    public static List<String> getIamgeSrcListForHtml(String imgString) { 
    	List<String> sttList = new ArrayList<String>();
        Document doc = Jsoup.parse(imgString);   
        Elements elemS =doc.getElementsByTag("img");
        for(Element elemTmp : elemS){
            String linkHref = elemTmp.attr("src"); 
            sttList.add(linkHref);
        }
        return sttList;  
    }
    
    /**
     * 从html代码中返回第一个img标签的src内容，
     * 若为空则返回 " "
     * @param imgString
     * @return
     */
    public static String getFirstIamgeSrcForHtml(String imgString) { 
    	if(null == imgString){
    		return imgString;
    	}
    	List<String> sttList = new ArrayList<String>();
        Document doc = Jsoup.parse(imgString);   
        Elements elemS =doc.getElementsByTag("img");
        for(Element elemTmp : elemS){
            String linkHref = elemTmp.attr("src"); 
            sttList.add(linkHref);
        }
        if(sttList.size()>0){
        	 return sttList.get(0); 
        }else{
        	return "";
        }
    }

    /**
     * 获取图片src列表中第一个图片Src (此处的第一个是按照图片上传后 img 标签出现的位置而定的),
     * 并且此处的src 并没有截取掉图片路径中的IP和端口号
     * @param imgString
     * @return
     */
    public static String getFirstImgSrcAndNotRemoveIP(String imgString) {
        List<String> sttList = new ArrayList<String>();
        Document doc = Jsoup.parse(imgString);
        doc.select("img").removeAttr("height").removeAttr("width").removeAttr("style");
        Elements elemS =doc.getElementsByTag("img");
        for(Element elemTmp : elemS){
            elemTmp.parent().removeAttr("style");
            elemTmp.attr("style", "max-width:100%;height:auto;");
            String linkHref = elemTmp.attr("src");
            sttList.add(linkHref);
        }
        if(sttList.size()>0){
            return sttList.get(0);
        }else{
            return "";
        }
    }

    public static List<String> filterImgSrcListAndImgTag(String imgString) { 
    	List<String> sttList = new ArrayList<String>();
        Document doc = Jsoup.parse(imgString);   
        doc.select("img").removeAttr("height").removeAttr("width").removeAttr("style"); 
        Elements elemS =doc.getElementsByTag("img");
        for(Element elemTmp : elemS){
        	String linkHref = elemTmp.attr("src");
            sttList.add(linkHref);
        }
        return sttList;  
    } 
    
    
    /**
     * 去除html代码中的所有html标签,只保留文本内容. 并且若文本内容中含有&nsbp;的替换为" " 
     * @param imgString
     * @return
     */
    public static String removeImg(String imgString) { 
        String safe = Jsoup.clean(imgString, Whitelist.none());
        safe = safe.replaceAll("&nbsp;", " ");
        return safe;  
    } 
    
    /**
     * 去除html代码中的所有html标签,只保留文本内容,并且若文本中含有&nbsp,则保留
     * 
     * @param imgString
     * @return
     */
    public static String removeAllHtmlTagAndKeepNbsp(String imgString) {
        String safe = Jsoup.clean(imgString, Whitelist.none());
        return safe;
    }
    /**
     * 去除字段中所有的html标签,只保留文本,所有的&nbsp;去除
     * @param imgString
     * @return
     */
    public static String removeAllHtmlTag(String imgString) { 
        String safe = Jsoup.clean(imgString, Whitelist.none());
        safe = safe.replaceAll("&nbsp;", "");
        return safe;  
    } 
    
    /**
     * 去除img标签,并在table标签中增加表格边框的显示.
     * 
     * @param imgString
     * @return
     */
    public static String removeImgAndkeepOtherTag(String imgString) { 
    	Document doc = Jsoup.parse(imgString);
    	doc.select("img").remove();
        doc.getElementsByTag("table").attr(
            "style",
            "border-left:1px solid #000;border-top:1px solid #000;width:100%");
        doc.getElementsByTag("td").attr(
            "style",
            "border-right:1px solid #000;border-bottom:1px solid #000;");
    	String safe =  doc.html();
        safe = safe.replaceAll("&nbsp;", " ");
        return safe;  
    }

    /**
     * 过滤{} [] | " 四种特殊字符
     * @param stt
     */
    public static String filterSpecialCharacters(String stt){
//        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
//        Pattern   p   =   Pattern.compile(regEx);
//        Matcher   m   =   p.matcher(str);
//        return   m.replaceAll("").trim();
        stt = stt.replaceAll("\\{\\}","").replaceAll("\\[\\]","").replaceAll("\\|","").replaceAll("\"","").replaceAll("\\r|\\n|\\t", "");
        return stt;
    }

    /**
     * 验证字符串是否含有 {} [] | " 回车 制表符 换行 种字符
     * 若含有  返回 true
     * 若不含有 返回 false
     * \n 回车(\u000a)
     * \t 水平制表符(\u0009)
     * \s 空格(\u0008)
     * \r 换行(\u000d)
     * @param stt
     * @return
     */
    public static boolean checkSpecialCharacters(String stt){
        boolean flag = false;
        if(stt.indexOf("{}")!=-1){
            flag = true;
        }else if(stt.indexOf("[]")!=-1){
            flag = true;
        }else if(stt.indexOf("|")!=-1){
            flag = true;
        }else if(stt.indexOf("\"")!=-1){
            flag = true;
        }else if(stt.indexOf("\t")!=-1){
            flag = true;
        }else if(stt.indexOf("\n")!=-1){
            flag = true;
        }else if(stt.indexOf("\r")!=-1){
            flag = true;
        }
        return flag;
    }
    
    /**
     * 去除所有的img标签,保留其他的标签.
     * 
     * @param imgString
     * @return
     */
    public static String removeImgAndkeepOtherTagAndKeppNbsp(String imgString) {
        Document doc = Jsoup.parse(imgString);
        doc.select("img").remove();
        String safe = doc.html();
        return safe;
    }

    /**
     * 首先对文字进行分段,然后去除html标签
     * 
     * @param imgString
     * @return
     */
    public static String changeBtagAndRemoveOtherTag(String imgString) { 
    	imgString = imgString.replaceAll("</p>", "\\\\n");
    	imgString = imgString.replaceAll("<br>", "\\\\n");
    	imgString = imgString.replaceAll("<p>", "");
    	String safe = Jsoup.clean(imgString, Whitelist.none());
        safe = safe.replaceAll("&nbsp;", "");
        safe = safe.replace("\\n\\n","");
        safe = safe.replace("\\n","\n");
        safe = safe.replace("&amp;","&");
        safe = safe.replace("&quot;","\"");
        safe = safe.replace("&lt;","<");
        safe = safe.replace("&gt;",">");
        return safe;  
    }
    
    /**
     * 过滤html标签,并对过滤过的文本字段进行分段处理
     * 
     * @param imgString
     * @return
     */
    public static List<String> RemoveHtmlTagAndSplitContent(String imgString) {
        imgString = imgString.replaceAll("</p>", "\\\\n");
        imgString = imgString.replaceAll("<br>", "\\\\n");
        imgString = imgString.replaceAll("<p>", "");
        String safe = Jsoup.clean(imgString, Whitelist.none());
        safe = safe.replaceAll("&nbsp;", "");
        safe = safe.replace("\\n\\n", "");
        safe = safe.replace("\\n", "\n");
        safe = safe.replace("&amp;", "&");
        safe = safe.replace("&quot;", "\"");
        safe = safe.replace("&lt;", "<");
        safe = safe.replace("&gt;", ">");
        String[] strArray = safe.split("\n");
        List<String> contentList = new ArrayList<String>();
        if (null != strArray) {
            for (int i = 0; i < strArray.length; i++) {
                String stt = strArray[i].replaceAll(" +","");
                String stt2 = stt.trim();
                String stt3 = stt2.replaceAll(" ","");
                if(!StringUtils.isEmpty(stt3)){
                    contentList.add(stt3);
                }
            }
        }
        return contentList;
    }

    /**
     * 移除html代码中的所有html标签,只保留文本内容,并且若文本内容中含有 &nbsp的移除掉.
     * @param imgString
     * @return
     */
    public static String removeTagAll(String imgString) { 
        String safe = Jsoup.clean(imgString, Whitelist.none());
        safe = safe.replaceAll("&nbsp;", "");
        return safe;  
    } 
    
    public static String removeImgAndWithBasic(String imgString) { 
        String safe = Jsoup.clean(imgString, Whitelist.basic());
        Document doc = Jsoup.parse(safe);
        Elements elemS =doc.getElementsByTag("p");
        for(Element elemTmp : elemS){
            elemTmp.attr("style", "color:green");
        }
        return doc.html();  
    }

    public static AjaxResponse getCommand(Long psychologicalId,String fileName ){

        try {
        //获取调查问卷详情的请求路径
        String questionnaireUrl = Config.getConfigProperty(SubImagUrlType.questionnaireUrl, "http://bigapp.shstzz.com/bigapp/bigapp/server/questionnaire/questionnaires/questionnaireReviewView?psychologicalId=");
        questionnaireUrl = questionnaireUrl+psychologicalId;
        //获取导出问卷的路径
        String exportPdfUrl = Config.getConfigProperty(SubImagUrlType.exportPdfQuestionnaireUrl, "C://Users//User//Desktop//");
        exportPdfUrl = exportPdfUrl+fileName;
        //前半段是我的安装路径，根据自己的安装路径换上即可
        String exportPdfSoftwareUrl = Config.getConfigProperty(SubImagUrlType.exportPdfSoftwareUrl, "C:\\Program Files (x86)\\wkhtmltopdf\\bin\\wkhtmltopdf.exe ");
        //此处的 exportPdfSoftwareUrl questionnaireUrl exportPdfUrl 比较加空字符串拼接.不然报错.
        String command =exportPdfSoftwareUrl+" "+ questionnaireUrl + " " + exportPdfUrl;
            Runtime.getRuntime().exec(command);
            return AjaxResponse.success("导出到桌面,请查阅");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return AjaxResponse.fail("导出失败");
        }

    }




    /**
     * 把时间日期转换为yyyy年MM月dd日 HH:mm:ss 格式的字符串返回.
     * @param ts
     * @return
     */
    public static String timeStampTDate(Timestamp ts){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String date = sdf.format(ts);
        return date;
    }
    
    /**
     * 把时间日期格式转换为yyyy-MM-dd HH:mm:ss 格式的字符串返回
     * @param ts
     * @return
     */
    public static String getDateToString(Date ts){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(ts);
        return date;
    }
    
    public static List<String> getImgSrcAndRemoveTag(String strClobTmp){
    	String strImagerUrl = Config.getConfigProperty(SubImagUrlType.subImageUrlPrefix, "/../appImages/ueditor/jsp/upload/image/");
    	List<String> strList = StringUtils.filterImgSrcListAndImgTag(strClobTmp);
    	List<String> sttList = new ArrayList<String>();
    	for(String stt: strList){
    		String stm ="";
    		stm = stt.substring(stt.indexOf(strImagerUrl));
    		sttList.add(stm);
    	}
    	return sttList;
    }
    
    /**
     * 返回图片URL列表(并且过滤掉图片URL的IP地址和端口号,从根目录截取)
     * 
     * @param str
     * @return
     */
    public static List<String> getImgSrcList(String str) {
    	//String strImagerUrl = Config.getConfigProperty(SubImagUrlType.subImageUrlPrefix, "/../appImages/ueditor/jsp/upload/image/");
    	//String strOne = "";
        //String strTwo = "";
    	//String stt = "";
    	List<String> sttList = new ArrayList<String>();
        Document doc = Jsoup.parse(str);   
      //  doc.select("img").removeAttr("height").removeAttr("width").removeAttr("style"); 
        Elements elemS =doc.getElementsByTag("img");
        for(Element elemTmp : elemS){
        	String linkHref = elemTmp.attr("src");
        	//linkHref = linkHref.substring(linkHref.indexOf(strImagerUrl));
            sttList.add(linkHref);
        }
        return sttList;  
    }
    
    public static String getImgSrc(String str) {
    	String strImagerUrl = Config.getConfigProperty(SubImagUrlType.subImageUrlPrefix, "/../appImages/ueditor/jsp/upload/image/");
    	String strOne = "";
    	List<String> sttList = new ArrayList<String>();
        Document doc = Jsoup.parse(str);   
        doc.select("img").removeAttr("height").removeAttr("width").removeAttr("style"); 
        Elements elemS =doc.getElementsByTag("img");
        for(Element elemTmp : elemS){
        	String linkHref = elemTmp.attr("src");
        	linkHref = linkHref.substring(linkHref.indexOf(strImagerUrl));
            sttList.add(linkHref);
        }
        if(sttList.size()>0){
        	return sttList.get(0);
        }else{
        	return strOne;
        }
        
    }

    public static String getLastImgSrc(String str) {
        String strImagerUrl = Config.getConfigProperty(SubImagUrlType.subImageUrlPrefix, "/../appImages/ueditor/jsp/upload/image/");
        String strOne = "";
        List<String> sttList = new ArrayList<String>();
        Document doc = Jsoup.parse(str);
        doc.select("img").removeAttr("height").removeAttr("width").removeAttr("style");
        Elements elemS =doc.getElementsByTag("img");
        for(Element elemTmp : elemS){
            String linkHref = elemTmp.attr("src");
            linkHref = linkHref.substring(linkHref.indexOf(strImagerUrl));
            sttList.add(linkHref);
        }
        if(sttList.size()>0){
            return sttList.get(sttList.size()-1);
        }else{
            return strOne;
        }

    }

    public static String getFirstImgSrc(String str) {
        String strOne = "";
        Document doc = Jsoup.parse(str);
        doc.select("img").removeAttr("height").removeAttr("width").removeAttr("style");
        Elements elemS =doc.getElementsByTag("img");
        strOne = elemS.attr("src");
        return strOne;

    }

    /**
     * 判断活动开始或者结束/时间与当前时间的天数差.
     * @param dayValue
     * @return
     */
    public static Integer getEndDayNumber(BigDecimal dayValue){
    	BigDecimal bigDecimalT = new BigDecimal(0);
    	Integer itg = null;
    	if(dayValue.compareTo(bigDecimalT)<0){
    		itg = 0;
    	}else if(dayValue.compareTo(bigDecimalT)==0){
            itg = 0;
    	}else if(dayValue.compareTo(bigDecimalT)>0){
    		itg = dayValue.intValue();
    	}
    	return itg;
    }
    
    /**
     * 返回当前服务器时间
     * @return
     */
    public static Date getSysDate(){
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Integer ing = 0;
    	Date dt1 = new Date();
        try {
        	 dt1 = df.parse(df.format(timestamp));
        	return dt1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return dt1;
    }
    
    public static String subNewsContent(String stt){
    	String s ="";
    	if(null != stt){
    		stt.replaceAll("&nbsp;", " ");
    		stt.replaceAll("&middot;", "•");
    		s = stt.substring(0,30)+"...";
    	}
    	return s;
    }
    
	public static Object subNewsContentAndRemoveAllTag(String stt) {
		stt = StringUtils.removeTagAll(stt);
		String s ="";
    	if(null != stt){
    		stt = stt.replaceAll("&nbsp;", " ");
    		stt = stt.replaceAll("&middot;", "•");
    		if(30>stt.length()){
    			s = stt;
    		}else{
    			s = stt.substring(0,30)+"...";
    		}
    		
    	}
    	return s;
	}
	
	public static String subNewsContentAndRemoveAllTagForPush(String stt) {
		stt = StringUtils.removeTagAll(stt);
		String s ="";
    	if(null != stt){
    		stt = stt.replaceAll("&nbsp;", " ");
    		stt = stt.replaceAll("&middot;", "•");
    		if(30>stt.length()){
    			s = stt;
    		}else{
    			s = stt.substring(0,30)+"...";
    		}
    		
    	}
    	return s;
	}
    

    
    
    /**
     * 计算活动开始时间和活动结束时间.
     * 返回活动开始到结束时间拼接字段
     */
    public static String getActivityTime(String date1,String date2){
    	String resultStr = "";
    	Date date1T = new Date();
    	Date date2T = new Date();
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	DateFormat dfhm = new SimpleDateFormat("HH:mm");
    	DateFormat dfymd = new SimpleDateFormat("yyyy-MM-dd");
    	try {
			date1T = dfymd.parse(date1);
			date2T = dfymd.parse(date2);
			if(date1T.getTime() == date2T.getTime()){
				date1T = df.parse(date1);
				date2T = df.parse(date2);
				resultStr = dfymd.format(date1T)+" "+dfhm.format(date1T)+"-"+dfhm.format(date2T);
			}else{
				resultStr = date1+" --\n"+date2;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	return resultStr;
    }
    
    /**
     * 比较两个时间大小
     * @param DATE1  当前时间
     * @param DATE2  活动开始时间/或者结束时间
     * @return
     */
    public static boolean compare_date(String DATE1, String DATE2) {
    	boolean flag = false;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
            	//当前时间大于结束时间
                return flag;
            } else if (dt1.getTime() < dt2.getTime()) {
            	//当前时间 小于于 结束时间
                flag = true;
                return flag;
            } else {
                return flag;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return flag;
    }

}
