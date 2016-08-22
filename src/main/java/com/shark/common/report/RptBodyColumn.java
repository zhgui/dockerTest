package com.shark.common.report;

/**
 * Date: 11-10-16
 * Time: 下午6:40
 * To change this template use File | Settings | File Templates.
 */
public class RptBodyColumn extends RptBaseNode {
    private String name = "";

    RptBodyColumn(DefaultDocReport dr, String s){
        name = s;
        this.dr = dr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
