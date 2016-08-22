package com.shark.common.report.usermode;

import com.shark.common.report.DefaultDocReport;

/**
 * Date: 11-10-20
 * Time: 上午11:19
 * To change this template use File | Settings | File Templates.
 */
public class DocReportFactory {
    public DocReport createReport() {
        return new DefaultDocReport();
    }
}
