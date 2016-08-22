package com.shark.common.report;

import com.shark.common.report.usermode.DocReport;

/**
 * Date: 11-10-14
 * Time: 下午6:39
 * 需要保存为文件形式的报表数据。只存内容，不与具体文件格式耦合。
 * 可进一步转化为Excel, PDF等格式。
 * <p/>
 * Usage:
 * Simple:
 * <code>
 * DocReport dr = new DocReport().title("Something Report")
 * .header({"姓名","年龄","薪水"})
 * .body({"NAME","AGE","SEX","BIRTHDAY"},rows)
 * </code>
 * <p/>
 * Composite Header:
 * <code>
 * DocReport dr = new DocReport()
 * .title("Something Report")
 * .header()
 * .column("姓名")
 * .column("年龄")
 * .beginCompColumn("薪水")
 * .column("月薪")
 * .column("绩效")
 * .endCompColumn()
 * .column("备注")
 * .body({"NAME","AGE","SEX","BIRTHDAY"},rows);
 * </code>
 * <p/>
 * Other approach of the upper example:
 * DocReport dr = new DocReport();
 * dr.title("Something Report");
 * dr.header().column("姓名")
 * .column("年龄")
 * .beginCompColumn("薪水").column("月薪")
 * .column("绩效")
 * .endCompColumn()
 * .column("备注");
 * dr.body({"NAME","AGE","SEX","BIRTHDAY"},rows);
 * <p/>
 * Style:
 * DocReport dr = new DocReport().borderStyle("solid")
 * .title("Something Report").fontSize(30).
 * .fontWeight("bold")
 * .align("center")
 * .header({"姓名","年龄","薪水"}).fontSize(20)
 * .fontWeight("bold")
 * .align("center")
 * .body({"NAME","AGE","SEX","BIRTHDAY"},rows).fontSize(15);
 */
public class DefaultDocReport extends RptBaseNode implements DocReport, RptNodes {
    private RptTitle title;
    private RptRow header;
    private RptBody body;
    private RptRow footer;
    private RptRow additionalInformation;

    public DefaultDocReport() {
        title = new RptTitle(this);
        title.copyStyleFrom(RptStyle.DEFAULT_TITLE_STYLE);
        title.setUpperStyle(this.style);

        header = new RptRow(this);
        header.copyStyleFrom(RptStyle.DEFAULT_HEADER_STYLE);
        header.setUpperStyle(this.style);

        footer = new RptRow(this);
        footer.copyStyleFrom(RptStyle.DEFAULT_FOOTER_STYLE);
        footer.setUpperStyle(this.style);

        additionalInformation = new RptRow(this);
        additionalInformation.copyStyleFrom(RptStyle.DEFAULT_ADDITIONAL_INFORMATION_STYLE);
        additionalInformation.setUpperStyle(this.style);

        body = new RptBody(this);
        body.copyStyleFrom(RptStyle.DEFAULT_BODY_STYLE);
        body.setUpperStyle(this.style);
    }

    @Override
    public RptTitle title(String s) {
        title().addLine(s);
        return title;
    }

    @Override
    public RptTitle title() {
        return title;
    }

    @Override
    public RptRow header(String... columns) {
        for (String s : columns) {
            header.addColumn(s);
        }
        return header;
    }

    @Override
    public RptRow header() {
        return header;
    }


    @Override
    public RptRow footer(String... columns) {
        for (String s : columns) {
            footer.addColumn(s);
        }
        return footer;
    }

    @Override
    public RptRow footer() {
        return footer;
    }


    @Override
    public RptRow additionalInformation(String... columns) {
        for (String s : columns) {
            additionalInformation.addColumn(s);
        }
        return additionalInformation;
    }

    @Override
    public RptRow additionalInformation() {
        return additionalInformation;
    }


    @Override
    public RptBody body(String... columns) {
        for (String s : columns) {
            body.addColumn(this,s);
        }
        return body;
    }


    @Override
    public RptBody body() {
        return body;
    }

    public RptTitle getTitle() {
        return title;
    }

    public RptRow getHeader() {
        return header;
    }

    public RptBody getBody() {
        return body;
    }


    public RptRow getFooter() {
        return footer;
    }

    public RptRow getAdditionalInformation() {
        return additionalInformation;
    }
}
