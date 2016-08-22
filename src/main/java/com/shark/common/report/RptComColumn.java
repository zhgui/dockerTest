package com.shark.common.report;

/**
 * Date: 11-10-15
 * Time: 下午8:28
 * To change this template use File | Settings | File Templates.
 */
public class RptComColumn extends RptGeneralColumns
        implements RptGeneralColumn {

    private String value = "";

    RptComColumn(DefaultDocReport dr, String s) {
        super(dr);
        value = s;
    }

    @Override
    public int getDepth() {
        return (getSubDepth() + 1);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int getRestDepth() {
        int upperRestDepth = ((RptGeneralColumns) container).getRestDepth();
        return (upperRestDepth - 1);
    }

    @Override
    public RptGeneralColumns endComColumn() {
        return (RptGeneralColumns) (this.container);
    }
}
