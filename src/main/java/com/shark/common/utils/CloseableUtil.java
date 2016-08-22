package com.shark.common.utils;

import java.io.Closeable;
import java.io.IOException;

import com.shark.pcf.exception.BizApiException;

/**
 * 关闭的数据源或目标的类。
 * 
 * @author Shark
 * @version 1.0
 * 
 */
public class CloseableUtil {
    public static void close(Closeable closeable) throws BizApiException {
        if (closeable == null)
            return;
        try {
            closeable.close();
        } catch (IOException e) {
            // TODO 输入输出发生错误
            throw new BizApiException("F.BIZ.FC.07001", e);
        }
    }

    public static void closeQuietly(Closeable closeable) {
        try {
            close(closeable);
        } catch (BizApiException ignore) {
        }
    }
}