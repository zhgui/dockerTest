package com.shark.pcf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shark.common.entity.search.Searchable;
import com.shark.common.query.CondQueryBO;
import com.shark.common.query.ReportPage;
import com.shark.common.query.SearchConditions;
import com.shark.common.service.BaseService;
import com.shark.pcf.entity.PcfDepartmentAth;
import com.shark.pcf.repository.PcfDepartmentIncAthRepository;
import com.shark.pcf.service.PcfDepartmentIncAthService;

/**
 * 组织内包关系服务层接口
 * 
 * @since 1.0
 * @author shark
 * @version 1.0
 */
@Service
public class PcfDepartmentIncAthServiceImpl extends BaseService<PcfDepartmentAth, Long> implements
        PcfDepartmentIncAthService {

    @Autowired
    private CondQueryBO condQueryBO;

    @Autowired
    private PcfDepartmentIncAthRepository departmentIncAthRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public ReportPage findDepartmentListByDepartment(Searchable searchable) {
        StringBuffer sltFlds = new StringBuffer();
        // 取得项目
        sltFlds
            .append(" C.PARENT_DEPARTMENT_ID,")
            .append(" C.DEPARTMENT_ID,")
            .append(" D.DEPARTMENT_CD,")
            .append(" D.DEPARTMENT_NAME,")
            .append(" D.EMAIL_ADDRESS1,")
            .append(" D.RECORD_USER_CD,")
            .append(" TO_CHAR(D.RECORD_DATE,'yyyy-mm-dd hh24:mi:ss') RECORD_DATE,")
            .append(" DECODE(D.DELETE_FLAG,'0','有效','1','无效') AS DELETE_FLAG");
        StringBuffer fromTbl = new StringBuffer();
        // 关联表
        fromTbl
            .append(" FROM ")
            .append("     ( ")
            .append("      SELECT ")
            .append("          B.PARENT_DEPARTMENT_ID,")
            .append("          B.DEPARTMENT_ID ")
            .append("      FROM ")
            .append("          PCF_DEPARTMENT_T A, ")
            .append("          PCF_DEPARTMENT_INC_ATH_T B ")
            .append("      WHERE ")
            .append("          A.DEPARTMENT_ID = B.PARENT_DEPARTMENT_ID")
            .append("          AND")
            .append("          B.DEPARTMENT_ID <> B.PARENT_DEPARTMENT_ID")
            .append("     ) ")
            .append("     C,")
            .append("     PCF_DEPARTMENT_T D ")
            .append(" WHERE ")
            .append("     C.DEPARTMENT_ID            = D.DEPARTMENT_ID ")
            .append(SearchConditions.DFT_GROUP)
            .append(" ORDER BY D.SORT_KEY ");
        SearchConditions conditions =
            SearchConditions.forNoGroupSearch(searchable, fromTbl.toString(), sltFlds.toString());
        return condQueryBO.searchPage(conditions);
    }
}
