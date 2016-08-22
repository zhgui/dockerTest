package com.shark.common.query;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.orm.jpa.SharedEntityManagerCreator;
import org.springframework.stereotype.Repository;

import com.shark.common.entity.search.Searchable;
import com.shark.common.repository.hibernate.HibernateUtils;
import com.shark.common.utils.Arith;
import com.shark.common.utils.Parser;
import com.shark.common.utils.ReflectUtils;
import com.shark.common.utils.formatter.DateFormatter;
import com.shark.common.utils.formatter.NumberFormatter;

/**
 * User: Tony
 * Date: 11-9-3
 * Time: 下午5:08
 */
@Repository
public class HibernateSearchDaoImpl implements BaseSearchDao {

    private EntityManager entityManager;

    protected Logger logger = LoggerFactory.getLogger(getClass());


    public ReportPage searchPage(SearchConditions sc) {
        String sql_index = sc.getSql_index();
        String sql_part2 = sc.getCompositeSQLBone();// "FROM TABLE WHERE ...................."
        boolean isCount = sc.getCountAll();
        String scNewSQL = sc.getScNewSQL();
        String sql_part1_forselect;
        if (sql_index != null && !sql_index.equals("")) {
            sql_part1_forselect = "SELECT " + sql_index + " " + sc.getSql_fields() + " ";
        } else {
            sql_part1_forselect = "SELECT " + sc.getSql_fields() + " ";
        }
        String sql_part1_forSumAndCount;
        boolean hasGroupBy = sql_part2.toUpperCase().indexOf(" GROUP BY ") > 0;
        String sql_countHeadForGroupBy = "";
        String[] sumColumns = sc.getSumColumns();
        if (hasGroupBy) {
            if (sql_index != null && !sql_index.equals("")) {
                sql_countHeadForGroupBy = "SELECT " + sql_index + " COUNT(*) ";
                sql_part1_forSumAndCount = "SELECT " + sql_index + " COUNT(*) ";
                //sql_part1_forSumAndCount = "SELECT COUNT(COUNT(*))";
            } else {
                sql_countHeadForGroupBy = "SELECT COUNT(*) ";
                sql_part1_forSumAndCount = "SELECT COUNT(*) ";
                //sql_part1_forSumAndCount = "SELECT COUNT(COUNT(*))";
            }
        } else {
            if (sql_index != null && !sql_index.equals("")) {
                sql_part1_forSumAndCount = "SELECT " + sql_index + " COUNT(*)";
            } else {
                sql_part1_forSumAndCount = "SELECT COUNT(*) ";
            }
        }
        for (int i = 0; i < sumColumns.length; i++) {
            String sumColumn = sumColumns[i];

            if (hasGroupBy) {
                sql_countHeadForGroupBy = sql_countHeadForGroupBy + ",SUM(SUM" + i + ") as SUM" + i + " ";
                sql_part1_forSumAndCount = sql_part1_forSumAndCount + ",SUM(" + sumColumn + ") as SUM" + i + " ";
            } else {
                sql_part1_forSumAndCount = sql_part1_forSumAndCount + ",SUM(" + sumColumn + ") as SUM" + i + " ";
            }

        }

        //if (hasGroupBy) {
        //    sql_part1_forSumAndCount = " FROM (" + sql_part1_forSumAndCount + ")";
        //}

        int indexOfOrderBY = sql_part2.toUpperCase().lastIndexOf("ORDER BY");
        String sql_part2_no_orderby = sql_part2;
        if (indexOfOrderBY > -1) {
            sql_part2_no_orderby = sql_part2.substring(0, indexOfOrderBY);
        }
        String sql_sum = "SELECT 0 FROM DUAL";
        if (isCount) {
            if (hasGroupBy) {
                sql_sum = sql_part1_forSumAndCount + " FROM  (SELECT  *  " + sql_part2 + ") temp";
            } else {
                sql_sum = sql_part1_forSumAndCount + sql_part2_no_orderby;
            }
        } else {
            String new_sql_part = sql_part2.substring(sql_part2.indexOf("WHERE") + 5, sql_part2.lastIndexOf(")") + 1);
            sql_sum = scNewSQL + " WHERE " + new_sql_part;
        }
        String sql_slt = sql_part1_forselect + sql_part2;
        // 如果有 DISTINCT 的情况，则查询内容做统计数量。
        if (isCount && sql_part1_forselect != null && sql_part1_forselect.toUpperCase().indexOf("DISTINCT") > 0) {
            sql_sum = "SELECT COUNT(1) FROM (" + sql_slt + ")";
        }
        LinkedList<Timestamp> timestampParms = sc.getTimestampParms();
        Object sumResult = new Object();

        // SQLQuery query1 = getSession().createSQLQuery(sql_sum);
        Query query1 = entityManager.createNativeQuery(sql_sum);
//        sc.getSearchCallback().setValues(query1, sc.getSearchable());
        //      sc.getSearchCallback().setPageable(query1, sc.getSearchable());

        for (int i = 0; i < timestampParms.size(); i++) {
            Timestamp timestamp = timestampParms.get(i);
            query1.setParameter(i, timestamp);
        }

        logger.info(" * SQL : SELECTSQL = " + sql_slt + " * " + dump(timestampParms));
        logger.info(" * SQL : SUMSQL = " + sql_sum + " * " + dump(timestampParms));
        sumResult = query1.getSingleResult();

        long rowcount = 0;
        double[] sums = new double[sumColumns.length];
        if (sumColumns.length > 0) {
            Object[] sumRslts = ((Object[]) sumResult);
            if (sumRslts[0] != null) {
                rowcount = Parser.parseValue(sumRslts[0].toString(), 0L);
            } else {
                rowcount = 0;
            }
            for (int i = 0; i < sums.length; i++) {
                if (sumRslts[1 + i] == null) sums[i] = 0;
                else sums[i] = Parser.parseValue(sumRslts[1 + i].toString(), 0.00);
            }
        } else {
            if (isCount) {
                rowcount = Parser.parseValue(((Object) sumResult).toString(), 0L);
            }
        }

        //System.out.println("rowcount = " + rowcount);
        //DebugUtils.dumpBean(sc);

        int curRow = (sc.getPageNO() - 1) * sc.getPageSize();
        if (curRow > rowcount) {
            curRow = 0;
            sc.setPageNO(1);
        }

        if (rowcount > 0 || !isCount) {
            List lastResult = new LinkedList();
            Query query2 = entityManager.createNativeQuery(sql_slt);

            for (int i = 0; i < timestampParms.size(); i++) {
                Timestamp timestamp = timestampParms.get(i);
                query2.setParameter(i, timestamp);
            }
            List resultOf1stStep = query2.unwrap(SQLQuery.class).setFirstResult(curRow).setMaxResults(sc.getPageSize()).
                    setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
//             .setResultTransformer(Transformers.aliasToBean(CatDTO.class))
//            setResultTransformer(new AliasToBeanResultTransformerIgnorCase(sc.getClassOfResult())).list();
            if (sc.getExtraWorker() != null && sc.getExtraWorker().length > 0) {
                ExtraWorker[] extraWorkers = sc.getExtraWorker();
                Collection newlist = null, oldlist = resultOf1stStep;
                for (ExtraWorker extraWorker : extraWorkers) {
                    newlist = extraWorker.process(oldlist);
                    if (newlist != oldlist) {
                        oldlist.clear();
                        oldlist = newlist;
                    }
                }
                lastResult.addAll(newlist);
                newlist.clear();
                oldlist.clear();
                resultOf1stStep.clear();
            } else {
                lastResult.addAll(resultOf1stStep);
                resultOf1stStep.clear();
            }

            String[] p_sumColumns = sc.getSubSumColumns();
            double[] p_subSumDatas = new double[p_sumColumns.length];

            for (Object objBean : lastResult) {
                for (int i = 0; i < p_sumColumns.length; i++) {
                    String p_sumColumn = p_sumColumns[i];
                    double singleValue = Parser.parseValue((String.valueOf(ReflectUtils.getPropertyByName(objBean, p_sumColumn, true))), 0.00, ",");
                    p_subSumDatas[i] = Arith.add(singleValue, p_subSumDatas[i]);
                }
            }


            ReportPage pi = ReportPage.newInstance(rowcount, lastResult, sc.getPageSize(), sc.getPageNO());
            //  new PageImpl(lastResult, sc.getSearchable().getPage(), rowcount);
            pi.setCond(sc);
            for (int i = 0; i < sums.length; i++) {
                pi.setAttribute(sumColumns[i], String.format("%.2f", sums[i]));
            }
            for (int i = 0; i < p_subSumDatas.length; i++) {
                pi.setAttribute("SUBSUM" + i, NumberFormatter.number2MMMcommaMMMdotOO(p_subSumDatas[i]));
            }
            return pi;
        } else {
            ReportPage pi = ReportPage.newInstance(0, new ArrayList(0), sc.getPageSize(), sc.getPageNO());
            pi.setCond(sc);
            for (int i = 0; i < sums.length; i++) {
                pi.setAttribute("SUM" + i, "0.00");
            }
            for (int i = 0; i < sc.getSubSumColumns().length; i++) {
                pi.setAttribute("SUBSUM" + i, "0.00");
            }
            return pi;
        }
    }

    @Override
    public Page searchPage(Searchable searchable) {

        return null;
    }


    private String dump(LinkedList<Timestamp> timestampParms) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < timestampParms.size(); i++) {
            sb.append(i).append(" : ").append(DateFormatter.long2YYYY_MM_DDHH24miss(new Date(timestampParms.get(i).getTime()))).append(" ; ");
        }
        if (!sb.toString().equals("")) {
            sb.append(" ]").insert(0, " Dump Paras [ ");
        }
        return sb.toString();
    }

    protected Session getSession() {
        return HibernateUtils.getSession(entityManager);
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        entityManager = SharedEntityManagerCreator.createSharedEntityManager(entityManagerFactory);
    }
}
