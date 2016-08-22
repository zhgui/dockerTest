package com.shark.common.report;


import com.shark.common.report.usermode.Node;

/**
 * Date: 11-10-18
 * Time: 上午11:29
 * To change this template use File | Settings | File Templates.
 */
public interface RptNode extends Node {
    DefaultDocReport getDocReport();

    RptStyle getStyle();

    void setContainer(RptNodes thisNode);

    RptNodes getContainer();

    void setUpperStyle(RptStyle style);
}
