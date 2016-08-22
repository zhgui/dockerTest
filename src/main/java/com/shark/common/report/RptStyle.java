package com.shark.common.report;


/**
 * Date: 11-10-15
 * Time: 下午4:28
 * To change this template use File | Settings | File Templates.
 */
public class RptStyle {
    public static final int UN_DEFINE = -1;

    public static final int ALIGN_CENTER = 1;
    public static final int ALIGN_RIGHT = 2;
    public static final int ALIGN_LEFT = 3;

    public static final int FONT_WEIGHT_NORMAL = 1;
    public static final int FONT_WEIGHT_BOLD = 2;

    public static final int BORDER_NONE = 1;
    public static final int BORDER_THIN = 2;

    public static final RptStyle DEFAULT_HEADER_STYLE = new RptStyle();
    public static final RptStyle DEFAULT_TITLE_STYLE = new RptStyle();
    public static final RptStyle DEFAULT_BODY_STYLE = new RptStyle();
    public static final RptStyle DEFAULT_FOOTER_STYLE = new RptStyle();
    public static final RptStyle DEFAULT_ADDITIONAL_INFORMATION_STYLE = new RptStyle();

    private RptStyle upperStyle;
    private int align = UN_DEFINE;
    private int fontWeight = UN_DEFINE;
    private int border = UN_DEFINE;
    private int width = UN_DEFINE;


    static {
        DEFAULT_HEADER_STYLE.setFontWeight(FONT_WEIGHT_BOLD);
        DEFAULT_HEADER_STYLE.setAlign(ALIGN_CENTER);
        DEFAULT_HEADER_STYLE.setBorder(BORDER_THIN);

        DEFAULT_FOOTER_STYLE.setFontWeight(FONT_WEIGHT_BOLD);
        DEFAULT_FOOTER_STYLE.setBorder(BORDER_THIN);

        DEFAULT_ADDITIONAL_INFORMATION_STYLE.setFontWeight(FONT_WEIGHT_BOLD);
        DEFAULT_ADDITIONAL_INFORMATION_STYLE.setBorder(BORDER_THIN);

        DEFAULT_TITLE_STYLE.setAlign(RptStyle.ALIGN_CENTER);
        DEFAULT_TITLE_STYLE.setFontWeight(RptStyle.FONT_WEIGHT_BOLD);

        DEFAULT_BODY_STYLE.setBorder(BORDER_THIN);
    }

    public void setUpperStyle(RptStyle upperStyle) {
        this.upperStyle = upperStyle;
    }

    public int getAlign() {
        if (align == UN_DEFINE && upperStyle != null) {
            return upperStyle.getAlign();
        } else {
            return align;
        }
    }

    public void setAlign(int align) {
        this.align = align;
    }

    public int getFontWeight() {
        if (fontWeight == UN_DEFINE && upperStyle != null) {
            return upperStyle.getFontWeight();
        } else {
            return fontWeight;
        }
    }

    public void setFontWeight(int fontWeight) {
        this.fontWeight = fontWeight;
    }

    public int getBorder() {
        if (border == UN_DEFINE && upperStyle != null) {
            return upperStyle.getBorder();
        } else {
            return border;
        }
    }

    public void setBorder(int border) {
        this.border = border;
    }

    public int getWidth() {
        if (width == UN_DEFINE && upperStyle != null) {
            return upperStyle.getWidth();
        } else {
            return width;
        }
    }

    public void setWidth(int width) {
        this.width = width;
    }

    // copy self to theStyle except upper style
    void copyTo(RptStyle theStyle) {
        theStyle.setAlign(align);
        theStyle.setBorder(border);
        theStyle.setFontWeight(fontWeight);
        theStyle.setWidth(width);
    }
}
