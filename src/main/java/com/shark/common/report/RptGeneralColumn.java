package com.shark.common.report;

/**
 * Date: 11-10-22
 * Time: 上午10:37
 * To change this template use File | Settings | File Templates.
 */
public interface RptGeneralColumn extends RptNode {
    int getSpan();
    int getDepth();
    String getValue();
}
