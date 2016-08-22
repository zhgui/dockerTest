package com.shark.common.query;


import com.shark.common.entity.search.SearchOperator;
import com.shark.common.entity.search.exception.InvlidSearchOperatorException;
import com.shark.common.exception.FatalBizException;
import com.shark.common.exception.SysException;
import com.shark.common.utils.*;
import com.shark.common.utils.formatter.DateFormatter;
import com.shark.common.utils.formatter.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

/**
 * User: Tony
 * Date: 11-9-3
 * Time: 下午4:51
 */
public class SearchConditionsBak {

    public final static char FLG_DELIMITER = '_';
    /**
     * 默认GROUP的名字
     */
    public static final String DFT_GROUP = "[DEFAULT]";

    public static final Logger logger = LoggerFactory.getLogger(SearchConditionsBak.class);

    private final static String PARAMNAME_CURPAGENO = "page";
    private final static String PARAMNAME_PAGESIZE = "rows";

    protected Map<String, Object> parmMap;
    protected int pageSize = DFT_PAGE_SIZE;
    protected int pageNO;

    protected Map<String, String> groupSQL = new HashMap<String, String>();
    protected Map<String, String> groupSQL_Map = new HashMap<String, String>();
    /**
     * <pre>
     * 这个类是用来继续加工搜索结果的
     *
     * 可有可无, 一般是比较复杂的需求才会用到
     *
     * </pre>
     */
    protected ExtraWorker[] extraWorker = null;
    protected Class classOfResult;
    protected String sql_Bone = "";
    protected String sql_fields = "";

    public String getSql_index() {
        return sql_index;
    }

    public void setSql_index(String sql_index) {
        this.sql_index = sql_index;
    }

    protected String sql_index = "";
    private static final String[] EMPTY_STRINGARRAY = new String[0];
    //private static final LinkedList EMPTY_TIMESTAMPARRAY = new LinkedList();
    protected String[] subSumColumns = EMPTY_STRINGARRAY;
    protected String[] sumColumns = EMPTY_STRINGARRAY;
    protected String[] allGroupNames = EMPTY_STRINGARRAY;
    protected boolean countAll = true;
    protected String scNewSQL = "";
    private static final int DFT_PAGE_SIZE = 10;

    protected LinkedList<Timestamp> timestampParms = new LinkedList<Timestamp>();

    // --------------------------- CONSTRUCTORS ---------------------------

    protected SearchConditionsBak() {
    }

    /**
     * 收集Map里作为条件且有值的信息。
     *
     * @param p_parmMap
     * @return
     */
    public static Map getCondParmMap(Map p_parmMap) {
        HashMap toReturn = new HashMap(p_parmMap);
        Set<String> keys = toReturn.keySet();
        ArrayList<String> keystormv = new ArrayList<String>();
        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext(); ) {
            String longParamName = iterator.next();
            Object value = p_parmMap.get(longParamName);
            if (value instanceof String) {
                if (!isEffectParm(longParamName, (String) value)) {
                    keystormv.add(longParamName);
                }
            } else {
                keystormv.add(longParamName);
            }
        }
        for (Iterator it = keystormv.iterator(); it.hasNext(); ) {
            String keytormv = (String) it.next();
            toReturn.remove(keytormv);
        }
        keystormv.clear();
        return toReturn;
    }

    protected SearchConditionsBak(Map p_parmMap) throws FatalBizException {
        HashMap<String, StringBuffer> groupBuff = new HashMap<String, StringBuffer>();
        parmMap = new HashMap<String, Object>(p_parmMap);

        pageSize = Parser.parseValue(String.valueOf(parmMap.get(PARAMNAME_PAGESIZE)), DFT_PAGE_SIZE);
        if (pageSize < 1) pageSize = DFT_PAGE_SIZE;
        //logger.debug(" * pageSize='" + pageSize + "'  * ");
        pageNO = Parser.parseValue(String.valueOf(parmMap.get(PARAMNAME_CURPAGENO)), 1);
        if (pageNO < 1) pageNO = 1;
        /****** function block begin (By Stone For Parser Conditions) *****/
        Set<String> keys = parmMap.keySet();
        ArrayList<String> keystormv = new ArrayList<String>();
        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext(); ) {
            String longParamName = iterator.next();
            Object value = parmMap.get(longParamName);
            if (!(value instanceof Serializable)) {
                keystormv.add(longParamName);
            }
        }
        for (Iterator it = keystormv.iterator(); it.hasNext(); ) {
            String keytormv = (String) it.next();
            parmMap.remove(keytormv);
        }
        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext(); ) {
            String longParamName = iterator.next();
            Object value = parmMap.get(longParamName);
            if (!(value instanceof String)) continue;
            String paramValue = (String) value;

            //SearchOperator.valueOf(searchs[1])
//            try {
//                operator = SearchOperator.valueOf(searchs[1]);
//            } catch (IllegalArgumentException e) {
//                throw new InvlidSearchOperatorException(searchProperty, searchs[1]);
//            }
            int numOfDelimiter = StringUtils.countCharInString(longParamName, FLG_DELIMITER);
            paramValue = paramValue.trim();
            if (isEffectParm(longParamName, paramValue)) {
                int index = longParamName.lastIndexOf(FLG_DELIMITER);//
                String paramName = longParamName;
                String tableName = "";
                String groupName = DFT_GROUP;
                if (numOfDelimiter > 2) {
                    int secondDelimiterIdx = StringUtils.indexOf(longParamName, FLG_DELIMITER, 2);
                    paramName = longParamName.substring(0, secondDelimiterIdx);
                    int thirdDelimiterIdx = StringUtils.indexOf(longParamName, FLG_DELIMITER, 3);
                    SysException.throwWhenFalse(thirdDelimiterIdx > 0, "页面某搜索字段的name字段不正确，longParamName = '" + longParamName + "' 应该为1个或3个分隔符");
                    tableName = longParamName.substring(secondDelimiterIdx + 1, thirdDelimiterIdx);
                    if (tableName.length() > 0) tableName = tableName + ".";
                    groupName = longParamName.substring(thirdDelimiterIdx + 1);
                }

                String[] params = parseName(paramName, index);//0 for field name, 1 for field type: like, less, greater, equal, equal string
                params[0] = tableName + params[0];
                StringBuffer buf = getBuffByGroup(groupBuff, groupName);
                if (buf.length() > 0) {
                    buf.append(" and ");
                }
                translateCondUnit(params, buf, paramValue);
            }
        }

        keys = groupBuff.keySet();
        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            StringBuffer sb = groupBuff.get(key);
            groupSQL.put(key, sb.toString());
        }
        groupBuff.clear();
        /******* function block end ******/
    }

    private static boolean isEffectParm(final String p_longParamName, final String p_paramValue) {
        int numOfDelimiter = StringUtils.countCharInString(p_longParamName, FLG_DELIMITER);
        return numOfDelimiter > 0 && p_paramValue != null && p_paramValue.length() > 0 && !"NULL".equals(p_paramValue);
    }

    protected SearchConditionsBak(Map p_parmMap, String SQL_Bone, String[] sumColumns, String[] subSumColumns,
                                  String sql_fields) throws FatalBizException {
        this(p_parmMap);
        SysException.throwWhenNull(SQL_Bone, "参数有误：SQL_Bone = '" + SQL_Bone + "' ");
        SysException.throwWhenNull(sql_fields, "参数有误：sql_fields = '" + sql_fields + "' ");
        this.sql_Bone = SQL_Bone;
        this.sql_fields = sql_fields;
        if (subSumColumns != null) {
            this.subSumColumns = subSumColumns;
        }
        if (sumColumns != null) {
            this.sumColumns = sumColumns;
        }
    }

    /**
     * 利用GenericSearchDAO来产生相应的搜索结果时候使用的构造函数。
     * 有GROUP的情况请使用这个
     *
     * @param p_parmMap
     * @param p_groupSQL_Map
     * @param p_sql_Bone      sample : "from tableA where " or "from tableA where abc = '111' "
     * @param p_sql_fields
     * @param p_subSumColumns
     * @param p_sumColumns
     * @param p_allGroupNames
     */
    protected SearchConditionsBak(Map<String, String> p_parmMap, Map<String, String> p_groupSQL_Map,
                                  String p_sql_Bone, String p_sql_fields, String[] p_subSumColumns, String[] p_sumColumns,
                                  String[] p_allGroupNames) throws FatalBizException {
        this(p_parmMap, p_sql_Bone, p_sumColumns, p_subSumColumns, p_sql_fields);
        if (p_groupSQL_Map != null) groupSQL_Map = p_groupSQL_Map;
        if (p_allGroupNames != null) allGroupNames = p_allGroupNames;
    }

    // -------------------------- STATIC METHODS --------------------------

    /**
     * <pre>
     * 如果程序仅仅希望SearchConditions帮忙根据页面生成相应的条件SQL，那么使用这个构造函数。
     * 生成了SearchConditions的实例后，通过getWhereSQL方法来获得想要的条件语句。
     * </pre>
     *
     * @param p_parmMap
     * @return SearchConditions
     */
    public static SearchConditionsBak forGenSQLOnly(Map p_parmMap) throws FatalBizException {
        return new SearchConditionsBak(p_parmMap);
    }

    /**
     * <pre>
     * 利用GenericSearchDAO来产生相应的搜索结果时候使用的构造函数。
     * 有GROUP的情况请使用这个。
     * </pre>
     *
     * @param p_parmMap
     * @param p_sql_Bone      sample : "from tableA" or "from tableA where abc = '111' " or "from tableA where abc = '111' [DEFAULT] ORDER BY XXX"
     * @param p_subSumColumns
     * @param p_sumColumns
     * @param p_sql_fields    sample : "t1.UNITID, t1.UNITNAME, t2.USERNAME, t2.USERID, t3.RECEIPTTYPENO as RTNO"
     * @param p_groupSQL_Map
     * @param p_allGroupNames
     * @return SearchConditions
     * @see #DFT_GROUP
     */
    public static SearchConditionsBak forGroupSearch
    (Map p_parmMap, String p_sql_Bone, String[] p_sumColumns, String[] p_subSumColumns, String p_sql_fields,
     Map<String, String> p_groupSQL_Map, String[] p_allGroupNames)
            throws FatalBizException {
        return new SearchConditionsBak(p_parmMap, p_groupSQL_Map, p_sql_Bone, p_sql_fields,
                p_subSumColumns, p_sumColumns, p_allGroupNames);
    }

    /**
     * <pre>
     * 利用GenericSearchDAO来产生相应的搜索结果时候使用的构造函数。
     * 没有GROUP的情况请使用这个
     * </pre>
     *
     * @param p_parmMap
     * @param SQL_Bone      sample : "from tableA" or "from tableA where [DEFAULT]" or "from tableA where abc = '111' " or "from tableA where abc = '111' [DEFAULT] ORDER BY XXX"
     * @param sumColumns
     * @param subSumColumns
     * @param sql_fields    sample : "t1.UNITID, t1.UNITNAME, t2.USERNAME, t2.USERID, t3.RECEIPTTYPENO as RTNO"
     * @return SearchConditions
     * @see #DFT_GROUP
     */
    public static SearchConditionsBak forNoGroupSearch(Map p_parmMap, String SQL_Bone, String[] sumColumns, String[] subSumColumns, String sql_fields)
            throws FatalBizException {
        return new SearchConditionsBak(p_parmMap, SQL_Bone, sumColumns, subSumColumns, sql_fields);
    }

    protected static String[] parseName(String str, int index) {
        String[] params = new String[2];
        params[0] = str.substring(0, index);
        params[1] = str.substring(index + 1);
        return params;
    }

    // ------------------------ EXPOSE METHODS ------------------------

    public String getCompositeSQLBone() {
        String strConstructing = this.sql_Bone;
        Set<String> DSQLGroup_keys = groupSQL_Map.keySet();
        if (groupSQL.get(DFT_GROUP) == null) groupSQL.put(DFT_GROUP, "");
        for (int i = 0; i < allGroupNames.length; i++) {
            String groupName = allGroupNames[i];
            if (groupSQL.get(groupName) == null) groupSQL.put(groupName, "");
        }


        for (Iterator<String> iterator = DSQLGroup_keys.iterator(); iterator.hasNext(); ) {
            String groupName = iterator.next();
            int groupIdx = strConstructing.indexOf(groupName);
            if (groupIdx < 0) {
                logger.warn(" ** 搜索代码可能有问题，groupSQL_Map里面的一个groupName='" + groupName + "' 在SQL_Bone里找不到对应的位置 strConstructing = '" + strConstructing + "'  ** ");
                continue;
            }
            String condsql = groupSQL.get(groupName);
            if (condsql != null && condsql.length() > 0) {
                String DGroupSQL = groupSQL_Map.get(groupName);
                strConstructing = StringUtils.replace(strConstructing, groupName, DGroupSQL);
            }
        }
        Set<String> DSQLCond_keys = groupSQL.keySet();
        for (Iterator<String> iterator = DSQLCond_keys.iterator(); iterator.hasNext(); ) {
            String groupName = iterator.next();
            int groupIdx = strConstructing.indexOf(groupName);
            if (groupIdx < 0) {
                logger.warn(" ** 搜索代码可能有问题，groupSQL里面的一个groupName='" + groupName + "' 在SQL_Bone里找不到对应的位置 strConstructing = '" + strConstructing + "'  ** ");
                continue;
            }
            String condsql = StringUtils.trimString(groupSQL.get(groupName), "");
            if (condsql.length() > 0) condsql = "and " + condsql;
            strConstructing = StringUtils.replace(strConstructing, groupName, condsql);
        }
        /****** r 简化no group时, 所有条件都是动态的情况[这种情况由于所有条件都动态, 所以可能没有条件, 没有条件的时候, 就通过这个块里面的代码来删除无效的WHERE]) *****/
        strConstructing = strConstructing.trim();
        if (strConstructing.toUpperCase().endsWith("WHERE")) {
            strConstructing = strConstructing.substring(0, strConstructing.length() - 5).trim();
        }
        strConstructing = strConstructing.replaceAll("[wW][hH][eE][rR][eE][ ]+[gG][rR][oO][uU][pP][ ]+[bB][yY]", "GROUP BY");//replace "where  and" to "where "
        strConstructing = strConstructing.replaceAll("[wW][hH][eE][rR][eE][ ]+[oO][rR][dD][eE][rR][ ]+[bB][yY]", "ORDER BY");//replace "where  and" to "where "
        /******* function block end ******/
        //logger.debug(" * strConstructing='" + strConstructing + "'  * ");
        strConstructing = strConstructing.replaceAll("([aA][nN][dD][ ]+){2,}", "and ");//replace "where  and" to "where "
        //logger.debug(" * strConstructing='" + strConstructing + "'  * ");
        strConstructing = strConstructing.replaceAll("[wW][hH][eE][rR][eE][ ]+[aA][nN][dD]", "WHERE");//replace "where  and" to "where "
        //logger.debug(" * strConstructing='" + strConstructing + "'  * ");
        return strConstructing.trim();
    }

    /**
     * <pre>
     * 取出默认Group的where sql组合。（就是没有Group的sql）
     * </pre>
     *
     * @return 条件sql，形式为：<b>fieldA == '' and fieldB > 12 ………… </b>
     */
    public String getWhereSQL() {
        String sql = groupSQL.get(DFT_GROUP);
        return (sql == null) ? "" : sql;
    }

    /**
     * <pre>
     * 取出指定Group的where sql组合。
     * </pre>
     *
     * @param p_GroupName
     * @return 条件sql，形式为：<b>fieldA == '' and fieldB > 12 ………… </b>
     */
    public String getWhereSQL(String p_GroupName) {
        String sql = groupSQL.get(p_GroupName);
        return (sql == null) ? "" : sql;
    }


    public String toXML() {
        //System.out.println("SearchConditions.toXML");
        StringBuffer buf = new StringBuffer();
        Set keys = parmMap.keySet();
        for (Object key : keys) {
            String paramName = (String) key;
            buf.append(XMLHelper.createXmlNodeNoShift(paramName, parmMap.get(paramName)));
        }
        String scurDate = DateFormatter.long2YYYYcnMMcnDDcn(new Date(System.currentTimeMillis()));
        buf.append(XMLHelper.createXmlNodeNoShift("today", scurDate));
        return buf.toString();
    }

    // --------------------- GETTER / SETTER METHODS ---------------------

    public Class getClassOfResult() {
        return classOfResult;
    }

    public void setClassOfResult(Class p_classOfResult) {
        classOfResult = p_classOfResult;
    }

    /**
     * <pre>
     * 这个类是用来继续加工搜索结果的
     * 可有可无, 一般是比较复杂的需求才会用到
     * </pre>
     *
     * @return ExtraWorker instance
     */
    public ExtraWorker[] getExtraWorker() {
        return extraWorker;
    }

    /**
     * <pre>
     * 这个类是用来继续加工搜索结果的
     * 可有可无, 一般是比较复杂的需求才会用到
     * </pre>
     *
     * @param p_extraWorker ExtraWorker instance
     */
    public void setExtraWorker(ExtraWorker p_extraWorker) {
        extraWorker = new ExtraWorker[]{p_extraWorker};
    }

    /**
     * <pre>
     * 这个类是用来继续加工搜索结果的
     * 可有可无, 一般是比较复杂的需求才会用到
     * 这里传入的是worker的数组，worker的执行顺序是按照数组的排列顺序来执行的。
     * </pre>
     *
     * @param p_extraWorkers ExtraWorker instances
     */
    public void setExtraWorker(ExtraWorker[] p_extraWorkers) {
        extraWorker = p_extraWorkers;
    }

    public int getPageNO() {
        return pageNO;
    }

    public void setPageNO(int p_pageNO) {
        pageNO = p_pageNO;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getSql_fields() {
        return sql_fields;
    }

    public String[] getSubSumColumns() {
        return subSumColumns;
    }

    public String[] getSumColumns() {
        return sumColumns;
    }

    // -------------------------- OTHER METHODS --------------------------

    private StringBuffer getBuffByGroup(HashMap<String, StringBuffer> groupBuff, String p_groupName) {
        StringBuffer sbRet = groupBuff.get(p_groupName);
        if (sbRet == null) {
            sbRet = new StringBuffer();
            groupBuff.put(p_groupName, sbRet);
        }
        return sbRet;
    }

    private void translateCondUnit(String[] p_params, StringBuffer p_buf, String p_paramValue) {
        //int type = Integer.parseInt(p_params[1]);
        Timestamp ts = null;
        SearchOperator operator;
        try {
            operator = SearchOperator.valueOf(p_params[1]);
        } catch (IllegalArgumentException e) {
            throw new InvlidSearchOperatorException(p_paramValue, p_params[1]);
        }
        switch (operator) {
            case eq:
                //判断是否
                p_buf.append(p_params[0]).append(" = '").append(p_paramValue).append("'");
                break;
            case lt:
                p_buf.append(p_params[0]).append(" < '").append(p_paramValue).append("'");
                break;
            case gt:
                p_buf.append(p_params[0]).append(" > '").append(p_paramValue).append("'");
                break;
            case lte:
                ts = judgeEndTimestamp(p_paramValue, ts);
                if (ts != null) {//日期格式
                    p_buf.append(p_params[0]).append(" <=? ");
                    timestampParms.add(ts);
                } else {
                    p_buf.append(p_params[0]).append(" <= '").append(p_paramValue).append("'");
                }
                break;
            case gte:
                ts = judgeStartTimestamp(p_paramValue, ts);
                if (ts != null) {//日期格式
                    p_buf.append(p_params[0]).append(" >=? ");
                    timestampParms.add(ts);
                } else {
                    p_buf.append(p_params[0]).append(" >= '").append(p_paramValue).append("'");
                }
                break;
            case like:
                p_buf.append(p_params[0]).append(" like '%").append(p_paramValue).append("%'");
                break;
            case notLike:
                p_buf.append(p_params[0]).append(" like '%").append(p_paramValue).append("%'");
                break;
            case prefixLike:
                p_buf.append(p_params[0]).append(" like '%").append(p_paramValue).append("'");
                break;
            case prefixNotLike:
                p_buf.append(p_params[0]).append(" not like '%").append(p_paramValue).append("'");
                break;
            case suffixLike:
                p_buf.append(p_params[0]).append(" like '").append(p_paramValue).append("%'");
                break;
            case suffixNotLike:
                p_buf.append(p_params[0]).append(" not like '").append(p_paramValue).append("%'");
                break;
            case ne:
                p_buf.append(p_params[0]).append(" != '").append(p_paramValue).append("'");
                break;
            case in:
                p_buf.append(p_params[0]).append(" in ").append(p_paramValue);
                break;
            case notIn:
                p_buf.append(p_params[0]).append(" not in ").append(p_paramValue);
                break;
            case isNull:
                p_buf.append(p_params[0]).append(" is null ");
                break;
            case isNotNull:
                p_buf.append(p_params[0]).append(" is not null ");
                break;
//            case TYP_SRH_IN_SELECT:
//                p_buf.append(p_params[0]).append(" in (").append(p_paramValue).append(")");
//                break;
//            case TYP_SRH_LESS_EQUAL_DT:
//                p_buf.append(p_params[0]).append("<=?");
//                ts = new Timestamp(TimeUtils.getLastMillsecondOfDay(DateFormatter.yyyy_MM_DD2Long(p_paramValue)));
//                timestampParms.add(ts);
//                break;
//            case TYP_SRH_GREATER_EQUAL_DT:
//                p_buf.append(p_params[0]).append(">=?");
//                ts = new Timestamp(TimeUtils.getFirstMillsecondOfDay(DateFormatter.yyyy_MM_DD2Long(p_paramValue)));
//                timestampParms.add(ts);
//                break;
//            case TYP_SRH_EQUAL_DT:
//                long beginTM = TimeUtils.getFirstMillsecondOfDay(DateFormatter.yyyycnMMcnDDcn2Long(p_paramValue));
//                long endTM = TimeUtils.getLastMillsecondOfDay(DateFormatter.yyyycnMMcnDDcn2Long(p_paramValue));
//                p_buf.append(p_params[0]).append(" between ? and ?");
//                timestampParms.add(new Timestamp(beginTM));
//                timestampParms.add(new Timestamp(endTM));
//                break;
//            case TYP_SRH_LESS_EQUAL_DT_RPT:
//                p_buf.append(p_params[0]).append("<=").append(DateFormatter.yyyycnMMcnDDcn2Long(p_paramValue)).append("");//note, cannot use equal.
//                break;
//            case TYP_SRH_GREATER_EQUAL_DT_RPT:
//                p_buf.append(p_params[0]).append(">=").append(DateFormatter.yyyycnMMcnDDcn2Long(p_paramValue)).append("");
//                break;
//            case TYP_SRH_LESS_EQUAL_FLOAT:
//                p_buf.append(p_params[0]).append("<=").append(p_paramValue).append("");
//                break;
//            case TYP_SRH_GREATER_EQUAL_FLOAT: {
//                p_buf.append(p_params[0]).append(">=").append(p_paramValue).append("");
//                break;
//            }
            default:
                throw new SysException("Invalid search type:" + p_params[1]);
        }
    }

    private Timestamp judgeStartTimestamp(String p_paramValue, Timestamp ts) {
        try {
            ts = new Timestamp(DateFormatter.yyyy_MM_DD2Long(p_paramValue));
        } catch (Exception e) {
        }
        if (ts == null) {
            try {
                ts = new Timestamp(DateFormatter.yyyycnMMcnDDcn2Long(p_paramValue));
            } catch (Exception e1) {
            }
        }
        return ts;
    }

    private Timestamp judgeEndTimestamp(String p_paramValue, Timestamp ts) {
        try {
            ts = new Timestamp(TimeUtils.getLastMillsecondOfDay(DateFormatter.yyyy_MM_DD2Long(p_paramValue)));
        } catch (Exception e) {
        }
        if (ts == null) {
            try {
                ts = new Timestamp(TimeUtils.getLastMillsecondOfDay(DateFormatter.yyyycnMMcnDDcn2Long(p_paramValue)));
            } catch (Exception e1) {
            }
        }
        return ts;
    }


    protected Timestamp judgeTimestamp(String value) {
        Timestamp ts = null;
        try {
            ts = new Timestamp(TimeUtils.getLastMillsecondOfDay(DateFormatter.yyyy_MM_DD2Long(value)));
        } catch (Exception e) {

        }
        try {
            ts = new Timestamp(TimeUtils.getLastMillsecondOfDay(DateFormatter.yyyy_MM_DD2Long(value)));
        } catch (Exception e) {

        }
        try {
            ts = new Timestamp(TimeUtils.getLastMillsecondOfDay(DateFormatter.yyyy_MM_DD2Long(value)));
        } catch (Exception e) {

        }
        try {
            ts = new Timestamp(TimeUtils.getLastMillsecondOfDay(DateFormatter.yyyy_MM_DD2Long(value)));
        } catch (Exception e) {

        }
        return ts;
    }

    public LinkedList<Timestamp> getTimestampParms() {
        return timestampParms;
    }

    public void setPageSize(int p_pageSize) {
        pageSize = p_pageSize;
    }

    public void isCountAll(boolean p_countAll) {
        this.countAll = p_countAll;
    }

    public boolean getCountAll() {
        return this.countAll;
    }

    public String getScNewSQL() {
        return scNewSQL;
    }

    public void setScNewSQL(String scNewSQL) {
        this.scNewSQL = scNewSQL;
    }

    protected void finalize() throws Throwable {
        this.groupSQL.clear();
        this.groupSQL_Map.clear();
        this.parmMap.clear();
        this.timestampParms.clear();
        super.finalize();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public Map<String, Object> getParmMap() {
        return parmMap;
    }
}
