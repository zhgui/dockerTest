package com.shark.common.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * User: Tony
 * Date: 11-9-3
 * Time: 下午5:02
 */
public class ReportPage {

    // ------------------------------ FIELDS ------------------------------
    private List result = null;
    private Iterator iterator = null;
    private SearchConditions cond = null;
    private long totalItemCount = 0;
    private long totalPageCount = 0;
    private long curPageNO = 1;
    private int pageSize = 10;
    private long curPageItemCount = 0;
    private Properties otherAttrs = null;
    private String[] columnNames = null;

    private TreeMap<String, String> excelHeadMap = new TreeMap<String, String>();

    // --------------------------- CONSTRUCTORS ---------------------------

    protected ReportPage(List result) {
        totalItemCount = result.size();
        this.result = result;
    }

    protected ReportPage(long totalItemCount, int pageSize, int curPageNO, List result) {
        this.totalItemCount = totalItemCount;
        this.pageSize = pageSize;
        this.curPageNO = curPageNO;
        totalPageCount = totalItemCount / pageSize + (totalItemCount % pageSize == 0 ? 0 : 1);
        curPageItemCount = pageSize;
        if (totalItemCount == 0) {
            curPageItemCount = 0;
        } else if (curPageNO == totalPageCount) {
            if (totalItemCount % pageSize != 0) {
                curPageItemCount = totalItemCount % pageSize;
            }
        }
        this.result = result;
    }

    // -------------------------- STATIC METHODS --------------------------

    public static ReportPage newInstance(List result) {
        return new ReportPage(result);
    }
    
    public static ReportPage getDefaultReportPageAndNoData() {
    	List result = new ArrayList();
    	Map resultMap =  new HashMap<String,String>();
    	ReportPage reportPage = ReportPage.newInstance(result);
    	reportPage.getResult().add(resultMap);
    	return reportPage;
    }

    /**
     * @param rowcount
     * @param result
     * @param pageSize
     * @param curPageNO
     * @return PageIterator
     */
    public static ReportPage newInstance(long rowcount, List result, int pageSize, int curPageNO) {
        return new ReportPage(rowcount, pageSize, rowcount > 0 ? curPageNO : 0, result);
    }

    /**
     * @param rowcount
     * @param result
     * @param columnNames
     * @param pageSize
     * @param curPageNO
     * @return PageIterator
     */
    public static ReportPage newInstance(int rowcount, List result, String[] columnNames, int pageSize, int curPageNO) {
        ReportPage pi = new ReportPage(rowcount, pageSize, rowcount > 0 ? curPageNO : 0, result);
        pi.columnNames = columnNames;
        return pi;
    }

    // ------------------------ EXPOSE METHODS ------------------------

    public String getColumnName(int index) {
        return columnNames[index];
    }

    public String[] getColumnNames() {
        if (columnNames != null) {
            String[] cloneColumnNames = new String[columnNames.length];
            System.arraycopy(columnNames, 0, cloneColumnNames, 0, cloneColumnNames.length);
            return cloneColumnNames;
        } else {
            return null;
        }
    }

    public final String getParameter(String paramName) {
        if (otherAttrs != null) {
            return otherAttrs.getProperty(paramName);
        } else {
            return null;
        }
    }

    public void setCond(SearchConditions p_cond) {
        cond = p_cond;
    }

    public SearchConditions getCond() {
        return cond;
    }

    public boolean hasNextPage() {
        return curPageNO < totalPageCount;
    }

    public boolean hasPrevPage() {
        return curPageNO > 1;
    }


    /**
     * The attributes will be generate <b>&lt;paramName&gt;paramValue&lt;/paramName&gt; </b> into the XML file .
     *
     * @param paramName
     * @param paramValue 值

     */
    public final void setAttribute(String paramName, String paramValue) {
        if (otherAttrs == null) {
            otherAttrs = new Properties();
        }
        otherAttrs.setProperty(paramName, paramValue);
    }

    public final String getAttribute(String paramName) {
        if (otherAttrs == null) {
            return null;
        } else {
            return otherAttrs.getProperty(paramName);
        }
    }

    // --------------------- GETTER / SETTER METHODS ---------------------

    public long getCurPageItemCount() {
        return curPageItemCount;
    }

    public long getCurPageNO() {
        return curPageNO;
    }

    public final Properties getOtherAttrs() {
        return otherAttrs;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalItemCount() {
        return totalItemCount;
    }

    public long getTotalPageCount() {
        return totalPageCount;
    }

    // -------------------------- OTHER METHODS --------------------------

    /**
     * @param key  - the XML node key value
     * @param text - the XML node text value
     * @return StringBuffer - the XML node string
     */
    protected StringBuffer createNode(String key, String text) {
        StringBuffer node = new StringBuffer("\t\t");
        node.append("<");
        node.append(key);
        node.append(">");
        node.append(text);
        node.append("</");
        node.append(key);
        node.append(">\n");
        return node;
    }


    public TreeMap<String,String> getExcelHeadMap() {
        return excelHeadMap;
    }

    public void setExcelHeadMap(TreeMap<String,String> excelHeadMap) {
        this.excelHeadMap = excelHeadMap;
    }

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }
}
