package com.shark.common.utils;

public class PathUtils {

    /**
     * 获取当前项目的WEB-INF的绝对路径
     * 
     * @return
     */
    public static String getWebInfAbsolutePath() {
        // 获取当前项目WEB-INF的绝对路径.
        String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
        path = path.replace('/', '\\'); // 将/换成\
        path = path.replace("file:", ""); // 去掉file:
        path = path.replace("classes\\", ""); // 去掉class\
        path = path.substring(1); // 去掉第一个\,如 \D:\JavaWeb.
        return path;
    }

}
