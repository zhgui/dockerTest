package com.shark.common.utils;

import com.shark.common.exception.GenericBizException;
import com.shark.common.exception.SysException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * User: Tony
 * Date: 11-9-3
 * Time: 下午5:00
 */
public class XMLHelper {

    // ------------------------------ FIELDS ------------------------------

    /**
     * default logger for all static methods, like System.io.println(); but better.
     */
    public static final Logger syslog = LoggerFactory.getLogger(XMLHelper.class);


    private Document document = null;

    // --------------------------- CONSTRUCTORS ---------------------------

    public XMLHelper(String xmlSrc) throws DocumentException {
        if (xmlSrc == null) {
            throw new IllegalArgumentException("xmlSrc cannot be null.");
        }
        document = DocumentHelper.parseText(xmlSrc);
    }

    // -------------------------- STATIC METHODS --------------------------

    /**
     * 格式化XML字符串信息,以便输出页面,主要转换一些符号为HTML格式
     *
     * @param source - the source string XML字符串
     * @return String - the string being encoded 格式化后的字符串
     */
    public static String XMLEncode(String source) {
        if (source == null) {
            return null;
        }
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            switch (source.charAt(i)) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                default:
                    result.append(source.charAt(i));
            }
        }
        return result.toString();
    }

    /**
     * Get XMLString from the request stream.
     *
     * @param p_rqXMLCarrier HttpServletRequest
     * @param p_sDftValue    String
     * @return String
     */
    public static String getXMLString(javax.servlet.http.HttpServletRequest p_rqXMLCarrier, String p_sDftValue) {
        String sReturn = p_sDftValue;
        try {
            StringBuffer sbXMLString = new StringBuffer();
            char[] cBuffer = new char[100];
            int iLength;
            java.io.InputStreamReader isrXMLStreamReader = new java.io.InputStreamReader(p_rqXMLCarrier.getInputStream(), "UTF-8");
            while ((iLength = isrXMLStreamReader.read(cBuffer)) != -1) {
                sbXMLString.append(cBuffer, 0, iLength);
            }
            //isrXMLStreamReader = null;
            //cBuffer = null;
            sReturn = sbXMLString.toString();
            //syslog.info(" * XMLString = " + sReturn + " * ");
            //sbXMLString = null;
            //if(isrXMLStreamReader!=null)
            //{
            isrXMLStreamReader.close();
            //}
        } catch (Exception e) {
            syslog.error("", e);
        }
        return sReturn;
    }

    public static boolean isXMLValid(String xmlSrc) {
        boolean bReturn = true;
        try {
            if (xmlSrc == null) {
                throw new IllegalArgumentException("xmlSrc cannot be null.");
            }
            DocumentHelper.parseText(xmlSrc);
        } catch (DocumentException e) {
            syslog.error("XML Src is invalid, XML Src dump : " + xmlSrc, e);
            bReturn = false;
        }
        return bReturn;
    }


    /**
     * 如果dtos里面没有元素, 则返回"";
     *
     * @param dtos
     * @return
     */
    public static String genSimpleXML(XMLDTO[] dtos) {
        StringBuffer p_sb = new StringBuffer();
        if (dtos == null || dtos.length == 0) return "";
        for (int i = 0; i < dtos.length; i++) {
            XMLDTO value = dtos[i];
            if (i == 0) {
                p_sb.append("<").append(value.getTagName()).append("S>");
            }
            p_sb.append(value.toXML());
            if (i == dtos.length - 1) {
                p_sb.append("</").append(value.getTagName()).append("S>");
            }
        }
        return p_sb.toString();
    }

    private static boolean isArray(Object p_curValue) {
        return p_curValue instanceof int[] || p_curValue instanceof double[] || p_curValue instanceof long[] || p_curValue instanceof float[] ||
                p_curValue instanceof char[] || p_curValue instanceof byte[] || p_curValue instanceof short[];
        //p_curValue instanceof Integer[] || p_curValue instanceof Double[] || p_curValue instanceof Long[] || p_curValue instanceof Float[] ||
        //p_curValue instanceof Byte[] || p_curValue instanceof Short[] ||
        //p_curValue instanceof String[] || p_curValue instanceof StringBuffer[] || p_curValue instanceof BigDecimal[];
    }

    /**
     * @param key  - the XML node key value
     * @param text - the XML node text value
     * @return String - the XML node string
     */
    public static String createXmlNode(String key, Object text) {
        return ("<" + key + ">" + XMLEncode(String.valueOf(text)) + "</" + key + ">\n");
    }

    public static String createXMLAtt(String key, Object text) {
        return (key + "=\"" + XMLEncode(String.valueOf(text)) + "\" ");
    }

    /**
     * @param key  - the XML node key value
     * @param text - the XML node text value
     * @return String - the XML node string
     */
    public static String createXmlNodeNoShift(String key, Object text) {
        if (key.indexOf("$") == -1) {
            return ("<" + key + ">" + XMLEncode(String.valueOf(text)) + "</" + key + ">");
        } else {
            return "";
        }
    }

    /**
     * @param key        - the XML node key value
     * @param text       - the XML node text value
     * @param sAttribute - the attribute string to insert
     * @return String - the XML node string
     */
    public static String createXmlNodeNoShift(String key, Object text, String sAttribute) {
        if (key.indexOf("$") == -1) {
            return ("<" + key + " " + sAttribute + " >" + XMLEncode(String.valueOf(text)) + "</" + key + ">");
        } else {
            return "";
        }
    }

    // ------------------------ EXPOSE METHODS ------------------------

    /**
     * Please ensure the searchresult of the XPath is ONE node. Not found or null
     * return the Dft.
     *
     * @param xPath String
     * @param Dft   String
     * @return java.lang.String
     */
    public String getNodeText(String xPath, String Dft) {
        String sReturn;
        Node node = document.selectSingleNode(xPath);
        if (node != null) {
            sReturn = node.getText();
            sReturn = (null == sReturn) ? (Dft) : (sReturn.trim());
        } else {
            sReturn = Dft;
        }
        return sReturn;
    }

    /**
     * Not found or null,return the Dft.
     *
     * @param xPath String
     * @param dft   String[]
     * @return java.lang.String[]
     */
    public String[] getNodesText(String xPath, String[] dft) {
        List<Node> nodes = document.selectNodes(xPath);
        String[] sReturn;
        if (nodes.size() <= 0)
            sReturn = dft;
        else
            sReturn = new String[nodes.size()];
        int i = 0;
        for (Node node : nodes) {
            sReturn[i++] = node.getText();
        }
        return sReturn;
    }

    /**
     * Please ensure the searchresult of the XPath is ONE node. Not found or null
     * or invalid number return the Dft.
     *
     * @param xPath String
     * @param Dft   int
     * @return int
     */
    public int getNodeText(String xPath, int Dft) {
        int iReturn = Dft;
        String sNodeText;
        Node node = document.selectSingleNode(xPath);
        if (node != null) {
            sNodeText = node.getText();
            if (sNodeText == null) {
                iReturn = Dft;
            } else {
                try {
                    iReturn = Integer.parseInt(sNodeText, 10);
                } catch (NumberFormatException e) {
                    iReturn = Dft;
                }
            }
        }
        return iReturn;
    }

    /**
     * Please ensure the searchresult of the XPath is ONE node. Not found or null
     * or invalid number return the Dft.
     *
     * @param xPath String
     * @param Dft   Integer
     * @return java.lang.Integer
     */
    public Integer getNodeText(String xPath, Integer Dft) {
        Integer iReturn = Dft;
        String sNodeText;
        Node node = document.selectSingleNode(xPath);
        if (node != null) {
            sNodeText = node.getText();
            if (sNodeText == null) {
                iReturn = Dft;
            } else {
                try {
                    iReturn = Integer.decode(sNodeText);
                } catch (NumberFormatException e) {
                    iReturn = Dft;
                }
            }
        }
        return iReturn;
    }

    /**
     * Please ensure the searchresult of the XPath is ONE node. Not found or null
     * or invalid number return the Dft.
     *
     * @param xPath String
     * @param Dft   double
     * @return double
     */
    public double getNodeText(String xPath, double Dft) {
        double dReturn = Dft;
        String sNodeText;
        Node node = document.selectSingleNode(xPath);
        if (node != null) {
            sNodeText = node.getText();
            if (sNodeText == null) {
                dReturn = Dft;
            } else {
                try {
                    dReturn = Double.parseDouble(sNodeText);
                } catch (NumberFormatException e) {
                    dReturn = Dft;
                }
            }
        }
        return dReturn;
    }

    /**
     * Get the Nodelist's size.
     *
     * @param xPath String
     * @return int 0 while not found
     */
    public int getNumOfNode(String xPath) {
        int iReturn = 0;
        List list = document.selectNodes(xPath);
        if (list != null) {
            iReturn = list.size();
        }
        return iReturn;
    }

    // -------------------------- INNER CLASSES --------------------------

    public static class XMLReply {

        /*
        <XMLReply>
            <FailExceptionInfo></FailExceptionInfo>
            <BusinessExceptionInfo></BusinessExceptionInfo>
            <SuccessMsg></SuccessMsg>
            <WarningMsg></WarningMsg>
            <AttachXML>XML Inside</AttachXML>
        </XMLReply>
        */

        private String FailExceptionInfo = "";
        private String BusinessExceptionInfo = "";
        private String SuccessMsg = "";
        private String WarningMsg = "";
        private String AttachXML = "";

        public static String getJScriptParser() {
            String sb;
            /**
             * xmlreply 是用于解释服务器端的XMLReply的.
             * 它拥有以下的几个属性:
             * failmsg      系统发生了比较严重的错误
             * business     业务出错信息,多数情况下是由于用户操作失误所导致
             * msg          成功信息
             * warn         成功但是有警告




             * attach       附带的数据信息(可能是XML)
             * 除了attach以外,剩下的四个在同一时间内,只有一个表达服务器端的信息.
             * 而attach多数情况下是一个XML. 主要存放一些操作后返回的数据.
             */
            /*
            function xmlreply(sXML) {
                var dom = new ActiveXObject("Microsoft.XMLDOM");
                var bLoadSuccess = dom.loadXML(sXML);
                if (!bLoadSuccess) {
                    alert("系统出错。请退出IE后，重新执行操作。");
                    return null;
                }
                var root = dom.documentElement;
                this.failmsg = "";
                this.business = "";
                this.msg = "";
                this.warn = "";
                if (root.selectSingleNode("FailExceptionInfo").text.length > 0) {
                    this.failmsg = root.selectSingleNode("FailExceptionInfo").text;
                } else if (root.selectSingleNode("BusinessExceptionInfo").text.length > 0) {
                    this.business = root.selectSingleNode("BusinessExceptionInfo").text;
                } else if (root.selectSingleNode("SuccessMsg").text.length > 0) {
                    this.msg = root.selectSingleNode("SuccessMsg").text;
                } else if (root.selectSingleNode("WarningMsg").text.length > 0) {
                    this.warn = root.selectSingleNode("WarningMsg").text;
                }
                if (root.selectSingleNode("AttachXML").text.length > 0) {
                    this.attach = root.selectSingleNode("AttachXML").xml;
                }
            }
            */

            sb = ("            /**\n" +
                    "             * xmlreply 是用于解释服务器端的XMLReply的.\n" +
                    "             * 它拥有以下的几个属性:\n" +
                    "             * failmsg      系统发生了比较严重的错误\n" +
                    "             * business     业务出错信息,多数情况下是由于用户操作失误所导致\n" +
                    "             * msg          成功信息\n" +
                    "             * warn         成功但是有警告\n" +
                    "             * attach       附带的数据信息(可能是XML字符串)\n" +
                    "             * 除了attach以外,剩下的四个在同一时间内,只有一个表达服务器端的信息.\n" +
                    "             * 而attach多数情况下是一个XML. 主要存放一些操作后返回的数据.\n" +
                    "             */\n" +
                    "            \n" +
                    "            function xmlreply(sXML) {\n" +
                    "                var dom = new ActiveXObject(\"Microsoft.XMLDOM\");\n" +
                    "                var bLoadSuccess = dom.loadXML(sXML);\n" +
                    "                if (!bLoadSuccess) {\n" +
                    "                    alert(\"系统出错。请退出IE后，重新执行操作。\");\n" +
                    "                    return null;\n" +
                    "                }\n" +
                    "                var root = dom.documentElement;\n" +
                    "                this.failmsg = \"\";\n" +
                    "                this.business = \"\";\n" +
                    "                this.msg = \"\";\n" +
                    "                this.warn = \"\";\n" +
                    "                this.attach = \"\";\n" +
                    "                if (root.selectSingleNode(\"FailExceptionInfo\").text.length > 0) {\n" +
                    "                    this.failmsg = root.selectSingleNode(\"FailExceptionInfo\").text;\n" +
                    "                } else if (root.selectSingleNode(\"BusinessExceptionInfo\").text.length > 0) {\n" +
                    "                    this.business = root.selectSingleNode(\"BusinessExceptionInfo\").text;\n" +
                    "                } else if (root.selectSingleNode(\"SuccessMsg\").text.length > 0) {\n" +
                    "                    this.msg = root.selectSingleNode(\"SuccessMsg\").text;\n" +
                    "                } else if (root.selectSingleNode(\"WarningMsg\").text.length > 0) {\n" +
                    "                    this.warn = root.selectSingleNode(\"WarningMsg\").text;\n" +
                    "                }\n" +
                    "                if (root.selectSingleNode(\"AttachXML\").text.length > 0) {\n" +
                    "                    this.attach = root.selectSingleNode(\"AttachXML\").xml;\n" +
                    "                }\n" +
                    "            }");
            return sb;
        }

        private XMLReply() {
        }

        public String toXML() {
            StringBuffer sb = new StringBuffer("<XMLReply>");
            sb.append(createNode("FailExceptionInfo", FailExceptionInfo));
            sb.append(createNode("BusinessExceptionInfo", BusinessExceptionInfo));
            sb.append(createNode("SuccessMsg", SuccessMsg));
            sb.append(createNode("WarningMsg", WarningMsg));
            sb.append("<AttachXML>").append(AttachXML).append("</AttachXML>");
            sb.append("</XMLReply>");
            return sb.toString();
        }

        public static XMLReply forSysFail() {
            XMLReply reply = new XMLReply();
            reply.FailExceptionInfo = "系统级错误, 请重新登录并尝试执行刚才的操作。";
            return reply;
        }

        public static XMLReply forSysFail(Throwable e) {
            XMLReply reply = new XMLReply();
            if (e == null) {
                syslog.error(" ****** Illegal Argument, parameter e can't be null. ****** ");
                reply.FailExceptionInfo = "系统出错。请退出IE后，重新执行操作。";
            } else {
                syslog.error("", e);
                reply.FailExceptionInfo = "系统级错误, 请重新登录并尝试执行刚才的操作。";
            }
            return reply;
        }

        public static XMLReply forSysFail(SysException e) {
            XMLReply reply = new XMLReply();
            if (e == null) {
                syslog.error(" ****** Illegal Argument, parameter e can't be null. ****** ");
                reply.FailExceptionInfo = "系统出错。请退出IE后，重新执行操作。";
            } else {
                reply.FailExceptionInfo = "系统级错误, 请重新登录并尝试执行刚才的操作。";
            }
            return reply;
        }

        public static XMLReply forFail(String msg) {
            XMLReply reply = new XMLReply();
            reply.BusinessExceptionInfo = msg;
            return reply;
        }

        public static XMLReply forFail(GenericBizException e) {
            XMLReply reply = new XMLReply();
            if (e == null) {
                syslog.error(" ****** Illegal Argument, parameter e can't be null. ****** ");
                reply.FailExceptionInfo = "系统出错。请退出IE后，重新执行操作。";
            } else {
                reply.BusinessExceptionInfo = e.getMessage();
            }
            return reply;
        }

        public static XMLReply forSuccess(String successMsg) {
            XMLReply reply = new XMLReply();
            if (successMsg != null) {
                reply.SuccessMsg = successMsg;
            } else {
                reply.SuccessMsg = "";
            }
            return reply;
        }

        public static XMLReply forSuccessButWarn(String warningMsg) {
            XMLReply reply = new XMLReply();
            if (warningMsg != null) {
                reply.WarningMsg = warningMsg;
            } else {
                reply.WarningMsg = "";
            }
            return reply;
        }

        public static void main(String[] args) {
            //System.out.println(isXMLValid("$12312313"));
            System.out.println(isXMLValid(""));
        }

        public void setAttachXML(String p_attachXML) {
            if (!isXMLValid("<a>" + p_attachXML + "</a>")) {
                syslog.error(" ****** Illegal Argument, parameter e can't be null. ****** ");
                this.FailExceptionInfo = "系统出错。请退出IE后，重新执行操作。";
            } else {
                AttachXML = p_attachXML;
            }
        }

        /**
         * @param key  - the XML node key value
         * @param text - the XML node text value
         * @return String - the XML node string
         */
        private String createNode(String key, String text) {
            StringBuffer node = new StringBuffer("\t\t\t");
            node.append("<").append(key).append(">");
            if (text != null) {
                node.append(XMLHelper.XMLEncode(text));
            } else {
                node.append("");
            }
            node.append("</").append(key).append(">\n");
            return node.toString();
        }
    }

    public static interface XMLDTO {

        public String getNodeAttribute(String nodename);

        public String getTagName();

        public String toXML();
    }

    public static String formatterXML(String inStr, String checkWord) {
        if (inStr.indexOf(checkWord) > 0) {
            String str = "";
            String startpart = inStr.substring(0, inStr.indexOf(checkWord) + 1);
            startpart = startpart.substring(0, startpart.lastIndexOf("<"));
            System.out.println("--1--" + startpart);
            String endpart = inStr.substring(inStr.indexOf(checkWord) + 1, inStr.length());
            System.out.println("--2--" + endpart);
            endpart = endpart.substring(endpart.indexOf(">"), endpart.length());
            System.out.println("--3--" + endpart);
            str = startpart + endpart;
            System.out.println("--4--" + str);
            endpart = endpart.substring(inStr.indexOf(checkWord), endpart.indexOf(inStr, inStr.indexOf("<")));
            return formatterXML(inStr, checkWord);
        }
        return inStr;
    }

    public static void main(String[] args) {
        String str = "<ab></ab><adfsff1$111dddd>" + "me" + "</1111dddd><adbbb></abbbb>";
        System.out.println(" formatter >>>>" + str.indexOf("$"));
        System.out.println(" formatter >>>>" + XMLHelper.formatterXML(str, "$"));
    }
}

