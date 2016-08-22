package com.shark.common.utils;

import java.io.File;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.shark.pcf.type.SubImagUrlType;

/**
 * Created by User on 2015-06-02.
 * 获取视频的第一帧图像
 */
public class VideoPictureUtil {


    public static String videoFirstPicture(String videoSrc,ServletRequest req)throws Exception{

        // 获取视频的截取开始标识符
        String strImagerUrl =
            Config.getConfigProperty(
                SubImagUrlType.subVideoUrlPrefix,
                "/appImages/ueditor/jsp/upload/video/");
        // 截取视频URL的IP或者域名 端口 及 项目的根目录
        String videoSrcHeader = videoSrc.substring(0, videoSrc.indexOf(strImagerUrl));
        // 获取tomcat的绝对路径
        File webappPath =
            new File(((HttpServletRequest) req).getSession().getServletContext().getRealPath("/"))
                .getParentFile();
        // 获取ffmpeg.exe插件
        String ffmpegPath =
            PathUtils.getWebInfAbsolutePath() + "static\\plugin\\ffmpeg\\ffmpeg.exe";

        // 获取视频URL
        String videoPath =
            webappPath.getAbsolutePath() + videoSrc.substring(videoSrc.indexOf(strImagerUrl));
        // 生成视频的图像,并存入和视频相同的目录下而且使用视频的文件名+jpg
        String videoSrcTmp = videoSrc.substring(videoSrc.indexOf(strImagerUrl));
        String imagePath =
            webappPath.getAbsolutePath()
                + videoSrcTmp
                    .substring(videoSrcTmp.indexOf(strImagerUrl), videoSrcTmp.indexOf("."))
                + ".jpg";
        // 得到生成的视频预览图像服务器路径
        String imgePathURL =
            videoSrcHeader
                + videoSrcTmp
                    .substring(videoSrcTmp.indexOf(strImagerUrl), videoSrcTmp.indexOf("."))
                + ".jpg";
        // 生成图像
        boolean flag = getVideoImage(ffmpegPath, videoPath, imagePath);
        if (flag) {
            return imgePathURL;
        } else {
            return "";
        }

    }


    /**
     * 获得视频从第二秒开始的第一帧图像.并且图像的分辨率按照视频的分辨率.
     * 
     * @param ffmpegPath
     *            是ffmpeg.exe存放的路径
     * @param path
     *            是视频文件的存放路径
     * @param outImagePath
     *            输出缩略图的保存路径
     * @return
     */
    public static boolean getVideoImage(String ffmpegPath,String path,String outImagePath) {
        File file = new File(path);
        if (!file.exists()) {//判断视频文件是否存在
            System.err.println("路径[" + path + "]对应的视频文件不存在!");
            return false;
        }
        //设置参数
        List<String> commands = new java.util.ArrayList<String>();
        commands.add(ffmpegPath);//这里设置ffmpeg.exe存放的路径
        commands.add("-i");
        commands.add(path);//这里是设置要截取缩略图的视频的路径
        commands.add("-ss");
        commands.add("2");// 这里设置的是要截取视频开始播放多少秒后的图，可以自己设置时间
        commands.add("-t");
        commands.add("0.001");
        commands.add(outImagePath);//这里设置输出的截图的保存路径

        try {
            //截取缩略图并保存
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            builder.start();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 使用ffmpeg,截取视频从第二秒开始的一帧图像.并且按照规定的宽高比生成图像
     * 
     * @param ffmpegPath
     * @param path
     * @param outImagePath
     * @param width
     * @param height
     * @return
     */
    public static boolean getVideoImage(String ffmpegPath, String path, String outImagePath,
            int width, int height) {
        File file = new File(path);
        if (!file.exists()) {// 判断视频文件是否存在
            System.err.println("路径[" + path + "]对应的视频文件不存在!");
            return false;
        }
        // 设置参数
        List<String> commands = new java.util.ArrayList<String>();
        commands.add(ffmpegPath);// 这里设置ffmpeg.exe存放的路径
        commands.add("-i");
        commands.add(path);// 这里是设置要截取缩略图的视频的路径
        commands.add("-y");
        commands.add("-f");
        commands.add("image2");
        commands.add("-ss");
        commands.add("2");// 这里设置的是要截取视频开始播放多少秒后的图，可以自己设置时间
        commands.add("-t");
        commands.add("0.001");
        commands.add("-s");
        commands.add(width + "x" + height);// 这里设置输出图片的大小
        commands.add(outImagePath);// 这里设置输出的截图的保存路径

        try {
            // 截取缩略图并保存
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            builder.start();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
