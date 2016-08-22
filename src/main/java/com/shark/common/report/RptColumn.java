package com.shark.common.report;


import com.shark.common.report.usermode.Column;

/**
 * Date: 11-10-15
 * Time: 下午8:27
 * To change this template use File | Settings | File Templates.
 */
public class RptColumn extends RptBaseNode
        implements Column, RptGeneralColumn{

    String value ="";

    RptColumn(DefaultDocReport dr){
        super(dr);
    }

    RptColumn(DefaultDocReport dr, String s){
        super(dr);
        value = s;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {

        this.value = value;
    }

    @Override
    public int getDepth(){
        return 1;
    }

     @Override
    public RptColumn column(String... values) {
        RptColumn column = null;
        for (String value : values){
            column = ((RptGeneralColumns)container).addColumn(value);
        }
        return column;
    }

    @Override
    public RptComColumn beginComColumn(String s) {
        return ((RptGeneralColumns)container).addComColumn(s);
    }

    public RptGeneralColumns endComColumn() {
        RptGeneralColumns superContainer;
        if(container instanceof RptComColumn){
            RptComColumn c = (RptComColumn)container;
            superContainer = (RptGeneralColumns)c.getContainer();
        } else {
            throw new RuntimeException
              ("endComColumn() should be called after beginComColumn!");
        }
        return superContainer;
    }

    public int getSpan() {
        return 1;
    }
}
