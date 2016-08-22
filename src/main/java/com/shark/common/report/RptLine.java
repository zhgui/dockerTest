package com.shark.common.report;


import com.shark.common.report.usermode.Line;

/**
 * Date: 11-10-18
 * Time: 下午3:11
 * 表示一行数据。与RptRow的区别在于，RptLine只有一个Column.
 */


public class RptLine extends RptBaseNode implements Line {

    private String value ="";

    RptLine(DefaultDocReport dr) {
        super(dr);
        value = "";
    }

    RptLine(DefaultDocReport dr,String s) {
        this(dr);
        value = s;
    }

    public RptLine line(String s){
        return ((RptTitle)container).addLine(s);
    }

    public String getValue() {
        return value;
    }

    public int getSpan(){
        return ((RptTitle)container).getSpan();
    }
}
