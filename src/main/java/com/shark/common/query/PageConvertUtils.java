package com.shark.common.query;


import com.alibaba.fastjson.JSON;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Tony
 * Date: 11-9-5
 * Time: 下午5:17
 */
public class PageConvertUtils {
    public static String toJqGridPage(ReportPage reportPage) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"total\":\"")
          .append(reportPage.getTotalPageCount())
          .append("\",\"page\":\"")
          .append(reportPage.getCurPageNO())
          .append("\",\"records\":\"")
          .append(reportPage.getTotalItemCount())
          .append("\",\"rows\":")
          .append(JSON.toJSON(reportPage.getResult()))
          .append(",\"userdata\":")
          .append(JSON.toJSON(reportPage.getOtherAttrs()))
          .append("}");
        return sb.toString();
    }

    public static Map convertToJqGrid(ReportPage reportPage) {
        Map map = new HashMap();
        map.put("page", reportPage.getCurPageNO());
        map.put("pageSize", reportPage.getPageSize());
        map.put("pages", reportPage.getTotalPageCount());
        map.put("rows", reportPage.getResult());
        map.put("total", reportPage.getTotalItemCount());
        map.put("userdata", reportPage.getOtherAttrs());
        return map;
    }

    public static Map convertToJqGrid(Page page) {
        Map map = new HashMap();
        map.put("page", page.getNumber() + 1);
        map.put("pageSize", page.getSize());
        map.put("pages", page.getTotalPages());
        map.put("rows", page.getContent());
        map.put("total", page.getTotalElements());
        map.put("userdata", null);
        return map;
    }

    public static void convertToJqGrid(ReportPage reportPage, HttpServletResponse response) {
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = null;
        try {
            String jsonString = JSON.toJSONString(convertToJqGrid(reportPage));
            out = response.getWriter();
            out.println(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static void toJqGridPageJSON(ReportPage reportPage, HttpServletResponse response) {
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = null;
        try {
            String jsonString = toJqGridPage(reportPage);
            out = response.getWriter();
//            System.out.println(jsonString);
            out.println(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
