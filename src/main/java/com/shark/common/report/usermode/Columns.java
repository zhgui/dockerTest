package com.shark.common.report.usermode;

/**
 * Date: 11-10-17
 * Time: 上午10:36
 * To change this template use File | Settings | File Templates.
 */
public interface Columns extends Node {

    Column column(String... s);

    Columns beginComColumn(String s);

    Columns endComColumn();
}
