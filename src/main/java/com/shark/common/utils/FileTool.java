package com.shark.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FileTool {
	
	private static String hostname=Config.getParam("ftp.hostname");//ftp服务器
	
	private static int port=Integer.parseInt(Config.getParam("ftp.port"));//端口
	
	private static String username=Config.getParam("ftp.username"); //账户名
	
	private static String password=Config.getParam("ftp.password"); //密码
	
	//private static String pathname; //ftp路径
	
	
	public static void main(String[] args){

		 String pathname = "ftp"; 
		 String filename = "2.jpg"; 
		 String originfilename = "C:\\Users\\zhougui\\Desktop\\1.png";
		
		 System.out.println(FileTool.uploadFileFromProduction(pathname, filename, originfilename));
		 System.out.println("=================");
		 //String localpath = "D:/";		  
		// FavFTPUtil.downloadFile(hostname, port, username, password, pathname, filename, localpath);
		}
  /**
	* 上传文件（可供Action/Controller层使用）
	* @param pathname FTP服务器保存目录
	* @param fileName 上传到FTP服务器后的文件名称
	* @param inputStream 待上传文件的名称（绝对地址）
	* @return
	*/
	 public static boolean 	 uploadFile(String pathname, String fileName,InputStream inputStream){
			 boolean flag = false;
			 FTPClient ftpClient = new FTPClient();
			 ftpClient.setControlEncoding("UTF-8");
			 try {
				   //连接FTP服务器
				 ftpClient.connect(hostname, port);
				 //登录FTP服务器
				 Boolean login = ftpClient.login(username, password);
				 //是否成功登录FTP服务器
				 int replyCode = ftpClient.getReplyCode();
				 if(!FTPReply.isPositiveCompletion(replyCode)){
				 return flag;
				 }				
				 ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				 ftpClient.makeDirectory(pathname);
				 ftpClient.changeWorkingDirectory(pathname);
				 //InputStream inputStream = new FileInputStream(new File(originfilename));
				 flag=ftpClient.storeFile(fileName, inputStream);
				 inputStream.close();
				 ftpClient.logout();
				 //flag = true;
			 } catch (Exception e) {
			 e.printStackTrace();
			 } finally{
				 if(ftpClient.isConnected()){
				 try {
				 ftpClient.disconnect();
				 } catch (IOException e) {
				  e.printStackTrace();
				 }
				 }
			 }
			 return flag;
	 }
	
	
   /**
	 * 上传文件（可对文件进行重命名）
	* @param pathname FTP服务器保存目录
	* @param filename 上传到FTP服务器后的文件名称
	* @param originfilename 待上传文件的名称（绝对地址）
	* @return
	*/
	public static boolean uploadFileFromProduction(String pathname, String filename,String originfilename){
		boolean flag = false;
		try {
			InputStream inputStream = new FileInputStream(new File(originfilename));
			flag = uploadFile(pathname, filename, inputStream); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	* 删除文件
	* @param hostname FTP服务器地址
	* @param port FTP服务器端口号
	* @param username FTP登录帐号
	* @param password FTP登录密码
	* @param pathname FTP服务器保存目录
	* @param filename 要删除的文件名称
	* @return
	*/
	public static boolean deleteFile(String hostname, int port, String username, String password, String pathname, String filename){
		boolean flag = false;
		FTPClient ftpClient = new FTPClient();
		try {
			//连接FTP服务器
			ftpClient.connect(hostname, port);
			//登录FTP服务器
			ftpClient.login(username, password);
			//验证FTP服务器是否登录成功
			int replyCode = ftpClient.getReplyCode();
			if(!FTPReply.isPositiveCompletion(replyCode)){
			return flag;
		}
		//切换FTP目录
		ftpClient.changeWorkingDirectory(pathname);
		ftpClient.dele(filename);
		ftpClient.logout();
		flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(ftpClient.isConnected()){
			try {
			ftpClient.logout();
			} catch (IOException e) {
			
			}
		}
	  }
	 return flag;
	}
	/**
	* 删除文件
	* @param pathname FTP服务器保存目录
	* @param filename 要删除的文件名称
	* @return
	*/
	public static boolean deleteFile(String pathname, String filename){
		boolean flag = false;
		FTPClient ftpClient = new FTPClient();
		try {
			//连接FTP服务器
			ftpClient.connect(hostname, port);
			//登录FTP服务器
			ftpClient.login(username, password);
			//验证FTP服务器是否登录成功
			int replyCode = ftpClient.getReplyCode();
			if(!FTPReply.isPositiveCompletion(replyCode)){
			return flag;
		}
		//切换FTP目录
		ftpClient.changeWorkingDirectory(pathname);
		ftpClient.dele(filename);
		ftpClient.logout();
		flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(ftpClient.isConnected()){
			try {
			ftpClient.logout();
			} catch (IOException e) {
			
			}
		}
	  }
	 return flag;
	}
	/**
	* 下载文件
	* @param hostname FTP服务器地址
	* @param port FTP服务器端口号
	* @param username FTP登录帐号
	* @param password FTP登录密码
	* @param pathname FTP服务器文件目录
	* @param filename 文件名称
	* @param localpath 下载后的文件路径
	* @return
	*/
	public static boolean downloadFile(String hostname, int port, String username, String password, String pathname, String filename, String localpath){
		boolean flag = false;
		FTPClient ftpClient = new FTPClient();
		try {
		//连接FTP服务器
		ftpClient.connect(hostname, port);
		//登录FTP服务器
		ftpClient.login(username, password);
		//验证FTP服务器是否登录成功
		int replyCode = ftpClient.getReplyCode();
		if(!FTPReply.isPositiveCompletion(replyCode)){
			return flag;
		}
		//切换FTP目录
		ftpClient.changeWorkingDirectory(pathname);
		FTPFile[] ftpFiles = ftpClient.listFiles();
		for(FTPFile file : ftpFiles){
			if(filename.equalsIgnoreCase(file.getName())){
				File localFile = new File(localpath + "/" + file.getName());
				OutputStream os = new FileOutputStream(localFile);
				ftpClient.retrieveFile(file.getName(), os);
				os.close();
			}
		}
			ftpClient.logout();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(ftpClient.isConnected()){
			try {
			ftpClient.logout();
			} catch (IOException e) {
			
			}
		 }
		}
		return flag;
	 }
	
	
	
}
