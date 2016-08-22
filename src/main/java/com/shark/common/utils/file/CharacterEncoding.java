package com.shark.common.utils.file;

import com.shark.common.utils.AssertUtil;
import com.shark.common.utils.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * 文字编码枚举类。
 * 
 * @author Shark
 * @version 1.0
 */
public enum CharacterEncoding {
    SJIS("Shift_JIS"),

    UTF8("UTF-8"),

    UTF16("UTF-16"),

    WINDOWS_31J("Windows-31J"),

    MS932("MS932"),

    MS874("MS874"),

    Cp1258("Cp1258"),

    MS936("MS936"),

    MS949("MS949"),

    MS950("MS950"),

    ISO8859_1("ISO-8859-1");

    private String code = null;

    private CharacterEncoding(String encoding) {
        this.code = encoding;
    }

    public String getCode() {
        return this.code;
    }

    public byte[] stringToBytes(String str) {
        try {
            return str.getBytes(getCode());
        } catch (UnsupportedEncodingException e) {
        }
        // TODO 异常处理待追加
        return null;
    }

    public String bytesToString(byte[] bytes) {
        try {
            return new String(bytes, getCode());
        } catch (UnsupportedEncodingException e) {
        }
        // TODO 异常处理待追加
        return null;
    }

    public static CharacterEncoding getCharacterEncoding(String encodingName) {
        AssertUtil.notEmpty(encodingName);
        CharacterEncoding[] encs = values();
        for (CharacterEncoding encoding : encs) {
            if (StringUtils.equalsIgnoreCase(encodingName, encoding.getCode())) {
                return encoding;
            }
        }
        return null;
    }
}