package com.shark.common.report;


import com.shark.common.report.usermode.Title;

import java.util.List;

/**
 * Date: 11-10-18
 * Time: 下午2:59
 * To change this template use File | Settings | File Templates.
 */
public class RptTitle extends RptBaseNode implements Title, RptNodes {
    //    private List<RptLine> lines = new ArrayList<RptLine>();
    private RptNodesHelper lines = new RptNodesHelper(this);
    private int span = RptStyle.UN_DEFINE;

    RptTitle(DefaultDocReport dr) {
        super(dr);
    }

    public RptLine line(String s) {
        return addLine(s);
    }

    RptLine addLine(String s) {
        RptLine thisLine = new RptLine(dr, s);
        lines.add(thisLine);
        return thisLine;
    }

    public List<RptLine> getLines() {
        return (List<RptLine>) lines.getAll();
    }

    public RptTitle span(int i) {
        span = i;
        return this;
    }

    int getSpan() {
        return span;
    }

}


