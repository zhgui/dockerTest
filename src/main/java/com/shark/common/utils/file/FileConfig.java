package com.shark.common.utils.file;

/**
 * 文件输入输出文件名枚举。
 */
public enum FileConfig implements FileConfigEnum {

    /** 名称定义文件 */
    SHARK_PCF_NAMES("resources/shark_pcf-names");

    /** 值 */
    private String value;

    private FileConfig(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
