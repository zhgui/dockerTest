package com.shark.common.report;

/**
 * Date: 11-10-15
 * Time: 下午3:45
 * To change this template use File | Settings | File Templates.
 */
public abstract class RptBaseNode implements RptNode {

    protected RptStyle style = new RptStyle();

    protected DefaultDocReport dr;

    protected RptNodes container;

    RptBaseNode() {
    }

    RptBaseNode(DefaultDocReport dr) {
        this.dr = dr;
    }

    @Override
    public DefaultDocReport getDocReport() {
        return dr;
    }

    @Override
    public RptTitle title(String s) {
        return dr.title(s);
    }

    @Override
    public RptTitle title() {
        return dr.title();
    }

    @Override
    public RptRow header(String... columns) {
        return dr.header(columns);
    }

    @Override
    public RptRow header() {
        return dr.header();
    }

    @Override
    public RptRow footer(String... columns) {
        return dr.footer(columns);
    }

    @Override
    public RptRow footer() {
        return dr.footer();
    }


    @Override
    public RptRow additionalInformation(String... columns) {
        return dr.additionalInformation(columns);
    }

    @Override
    public RptRow additionalInformation() {
        return dr.additionalInformation();
    }


    @Override
    public RptBody body(String... columns) {
        return dr.body(columns);
    }

    @Override
    public RptBody body() {
        return dr.body();
    }

    @Override
    public RptStyle getStyle() {
        return style;
    }

    //@Override
    public void copyStyleFrom(RptStyle theStyle) {
        style.setAlign(theStyle.getAlign());
        style.setBorder(theStyle.getBorder());
        style.setFontWeight(theStyle.getFontWeight());
        style.setWidth(theStyle.getWidth());
    }

    @Override
    public void setUpperStyle(RptStyle theStyle) {
        style.setUpperStyle(theStyle);

    }
    @Override
    public void setContainer(RptNodes container) {
        this.container = container;
    }

        @Override
    public RptNodes getContainer() {
        return container;
    }

}
