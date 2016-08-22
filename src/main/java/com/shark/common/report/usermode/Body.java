package com.shark.common.report.usermode;

import java.util.List;
import java.util.Map;

/**
 * Date: 11-10-20
 * Time: 上午10:47
 * To change this template use File | Settings | File Templates.
 */
public interface Body extends Node {

    Body dataMaps(List<Map<String, Object>> data);
}
