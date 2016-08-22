package com.shark.common.report.usermode;

/**
 * Date: 11-10-18
 * Time: 上午11:29
 * To change this template use File | Settings | File Templates.
 */
public interface Node {

    Title title(String value);

    Title title();

    Columns header(String... columns);

    Columns header();

    Columns footer(String... columns);

    Columns footer();

    Columns additionalInformation(String... columns);

    Columns additionalInformation();

    Body body(String... columns);

    Body body();

}
