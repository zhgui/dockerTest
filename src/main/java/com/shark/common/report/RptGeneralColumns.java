package com.shark.common.report;


import com.shark.common.report.usermode.Columns;

import java.util.List;

/**
 * Date: 11-10-17
 * Time: 上午10:36
 * To change this template use File | Settings | File Templates.
 */
public abstract class RptGeneralColumns extends RptBaseNode
        implements Columns, RptNodes {

    private RptNodesHelper columns = new RptNodesHelper(this);


    RptGeneralColumns(DefaultDocReport dr) {
        super(dr);
    }



    void addGeneralColumn(RptGeneralColumn c) {
        columns.add(c);
    }

    RptColumn addColumn(String s) {
        DefaultDocReport dr = this.getDocReport();
        RptColumn col = new RptColumn(dr, s);
        addGeneralColumn(col);
        return col;
    }

    public List<RptGeneralColumn> getColumns() {
        return columns.getAll();
    }

    RptGeneralColumn getColumn(int idx) {
        return (RptGeneralColumn) columns.get(idx);
    }

    RptComColumn addComColumn(String s) {
        DefaultDocReport dr = this.getDocReport();
        RptComColumn col = new RptComColumn(dr, s);
        addGeneralColumn(col);
        return col;
    }


    @Override
    public RptComColumn beginComColumn(String s) {
        return this.addComColumn(s);
    }

    @Override
    abstract public RptGeneralColumns endComColumn();

    @Override
    public RptColumn column(String... values) {
        RptColumn column = null;
        for (String value : values){
            column = this.addColumn(value);
        }
        return column;
    }


    //深度（以单元格为单位）
    abstract int getDepth();

    //打印本级内容后剩下的深度（以单元格为单位）
    public abstract int getRestDepth();

    // 横向跨越多少个列 （以单元格为单位）
    public int getSpan() {
        int span = 0;
        List<RptGeneralColumn> cList = columns.getAll();
        for (RptGeneralColumn c : cList) {
            int cSpan = c.getSpan();
            span = span + cSpan;
        }
        return span;
    }

    protected int getSubDepth() {
        int depth = 0;
        List<RptGeneralColumn> cList = columns.getAll();
        for (RptGeneralColumn c : cList) {
            int d = c.getDepth();
            if (d > depth) {
                depth = d;
            }
        }
        return depth;
    }


}
