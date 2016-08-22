package com.shark.common.report;

/**
 * Date: 11-10-14
 * Time: 下午7:19
 * To change this template use File | Settings | File Templates.
 */
public class RptRow extends RptGeneralColumns {

    RptRow(DefaultDocReport dr) {
        super(dr);
    }

    @Override
    public int getDepth() {
        return getSubDepth();
    }

    @Override
    public int getRestDepth() {
        return getDepth();
    }

    @Override
    public RptGeneralColumns endComColumn() {
        return this;
    }
}
