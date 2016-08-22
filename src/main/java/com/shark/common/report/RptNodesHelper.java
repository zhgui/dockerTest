package com.shark.common.report;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Date: 11-10-21
 * Time: 下午8:11
 * To change this template use File | Settings | File Templates.
 */
public class RptNodesHelper {
    RptNodes thisNode;
    private List nodes = new ArrayList();

    RptNodesHelper(RptNodes node){
        thisNode = node;
    }

    void add(RptNode node){
        nodes.add(node);
        node.setContainer(thisNode);
        node.setUpperStyle(thisNode.getStyle());
    }

    RptNode get(int idx){
        if (idx < nodes.size()){
           return (RptNode)nodes.get(idx);
        } else {
            return null;
        }
    }

    List getAll(){
        return Collections.unmodifiableList(nodes);
    }
}
