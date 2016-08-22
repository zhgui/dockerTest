package com.shark.common.utils;

import com.shark.common.report.usermode.DocReport;
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Date: 11-9-16
 * Time: 下午3:38
 * To change this template use File | Settings | File Templates.
 */
public class ExcelUtils {
    /**
     * 生成简单的Excel表头
     * @param workbook
     * @param sheet
     * @param heads
     * @param indexY
     * @param indexX
     * @return
     */
    public static int createHead(HSSFWorkbook workbook,HSSFSheet sheet ,String[] heads,int indexY,short indexX){
        HSSFRow row = sheet.createRow(indexY);
        for(int i=0;i<heads.length;i++){
            createCell(workbook, row, (short) (indexX + i), HSSFCellStyle.ALIGN_CENTER, heads[i]);
        }
        return indexY;
    }

    /**
     * 利用反射，生成Excel内容，针对实体类的基本属性，不包含关系映射对象
     * @param workbook
     * @param sheet
     * @param result
     * @param columns
     * @param indexY
     * @param indexX
     * @return
     * @throws NoSuchMethodException
     * @throws java.lang.reflect.InvocationTargetException
     * @throws IllegalAccessException
     */
    public static int createContent(HSSFWorkbook workbook,HSSFSheet sheet ,Collection result,String[] columns,int indexY,short indexX) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String,Method> methodMap = new HashMap<String, Method>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Object obj : result){
            if(obj instanceof Map){
                break;
            }
            Class cl = obj.getClass();
            for(String column : columns){
                Method method = cl.getMethod("get" + column, new Class<?>[] {});
                methodMap.put(column,method);
            }
            break;
        }
        for(Object obj : result){
            HSSFRow row = sheet.createRow(indexY);
            for(int i=0;i<columns.length;i++){
                Object value = null;
                if(obj instanceof Map) {
                    value = ((Map)obj).get(columns[i]);
                }else{
                    value = methodMap.get(columns[i]).invoke(obj,null);
                }
                if(value != null && value instanceof Date){
                    value = sdf.format(value);
                }
                createCell(workbook, row, (short) (indexX + i), HSSFCellStyle.ALIGN_CENTER, value==null?"":value.toString());
            }
            indexY ++;
        }
        return indexY==0?0:indexY-1;
    }
    /**
     * 得到简单格式的Excel 对象
     * @param result
     * @param heads
     * @param columns   具体对象的首字母大写的属性名　例如：原对象属性name，传的值为Name
     *                   若是对象为Map　column为Map的key
     * @param indexY
     * @param indexX
     * @return
     * @throws NoSuchMethodException
     * @throws java.lang.reflect.InvocationTargetException
     * @throws IllegalAccessException
     */
    public static HSSFWorkbook getSimpleWorkbook(Collection result,String[] heads,String[] columns,int indexY,short indexX)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        int index = createHead(workbook,sheet,heads,indexY,indexX);
        createContent(workbook,sheet,result,columns,index+1,indexX);
        return workbook;
    }

    /**
     * 生成Excel单元格
     * @param workbook
     * @param row
     * @param colIndex
     * @param align
     * @param value
     */
    public static void createCell(HSSFWorkbook workbook, HSSFRow row, short colIndex, short align, String value) {
        HSSFCell cell = row.createCell(colIndex);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        //cell.setEncoding(HSSFCell.ENCODING_UTF_16);
        cell.setCellValue(value);
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(align);
        cell.setCellStyle(cellStyle);
    }

    /**
     * 导出简单格式Excel
     * @param rep
     * @param result
     * @param heads
     * @param columns   具体对象的首字母大写的属性名　例如：原对象属性name，传的值为Name
     * @param indexY
     * @param indexX
     */
    public static void exportExcel(HttpServletResponse rep,String userId,
                                   Collection result,String[] heads,String[] columns,int indexY,short indexX){
        BufferedOutputStream bos1 = null;
        OutputStream output = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            HSSFWorkbook workbook = getSimpleWorkbook(result,heads,columns,indexY,indexX);
            final String CONTENT_TYPE = "plain-text/txt;charset=" + "GB2312";
            rep.reset();
            rep.setContentType(CONTENT_TYPE);
            String filename = userId+"_result.xls";
            rep.setHeader("Content-Disposition", "attachment;filename=" + filename + "");
            bos1 = new BufferedOutputStream(new FileOutputStream(filename));
            workbook.write(bos1);
            bos1.close();
            workbook = null;
            output = rep.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(filename));
            bos = new BufferedOutputStream(output);
            byte data[] = new byte[4096];
            int size = 0;
            size = bis.read(data);
            while (size != -1) {
                bos.write(data, 0, size);
                size = bis.read(data);
            }
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(bis != null)
                    bis.close();
                if(bos != null)
                    bos.close();
                if(output != null)
                    output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


     public static void docReport2Web(DocReport dr, HttpServletResponse response)
            throws Exception {
        ExcelConverter converter = new ExcelConverter(dr);
        HSSFWorkbook wb = converter.ConvertDocReport();
        excel2Web(wb,response);
    }
     
    public static void docReport2WebAndTableName(DocReport dr,String tableName, HttpServletResponse response)
             throws Exception {
         ExcelConverter converter = new ExcelConverter(dr);
         HSSFWorkbook wb = converter.ConvertDocReport();
         excel2WebAndTableName(wb,tableName,response);
     }
    
    public static void excel2WebAndTableName(HSSFWorkbook wb,String tableName
            ,HttpServletResponse response)
            throws Exception {
        OutputStream output = response.getOutputStream();
        final String CONTENT_TYPE = "plain-text/txt;charset=" + "GB2312";
        try {
            response.reset();
            response.setContentType(CONTENT_TYPE);
            String fileName = tableName+".xls";
            response.setHeader("Content-Disposition", "attachment;filename=" +  new String( fileName.getBytes("gb2312"), "ISO8859-1" ) + "");
            wb.write(output);
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

    public static void excel2Web(HSSFWorkbook wb
            ,HttpServletResponse response)
            throws Exception {
        OutputStream output = response.getOutputStream();
        final String CONTENT_TYPE = "plain-text/txt;charset=" + "GB2312";
        try {
            response.reset();
            response.setContentType(CONTENT_TYPE);
            String filename = "report.xls";
            response.setHeader("Content-Disposition", "attachment;filename=" + filename + "");
            wb.write(output);
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }
}



