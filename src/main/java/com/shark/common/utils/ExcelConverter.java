package com.shark.common.utils;

import com.shark.common.report.*;
import com.shark.common.report.usermode.DocReport;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: A41E
 * Date: 11-10-16
 * Time: 下午6:09
 * To change this template use File | Settings | File Templates.
 */
public class ExcelConverter {
    DefaultDocReport dr;
    HSSFWorkbook wb = new HSSFWorkbook();
    CreationHelper createHelper = wb.getCreationHelper();
    Sheet sheet;
    int nextRowNum = 0;

    static SimpleDateFormat DEFAULT_DATE_FORMAT
            = new SimpleDateFormat("yyyy/MM/dd");

    static DecimalFormat DEFAULT_DECIMAL_FORMAT
            = new DecimalFormat("###,##0.00");

    static DecimalFormat DEFAULT_DECIMAL_FORMAT_FOR_FLOAT
            = new DecimalFormat("###,##0.00000");

    static DecimalFormat DEFAULT_DECIMAL_COUNT_FORMAT
            = new DecimalFormat("###,##0");

    static final Map<String, Map<Integer, Short>> STYLE_TABLE
            = new HashMap<String, Map<Integer, Short>>();

    {
        STYLE_TABLE.put("BORDER", new HashMap<Integer, Short>() {{
            put(RptStyle.BORDER_NONE, CellStyle.BORDER_NONE);
            put(RptStyle.BORDER_THIN, CellStyle.BORDER_THIN);
           
        }});

        STYLE_TABLE.put("ALIGN", new HashMap<Integer, Short>() {{
            put(RptStyle.ALIGN_CENTER, CellStyle.ALIGN_CENTER);
            put(RptStyle.ALIGN_LEFT, CellStyle.ALIGN_LEFT);
            put(RptStyle.ALIGN_RIGHT, CellStyle.ALIGN_RIGHT);
        }});

        STYLE_TABLE.put("FONT_WEIGHT", new HashMap<Integer, Short>() {{
            put(RptStyle.FONT_WEIGHT_NORMAL, Font.BOLDWEIGHT_NORMAL);
            put(RptStyle.FONT_WEIGHT_BOLD, Font.BOLDWEIGHT_BOLD);
        }});
    }

    public ExcelConverter(DocReport dr) {
        this.dr = (DefaultDocReport) dr;
    }

    public HSSFWorkbook ConvertDocReport() {

        sheet = wb.createSheet();
       // sheet.setColumnWidth(1, 20 * 256); 
        
        convertTitle();
        convertHeader();
        convertBody();
        convertFooter();
        convertAdditionalInformation();

        return wb;

    }

    private void convertTitle() {
        List<RptLine> lines = dr.getTitle().getLines();

        for (RptLine line : lines) {
            String value = line.getValue();
            int firstColNum = 0;
            int rowSpan = 1;
            int colSpan;
            RptStyle style = line.getStyle();
            colSpan = line.getSpan();
            if (colSpan == RptStyle.UN_DEFINE) {
                colSpan = dr.getHeader().getSpan();
            }


            Row row = sheet.createRow(nextRowNum);
            createRange(row, firstColNum, value
                    , colSpan, rowSpan, style);
            nextRowNum++;


        }
    }

    private void convertHeader() {
        RptRow header = dr.getHeader();
        convertRptRow(header);
    }

    public void convertFooter() {
        RptRow footer = dr.getFooter();
        convertRptRow(footer);
    }

    public void convertAdditionalInformation() {
        RptRow additionalInformation = dr.getAdditionalInformation();
        convertRptRow(additionalInformation);
    }

    private void convertRptRow(RptRow rptRow) {
        List<RptGeneralColumn> columns = rptRow.getColumns();

        Row row = sheet.createRow(nextRowNum);
        int i = 0;
        for (RptGeneralColumn c : columns) {
            convertColumn(row, i, c);
            i = i + c.getSpan();
        }
        nextRowNum += rptRow.getDepth();
    }

    private void convertColumn(Row row, int colNum, RptGeneralColumn col) {
        String value = col.getValue();
        int depth = col.getDepth();
        int colSpan = col.getSpan();
        RptStyle style = col.getStyle();

        RptGeneralColumns container = (RptGeneralColumns) col.getContainer();
        int rowSpan = getRowSpan(container);

        if (depth == 1) {
            createRange(row, colNum, value, colSpan, rowSpan, style);
        } else if (depth > 1) {
            createRange(row, colNum, value, colSpan, 1, style);
            List<RptGeneralColumn> subCols = ((RptComColumn) col).getColumns();

            int subRowNum = row.getRowNum() + 1;
            Row nextRow = obtainRow(subRowNum);

            for (RptGeneralColumn subCol : subCols) {
                convertColumn(nextRow, colNum, subCol);
                colNum = colNum + subCol.getSpan();
            }

        } else {
            throw new RuntimeException("Column depth shouldn't be less than 1");
        }
    }

    private Row obtainRow(int rowNum) {
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        return row;
    }

    private Cell obtainCell(Row row, int colNum) {
        return row.getCell(colNum, Row.CREATE_NULL_AS_BLANK);
    }

    private int getRowSpan(RptGeneralColumns container) {

        return container.getRestDepth();
    }


//    private void createCell(Row row, int colNum, String value, RptStyle style) {
//        Cell cell = row.createCell(colNum);
//        cell.setCellValue(value);
//    }

    private void mergeCells(int firstRow, int firstCol
            , int colSpan, int rowSpan) {
        int lastRow = firstRow + rowSpan - 1;
        int lastRCol = firstCol + colSpan - 1;

        if (lastRow > firstRow || lastRCol > firstCol) {
            sheet.addMergedRegion(new CellRangeAddress(
                    firstRow,
                    lastRow,
                    firstCol,
                    lastRCol
            ));
        }

    }

    private void convertBody() {
        List<RptBodyColumn> columns;
        List<Map<String, Object>> data;

        RptBody body = dr.getBody();

        columns = body.getColumns();
        data = body.getDataMaps();
        RptStyle rptStyle = body.getStyle();
        CellStyle poiStyle = convertStyle(rptStyle);

        for (Map<String, Object> rowData : data) {
            createBodyRow(columns, rowData, poiStyle);
            nextRowNum++;
        }
    }

    private void createBodyRow(List<RptBodyColumn> columns
            , Map<String, Object> rowData, CellStyle poiStyle) {

        Row row = sheet.createRow(nextRowNum);
        int idx = 0;
        for (RptBodyColumn column : columns) {
            String name = column.getName();
            if (column.getName().contains("$")) {
                name = name.replace("$", "");
            } else if (column.getName().contains("#")) {
                name = name.replace("#", "");
            }
            Object value = rowData.get(name);
            if (value == null) {
                value = "";
            }
            //RptStyle style = column.getStyle();
            if (column.getName().contains("$")) {
                createCellForCurrency(row, idx, value, poiStyle);
            } else if (column.getName().contains("#")) {
                createCellForFloat(row, idx, value, poiStyle);
            } else {
                createCell(row, idx, value, poiStyle);
            }
            idx++;
        }
    }

    private void createRange(Row row, int colNum, String value
            , int colSpan, int rowSpan, RptStyle style) {

        createCell(row, colNum, value);
        mergeCells(row.getRowNum(), colNum, colSpan, rowSpan);
        addRangeStyle(row.getRowNum(), colNum, colSpan, rowSpan, style);
    }

    private void addRangeStyle(int firstRow, int firstCol
            , int colSpan, int rowSpan, RptStyle style) {
        int lastRow = firstRow + rowSpan - 1;
        int lastRCol = firstCol + colSpan - 1;

        CellStyle poiStyle = convertStyle(style);

        for (int r = firstRow; r <= lastRow; r++) {
            Row row = obtainRow(r);
            for (int c = firstCol; c <= lastRCol; c++) {
                Cell cell = obtainCell(row, c);
                cell.setCellStyle(poiStyle);
            }
        }

    }

    private Cell createCell(Row row, int idx, Object value, RptStyle rptStyle) {
        CellStyle poiStyle = convertStyle(rptStyle);
        Cell cell = createCell(row, idx, value, poiStyle);
        return cell;
    }

    private Cell createCell(Row row, int idx, Object value, CellStyle poiStyle) {
        Cell cell = createCell(row, idx, value);
        cell.setCellStyle(poiStyle);
        return cell;
    }

    private Cell createCellForCurrency(Row row, int idx, Object value, CellStyle poiStyle) {
        Cell cell = row.createCell(idx);
        if (value instanceof BigDecimal) {
            cell.setCellValue(DEFAULT_DECIMAL_FORMAT.format(((BigDecimal) value).doubleValue()));
        }
        cell.setCellStyle(poiStyle);
        return cell;
    }

    private Cell createCellForFloat(Row row, int idx, Object value, CellStyle poiStyle) {
        Cell cell = row.createCell(idx);
        if (value instanceof BigDecimal) {
            cell.setCellValue(DEFAULT_DECIMAL_FORMAT_FOR_FLOAT.format(((BigDecimal) value).doubleValue()));
        }
        cell.setCellStyle(poiStyle);
        return cell;
    }


    private Cell createCell(Row row, int idx, Object value) {
        Cell cell = row.createCell(idx);
//        if (value instanceof Date) {
//            cell.setCellValue((Date) value);
//        } else if (value instanceof Calendar) {
//            cell.setCellValue((Calendar) value);
//        } else if (value instanceof Double) {
//            cell.setCellValue((Double) value);
//        } else if (value instanceof Float){
//            cell.setCellValue(((Float) value).doubleValue());
//        } else if (value instanceof Boolean) {
//            cell.setCellValue((Boolean) value);
//        } else {
//            cell.setCellValue(value.toString());
//        }

        if (value instanceof Date) {
            cell.setCellValue(DEFAULT_DATE_FORMAT.format((Date) value));
        } else if (value instanceof Double || value instanceof Float) {
            cell.setCellValue(DEFAULT_DECIMAL_FORMAT.format(value));
        } else if (value instanceof BigDecimal) {
            cell.setCellValue(DEFAULT_DECIMAL_COUNT_FORMAT.format(((BigDecimal) value).longValue()));
        } else {
            cell.setCellValue(value.toString());
        }
        return cell;
    }

    private CellStyle convertStyle(RptStyle rptStyle) {
        CellStyle poiStyle = wb.createCellStyle();

        convertBorderStyle(rptStyle, poiStyle);
        convertAlignStyle(rptStyle, poiStyle);
        convertFontStyle(rptStyle, poiStyle);

        return poiStyle;
    }

    private void convertFontStyle(RptStyle rptStyle, CellStyle poiStyle) {

        if (hasSetFontStyle(rptStyle)) {
            Font font = wb.createFont();
            convertFontWeight(rptStyle, font);
            poiStyle.setFont(font);
        }

    }

    private boolean hasSetFontStyle(RptStyle rptStyle) {
        if (rptStyle.getFontWeight() != RptStyle.UN_DEFINE) {
            return true;
        } else {
            return false;
        }
    }

    private void convertFontWeight(RptStyle rptStyle, Font font) {
        Short styleValue;
        styleValue = convertStyleValue("FONT_WEIGHT", rptStyle.getFontWeight());
        if (styleValue != null) {
            font.setBoldweight(styleValue);
        }
    }

    private void convertAlignStyle(RptStyle rptStyle, CellStyle poiStyle) {
        Short styleValue;
        styleValue = convertStyleValue("ALIGN", rptStyle.getAlign());
        if (styleValue != null) {
            poiStyle.setAlignment(styleValue);
        }
    }

    private void convertBorderStyle(RptStyle rptStyle, CellStyle poiStyle) {
        Short styleValue;
        styleValue = convertStyleValue("BORDER", rptStyle.getBorder());
        if (styleValue != null) {
            poiStyle.setBorderBottom(styleValue);
            poiStyle.setBorderLeft(styleValue);
            poiStyle.setBorderRight(styleValue);
            poiStyle.setBorderTop(styleValue);
        }
    }

    private Short convertStyleValue(String type, int rptStyleItem) {
        Short cellStyle = null;
        Map<Integer, Short> itemMap = STYLE_TABLE.get(type);
        if (itemMap != null) {
            cellStyle = itemMap.get(rptStyleItem);
        }
        return cellStyle;
    }


}

