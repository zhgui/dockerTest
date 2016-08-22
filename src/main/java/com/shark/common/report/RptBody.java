package com.shark.common.report;


import com.shark.common.report.usermode.Body;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Date: 11-10-14
 * Time: 下午7:20
 * To change this template use File | Settings | File Templates.
 */
public class RptBody extends RptBaseNode implements Body, RptNodes{
    //private List<RptBodyColumn> columns = new ArrayList<RptBodyColumn>();
    private RptNodesHelper columns = new RptNodesHelper(this);
    private List<Map<String, Object>> data;

    RptBody(DefaultDocReport dr){
        super(dr);
    }

    public void addColumn(DefaultDocReport dr, String s) {
        RptBodyColumn c = new RptBodyColumn(dr, s);
        columns.add(c);
    }

    public List<RptBodyColumn> getColumns(){
        return columns.getAll();
    }

    public List<Map<String, Object>> getDataMaps(){
        return Collections.unmodifiableList(data);
    }

    public RptBody dataMaps(List<Map<String, Object>> theData) {
        data = theData;
           return this;
    }
}
