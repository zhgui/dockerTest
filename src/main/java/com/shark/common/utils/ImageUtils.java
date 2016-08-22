package com.shark.common.utils;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.shark.pcf.type.SubImagUrlType;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtils {

    /**
     * 等比压缩图像
     * 
     * @param src
     *            源图像文件
     * @param target
     *            压缩后要存放的目标文件
     * @param maxWidth
     *            压缩后允许的最大宽度
     * @param maxHeight
     *            压缩后允许的最大高度
     * @throws java.io.IOException
     */
    public static void transform(String src, String target, int maxWidth, int maxHeight)
        throws Exception {
        String dirStr = target.substring(0, target.lastIndexOf("/") + 1);
        File dir = new File(dirStr);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File srcFile = new File(src);
        File targetFile = new File(target);
        AffineTransform transform = new AffineTransform();
        BufferedImage biSrc = ImageIO.read(srcFile);
        int width = biSrc.getWidth();
        int height = biSrc.getHeight();
        int newWidth = maxWidth;
        int newHeight = (int) (((double) newWidth / width) * height);
        if (newHeight > maxHeight) {
            newHeight = maxHeight;
            newWidth = (int) (((double) newHeight / height) * width);
        }
        double scaleX = (double) newWidth / width;
        double scaleY = (double) newHeight / height;
        transform.setToScale(scaleX, scaleY);
        AffineTransformOp ato = new AffineTransformOp(transform, null);
        BufferedImage biTarget =
            new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_3BYTE_BGR);
        biTarget.getGraphics().drawImage(
            biSrc.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH),
            0,
            0,
            null);
        ato.filter(biSrc, biTarget);
        ImageIO.write(biTarget, "jpeg", targetFile);
    }

    /**
     * 压缩图片大小
     * 
     * @param imagerUrl
     */
    public static void ImageCompression(String imagerUrl) {
        // 获取tomcat的绝对路径
        String absoluteImagePath = System.getProperty("catalina.base").concat("/webapps");

        String imageFilePath =
            Config.getConfigProperty(
                SubImagUrlType.subImageFilePath,
                "/appImages/ueditor/jsp/upload/image/");
        List<String> strList = StringUtils.filterImgSrcListAndImgTag(imagerUrl);
        try {
            for (String stt : strList) {
                stt = stt.substring(stt.indexOf(imageFilePath));
                stt = absoluteImagePath + stt;
                ImageUtils.transform(stt, stt);
                // ImageUtils.transformAndEcondImageType(stt, stt);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 压缩图片大小
     * (需要传入tomcat的根路径)
     * @param imagerUrl
     */
    public static void ImageCompression(String imagerUrl, ServletRequest req) {
        // 获取tomcat的绝对路径
       // String absoluteImagePath = System.getProperty("catalina.base").concat("/webapps");

        // 获取tomcat的绝对路径
        File webappPath =
                new File(((HttpServletRequest) req).getSession().getServletContext().getRealPath("/"))
                        .getParentFile();

        String imageFilePath =
                Config.getConfigProperty(
                        SubImagUrlType.subImageFilePath,
                        "/appImages/ueditor/jsp/upload/image/");
        List<String> strList = StringUtils.filterImgSrcListAndImgTag(imagerUrl);
        try {
            for (String stt : strList) {
                stt = stt.substring(stt.indexOf(imageFilePath));
                stt = webappPath.getAbsolutePath() + stt;
                ImageUtils.transform(stt, stt);
                // ImageUtils.transformAndEcondImageType(stt, stt);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 按图片
     * 
     * @param src
     *            源图像文件
     * @param target
     *            压缩后要存放的目标文件
     * @throws java.io.IOException
     */
    public static void transform(String src, String target) throws Exception {
        String dirStr = src.substring(0, src.lastIndexOf("/") + 1);
        File dir = new File(dirStr);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File srcFile = new File(src);
        File targetFile = new File(target);
        AffineTransform transform = new AffineTransform();
        BufferedImage biSrc = ImageIO.read(srcFile);
        int imageType = biSrc.getType();
        String[] imagePostfixTmp = src.split("\\.");
        String imagePostfix = imagePostfixTmp[imagePostfixTmp.length - 1];
        System.out.println("图片类型为:*******" + imageType);
        int width = biSrc.getWidth();
        int height = biSrc.getHeight();
        int newWidth = width;
        int newHeight = (int) (((double) newWidth / width) * height);
        if (newHeight > height) {
            newHeight = height;
            newWidth = (int) (((double) newHeight / height) * width);
        }
        double scaleX = (double) newWidth / width;
        double scaleY = (double) newHeight / height;
        transform.setToScale(scaleX, scaleY);
        AffineTransformOp ato = new AffineTransformOp(transform, null);
        BufferedImage biTarget =
            new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_3BYTE_BGR);
        biTarget.getGraphics().drawImage(
            biSrc.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT),
            0,
            0,
            null);
        ato.filter(biSrc, biTarget);
        ImageIO.write(biTarget, imagePostfix, targetFile);
    }

    /**
     * 按图片
     *
     * @param src
     *            源图像文件
     * @param target
     *            压缩后要存放的目标文件
     * @throws java.io.IOException
     */
    public static void transformAndEcondImageType(String src, String target) throws Exception {
        String dirStr = src.substring(0, src.lastIndexOf("/") + 1);
        File dir = new File(dirStr);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File srcFile = new File(src);
        File targetFile = new File(target);
        AffineTransform transform = new AffineTransform();
        BufferedImage biSrc = ImageIO.read(srcFile);
        int imageType = biSrc.getType();
        String[] imagePostfixTmp = src.split("\\.");
        String imagePostfix = imagePostfixTmp[imagePostfixTmp.length - 1];
        System.out.println("图片类型为:*******" + imageType);
        int width = biSrc.getWidth();
        int height = biSrc.getHeight();
        int newWidth = width;
        int newHeight = (int) (((double) newWidth / width) * height);
        if (newHeight > height) {
            newHeight = height;
            newWidth = (int) (((double) newHeight / height) * width);
        }
        double scaleX = (double) newWidth / width;
        double scaleY = (double) newHeight / height;
        transform.setToScale(scaleX, scaleY);
        AffineTransformOp ato = new AffineTransformOp(transform, null);
        // BufferedImage biTarget =
        // new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_3BYTE_BGR);
        BufferedImage biTarget =
                    new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        biTarget.getGraphics().drawImage(
                biSrc.getScaledInstance(newWidth, newHeight, Image.SCALE_FAST),
            0,
            0,
            null);
        FileOutputStream out = new FileOutputStream(targetFile);
        // JPEGImageEncoder可适用于其他图片类型的转换
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(biTarget);
        out.close();
    }


    //获取图片第一帧
}
