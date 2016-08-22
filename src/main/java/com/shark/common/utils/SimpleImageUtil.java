package com.shark.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;

import com.alibaba.simpleimage.ImageRender;
import com.alibaba.simpleimage.SimpleImageException;
import com.alibaba.simpleimage.render.ReadRender;
import com.alibaba.simpleimage.render.ScaleParameter;
import com.alibaba.simpleimage.render.ScaleRender;
import com.alibaba.simpleimage.render.WriteRender;

public class SimpleImageUtil {

	 public static void main(String[] args){   
	        File in = new File("c:/cc.jpg");      //原图片  
	        File out = new File("d:/cc_simal.jpg");       //目的图片  
	        ScaleParameter scaleParam = new ScaleParameter(400, 400);  //将图像缩略到1024x1024以内，不足1024x1024则不做任何处理  
	        
	        FileInputStream inStream = null;  
	        FileOutputStream outStream = null;  
	        WriteRender wr = null;  
	        try {  
	        	if(!out.exists()){//判断文件是否真正存在,如果不存在,创建一个;
		        	out.createNewFile(); 
		        }
	            inStream = new FileInputStream(in);  
	            outStream = new FileOutputStream(out);  
	            ImageRender rr = new ReadRender(inStream);  
	            ImageRender sr = new ScaleRender(rr, scaleParam);  
	            wr = new WriteRender(sr, outStream);  
	          
	            wr.render();                            //触发图像处理  
	        } catch(Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            IOUtils.closeQuietly(inStream);         //图片文件输入输出流必须记得关闭  
	            IOUtils.closeQuietly(outStream);  
	            if (wr != null) {  
	                try {  
	                    wr.dispose();                   //释放simpleImage的内部资源  
	                } catch (SimpleImageException ignore) {  
	                    // skip ...   
	                }  
	            }  
	        }  
	    }  
	 
	 
	 public static void SimpleCompressImage(String imageUrl,String destinationUrl,int maxWidth,int maxHeight){
		 
		 File in = new File(imageUrl);      //原图片路径  
	     File out = new File(destinationUrl);       //目的图片路径 
	     ScaleParameter scaleParam = new ScaleParameter(maxWidth, maxHeight);  //将图像缩略到指定尺寸以内，不足的则不做任何处理  
	     FileInputStream inStream = null;  
	        FileOutputStream outStream = null;  
	        WriteRender wr = null;  
	        try {  
	        	//判断文件是否真正存在,如果不存在,创建一个;
	        	if(!out.exists()){
		        	out.createNewFile(); 
		        }
	            inStream = new FileInputStream(in);  
	            outStream = new FileOutputStream(out);  
	            ImageRender rr = new ReadRender(inStream);  
	            ImageRender sr = new ScaleRender(rr, scaleParam);  
	            wr = new WriteRender(sr, outStream);  
	          
	            wr.render();                            //触发图像处理  
	        } catch(Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            IOUtils.closeQuietly(inStream);         //图片文件输入输出流必须记得关闭  
	            IOUtils.closeQuietly(outStream);  
	            if (wr != null) {  
	                try {  
	                    wr.dispose();                   //释放simpleImage的内部资源  
	                } catch (SimpleImageException ignore) {  
	                    // skip ...   
	                }  
	            }  
	        }  
	 }
}
