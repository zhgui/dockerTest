package com.shark.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

import com.shark.common.utils.file.FileUtil;

/**
 * Created with IntelliJ IDEA.
 * User: Sunlight
 * Date: 13-3-21
 * Time: 下午2:02
 */
public class Config {
    private static final String configFileName = "resources.properties";
    protected static Properties configProps = null;

    /**
	 * 获取配置文件的value 值
	 * @param key  key值
	 * @return
	 */
	public  static String getParam(String key) {
		String value = "";
		try {
			Properties properties = new Properties();
			properties.load(Config.class.getResourceAsStream("/"+configFileName));
			value = properties.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

    /**
     * 获取config配置文件某一配置属性值，如找不到，则返回该默认值(defaultVal)
     *
     * @param key        String 配置属性名
     * @param defaultVal 默认值
     * @return String 配置属性值
     */
    public static String getConfigProperty(String key, String defaultVal) {
        if (configProps == null) {
            try {
                openConfigFile();
            } catch (IOException e) {
                String exceptionName = e.getClass().getName();
                throw new RuntimeException("****** " + exceptionName + " ******", e);
            }
        }
        if (key == null || key.trim().equals("")) {
            throw new IllegalArgumentException("property must be non-null");
        }
        String property = configProps.getProperty(key);
        if (property != null) {
            return property;
        } else {
            return defaultVal;
        }
    }


    /**
     * 转换prompt file的内容以Properties对象保存,并将properties对象返回
     *
     * @return Properties properties对象
     */
    public static Properties getConfigProps() {
        if (configProps == null) {
            try {
                openConfigFile();
            } catch (IOException e) {
                String exceptionName = e.getClass().getName();
                throw new RuntimeException("****** " + exceptionName + " ******", e);
            }
        }
        return configProps;
    }


    /**
     * 转换config file的内容以Properties对象保存
     *
     * @throws java.io.IOException
     */
    public static void openConfigFile() throws IOException {
        configProps = getPropFromFile(configFileName);
    }


    private static Properties getPropFromFile(String filename) throws IOException {
        Properties newProperties = new Properties();
        URL resource = FileUtil.getResource(filename);
        if (resource == null)
            throw new RuntimeException("系统配置文件不在路径中, 文件名: " + filename);
        InputStream inStream = resource.openStream();
        try {
            newProperties.load(inStream);
        } finally {
            IOUtils.closeQuietly(inStream);

        }
        return newProperties;
    }


}
