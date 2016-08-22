/*
 * Copyright (c) 2011 NTT DATA BIZINTEGRAL CORPORATION.
 */
package com.shark.common.utils;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.shark.common.utils.file.FileConfigEnum;

/**
 * properties文件读取类。
 */
public class SharkResourceBundleUtil {

    /**
     * 构造函数。
     * 
     */
    private SharkResourceBundleUtil() {

    }

    /**
     * 获取{@link FmsResourceBundle}
     * 
     * @param fileConfig
     *            {@link com.shark.common.utils.file.FileConfigEnum}
     * @return {@link FmsResourceBundle}
     */
    public static SharkResourceBundle getFmsResourceBundle(FileConfigEnum fileConfig) {
        return new SharkResourceBundle(fileConfig.value());
    }

    /**
     * properties文件读取类。
     */
    public static class SharkResourceBundle {

        /** {@link ResourceBundle}的集合 */
        protected List<ResourceBundle> bundleList = CollectionsUtil.newArrayList();

        /**
         * 构造函数。
         */
        private SharkResourceBundle() {

        }

        protected SharkResourceBundle(String baseName) {

            bundleList.add(getResourceBundle(baseName));
        }

        private ResourceBundle getResourceBundle(String baseName) {
            return ResourceBundle.getBundle(baseName, Locale.CHINA);
        }

        /**
         * 获取指定的消息键的消息。
         * 
         * @param key
         *            消息键
         * @return 消息
         */
        public String getResourceValue(String key) {
            String value = null;
            if (CollectionsUtil.isEmpty(bundleList)) {
                return value;
            }

            for (ResourceBundle bundle : bundleList) {
                value = bundle.getString(key);
            }

            return value;
        }

        /**
         * 获取指定文件名的值。
         * 
         * @param key
         *            {@link FileConfigEnum}
         * @return 文件名对应的值
         */
        public String getResourceValue(FileConfigEnum key) {
            return getResourceValue(key.value());
        }
    }
}
