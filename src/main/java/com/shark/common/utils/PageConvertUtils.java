package com.shark.common.utils;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by acer on 2014/11/2.
 */
public class PageConvertUtils {

    public static Map convertToJqGrid(Page page) {
        Map map = new HashMap();
        map.put("page", page.getNumber() + 1);
        map.put("pageSize", page.getSize());
        map.put("pages", page.getTotalPages());
        map.put("rows", page.getContent());
        map.put("total", page.getTotalElements());
        return map;
    }
}
