package com.shark.common.utils.file;


import java.io.*;
import java.net.URL;

/**
 * User: todaytech
 * Date: 12-1-13
 * Time: 下午2:34
 * 文件管理工具类
 */
public class FileUtil {

    /**
     * 删除单个文件或文件夹
     *
     * @param fileName 文件或文件夹名
     * @return 文件删除成功返回true, 否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return false;
        } else {
            if (file.isFile()) {
                return deleteFile(fileName);
            } else {
                return deleteDirectory(fileName);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 被删除文件的文件名
     * @return 单个文件删除成功返回true, 否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.isFile() && file.exists()) {
            file.delete();
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param dir 被删除目录的文件路径
     * @return 目录删除成功返回true, 否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        //如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
            //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }

        if (!flag) {
            return false;
        }

        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 复制文件
     *
     * @param src 源文件流
     * @param dst 目标文件流
     */
    public static void copyFile(InputStream src, File dst) {
        int BUFFER_SIZE = 16 * 1024;
        try {

            InputStream in = null;
            OutputStream out = null;
            try {
                in = new BufferedInputStream(src, BUFFER_SIZE);
                out = new BufferedOutputStream(new FileOutputStream(dst),
                        BUFFER_SIZE);
                byte[] buffer = new byte[BUFFER_SIZE];
                while (in.read(buffer) > 0) {
                    out.write(buffer);
                }
            } finally {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到资源文件的URL
     * <pre>
     *     "abc.txt",
     *     "com/todaytech/ddd.xml
     * </pre>
     *
     * @param resource
     * @return
     */
    public static URL getResource(String resource) {
        ClassLoader classLoader = null;
        URL url = null;

        try {
            //以当前类所在位置查找资源,系统采用这种方式
            classLoader = Thread.currentThread().getContextClassLoader();
            if (classLoader != null) {
                url = classLoader.getResource(resource);
                if (url != null) {
                    return url;
                }
            }
            //以FileHelper类查找资源


            classLoader = FileUtil.class.getClassLoader();
            if (classLoader != null) {
                url = classLoader.getResource(resource);
                if (url != null) {
                    return url;
                }
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        //采用系统的环境变量定义的路径
        return ClassLoader.getSystemResource(resource);
    }

    /**
     * inputStream 转成 string
     *
     * @param in inputStream
     * @return
     */
    public static String inputStreamToString(InputStream in) {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        try {
            for (int n; (n = in.read(b)) != -1; ) {
                out.append(new String(b, 0, n));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return out.toString();
    }
}
