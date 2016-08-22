package com.shark.pcf.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.shark.pcf.entity.PcfDepartmentIncAth;

/**
 * Created by win7 on 2014/12/18.
 */
public class PcfDepartmentAthRepositoryImpl {
    @PersistenceContext
    private EntityManager em;

    public List getDepartmentTreeData() {
        StringBuffer getDepartmentTreeDataSql = new StringBuffer();
        getDepartmentTreeDataSql
            .append(" SELECT ")
            .append("     C.DEPARTMENT_CD, ")
            .append("     C.DEPARTMENT_NAME, ")
            // .append("     C.DEPARTMENT_ID, ")
            .append("     D.DEPARTMENT_ID, ")
            .append("     D.PARENT_DEPARTMENT_ID, ")
            .append("     D.DEPTH as DEPTH")
            .append(" FROM ")
            .append("     ( ")
            .append("     SELECT ")
            .append("         A.DEPARTMENT_CD, ")
            .append("         A.DEPARTMENT_NAME, ")
            .append("         B.DEPARTMENT_ID, ")
            .append("         B.PARENT_DEPARTMENT_ID, ")
            .append("         B.DEPTH ")
            .append("     FROM ")
            .append("         PCF_DEPARTMENT_T A, ")
            .append("         PCF_DEPARTMENT_INC_ATH_T B ")
            .append("     WHERE ")
            .append("         A.DEPARTMENT_ID= B.DEPARTMENT_ID ")
            .append("         AND B.DEPTH    = '0' ")
            .append("     ) ")
            .append("     C ")
            .append(" LEFT OUTER JOIN ")
            .append("     PCF_DEPARTMENT_INC_ATH_T D ")
            .append("     ON ")
            .append("     D.DEPARTMENT_ID= C.DEPARTMENT_ID ")
            .append("     AND D.DEPTH    = '1' ")
            .append(" ORDER BY ")
            .append("     C.DEPARTMENT_ID");
        List<PcfDepartmentIncAth> list =
            em
                .createNativeQuery(getDepartmentTreeDataSql.toString(), PcfDepartmentIncAth.class)
                .getResultList();
        return list;
    }
}
