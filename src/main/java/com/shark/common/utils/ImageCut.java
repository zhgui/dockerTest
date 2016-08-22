package com.shark.common.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 图像剪切方法. 图像在html页面上按照 400X400px格式呈现. 以图片左上角为(0,0)坐标原点. 参数 X 为 截取图片的截取点x坐标 参数 Y 为 截取图片的截取点y坐标 参数
 * destWidth 为 截取图片的截取宽度 参数 destHeight 为 截取图片的截取高度
 * 
 * @author User
 * 
 */
public class ImageCut {
    public static void abscut(String srcImageFile, int x, int y, int destWidth,
                              int destHeight) {
        try {
            Image img;
            ImageFilter cropFilter;

            BufferedImage bi = ImageIO.read(new File(srcImageFile));
            int srcWidth = bi.getWidth();
            int srcHeight = bi.getHeight();
            // System.out.println("srcWidth= " + srcWidth + "\tsrcHeight= "
            // + srcHeight);
            //此处的if判断是当要裁剪的宽度 高度 小于 原始图片的 宽度和高度时裁剪.
            //if (srcWidth >= destWidth && srcHeight >= destHeight) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight,
                        Image.SCALE_DEFAULT);
            // 此处的 400 是上传页面上显示图片的 img标签的宽和高的像素值 即 宽:400px 高 400px 按照这个比例等比例裁剪原图片
                int x1 = x*srcWidth/400;
                int y1 = y*srcHeight/400;
                int w = destWidth*srcWidth/400;
                int h = destHeight*srcHeight/400;

                /* 输出客户页面传来的坐标值 用于测试是否裁剪正确
                System.out.println("srcWidth= " + srcWidth + "\tsrcHeight= " + srcHeight);
                System.out.println("w= " + w + "\t h= "  + h);
                System.out.println("x1= " + x1 + "\t y1= "  + y1);
                System.out.println("x= " + x + "\t y= "  + y);
                System.out.println("destWidth= " + destWidth + "\t destHeight= "  + destHeight);
                System.out.println("srcWidth/420= " + srcWidth/400 + "\t srcHeight/420= "  + srcHeight/400);
                */

                cropFilter = new CropImageFilter(x1, y1, w, h);
                img = Toolkit.getDefaultToolkit().createImage(
                        new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(w, h,
                        BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null);
                g.dispose();
                ImageIO.write(tag, "JPEG", new File(srcImageFile));
           // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}