package com.shark.pcf.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.shark.common.entity.search.Searchable;
import com.shark.common.query.CondQueryBO;
import com.shark.common.query.ReportPage;
import com.shark.common.query.SearchConditions;
import com.shark.common.service.BaseService;
import com.shark.common.utils.ReflectUtils;
import com.shark.pcf.entity.PcfDepartment;
import com.shark.pcf.entity.PcfDepartmentIncAth;
import com.shark.pcf.repository.PcfDepartmentAthRepository;
import com.shark.pcf.repository.PcfDepartmentIncAthRepository;
import com.shark.pcf.repository.PcfDepartmentRepository;
import com.shark.pcf.service.PcfDepartmentService;
import com.shark.pcf.vo.PcfDepartmentVO;

/**
 * 组织服务层接口
 * 
 * @since 1.0
 * @author shark
 * @version 1.0
 */
@Service
public class PcfDepartmentServiceImpl extends BaseService<PcfDepartment, Long> implements
        PcfDepartmentService {

    @Autowired
    private CondQueryBO condQueryBO;

    @Autowired
    private PcfDepartmentRepository departmentRepository;

    @Autowired
    private PcfDepartmentAthRepository departmentAthRepository;

    @Autowired
    private PcfDepartmentIncAthRepository departmentIncAthRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public ReportPage findByCond(Searchable searchable) {
        StringBuffer sltFlds = new StringBuffer();
        // 取得项目
        sltFlds
            .append(" DEPARTMENT_ID,")
            .append(" DEPARTMENT_CD,")
            .append(" DEPARTMENT_NAME,")
            .append(" EMAIL_ADDRESS1,")
            .append(" RECORD_USER_CD,")
            .append(" TO_CHAR(RECORD_DATE,'yyyy-mm-dd hh24:mi:ss') AS RECORD_DATE,")
            .append(" IF(DELETE_FLAG='1','有效','无效') AS DELETE_FLAG");
        StringBuffer fromTbl = new StringBuffer();
        // 关联表
        fromTbl
            .append(" FROM")
            .append(" PCF_DEPARTMENT_T")
            .append(" WHERE ")
            .append(SearchConditions.DFT_GROUP);
        // .append(" ORDER BY SORT_KEY ");
        SearchConditions conditions =
            SearchConditions.forNoGroupSearch(searchable, fromTbl.toString(), sltFlds.toString());
        return condQueryBO.searchPage(conditions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PcfDepartmentVO save(PcfDepartmentVO departmentVO) {
        PcfDepartment department = new PcfDepartment();
        ReflectUtils.copyAllPropertiesByName(departmentVO, department, true);
        department = departmentRepository.save(department);
        ReflectUtils.copyAllPropertiesByName(department, departmentVO, true);
        return departmentVO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PcfDepartmentVO update(PcfDepartmentVO departmentVO) {
        PcfDepartment department = departmentRepository.findOne(departmentVO.getDepartmentId());
        Timestamp createDate = department.getCreateDate();
        String createUserCd = department.getCreateUserCd();
        ReflectUtils.copyAllPropertiesByName(departmentVO, department, true);
        department.setCreateDate(createDate);
        department.setCreateUserCd(createUserCd);

        department = departmentRepository.save(department);
        ReflectUtils.copyAllPropertiesByName(department, departmentVO, true);
        return departmentVO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PcfDepartmentVO setDepartment(PcfDepartmentVO departmentVO, String userCd) {

        Long parentDepartmentId = departmentVO.getParentDepartmentId();

        // 组织ID为null时，即为新增
        if (departmentVO.getDepartmentId() == null) {

            // 取得用户实体，判断重复编号
            PcfDepartmentVO pcfDepartmentVO = findByDepartmentCd(departmentVO.getDepartmentCd());
            if (pcfDepartmentVO != null) {
                return departmentVO;
            }

            // 新增组织
            Timestamp curTM = new Timestamp(System.currentTimeMillis());
            departmentVO.setRecordDate(curTM);
            departmentVO.setCreateDate(curTM);
            departmentVO.setCreateUserCd(userCd);
            departmentVO.setRecordUserCd(userCd);
            departmentVO = save(departmentVO);
            // 新增组织内包
            addDepartmantIncAth(departmentVO.getDepartmentId(), userCd);

            // 有父节点的情况下
            if (parentDepartmentId != null) {
                // 增加组织内包集合数据。
                addDepartmentIncAthTree(
                    departmentVO.getParentDepartmentId(),
                    departmentVO.getDepartmentId(),
                    userCd);
            }
        } else {
            // 修正模式
            Timestamp curTM = new Timestamp(System.currentTimeMillis());
            departmentVO.setRecordDate(curTM);
            departmentVO.setRecordUserCd(userCd);
            departmentVO = update(departmentVO);
        }
        return departmentVO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PcfDepartmentVO findByDepartmentCd(String departmentCd) {
        PcfDepartment department = departmentRepository.findByDepartmentCd(departmentCd);
        if (department == null) {
            return null;
        } else {
            PcfDepartmentVO departmentVO = new PcfDepartmentVO();
            ReflectUtils.copyAllPropertiesByName(department, departmentVO, true);
            return departmentVO;
        }
    }

    /**
     * 增加组织内包集合数据。
     * 
     * @param parentDepartmentId
     *            父组织节点
     * @param departmentId
     *            组织节点
     */
    private void addDepartmentIncAthTree(Long parentDepartmentId, Long departmentId, String userCd) {
        // 取得父组织树的集合
        Timestamp curTM = new Timestamp(System.currentTimeMillis());
        Searchable searchable = Searchable.newSearchable();
        searchable.addSearchParam("departmentId_eq", parentDepartmentId);
        searchable.removePageable();
        searchable.removeSort();
        List<PcfDepartmentIncAth> departmentIncAthList =
            Lists.newArrayList(departmentIncAthRepository.findAll(searchable).getContent());
        for (PcfDepartmentIncAth pcfDepartmentIncAth : departmentIncAthList) {
            PcfDepartmentIncAth entity = new PcfDepartmentIncAth();
            entity.setParentDepartmentId(pcfDepartmentIncAth.getParentDepartmentId());
            entity.setDepartmentId(departmentId);
            entity.setDepth(pcfDepartmentIncAth.getDepth() + 1);
            entity.setDeleteFlag("0");
            entity.setSortKey(0);
            entity.setRecordDate(curTM);
            entity.setCreateDate(curTM);
            entity.setCreateUserCd(userCd);
            entity.setRecordUserCd(userCd);
            departmentIncAthRepository.save(entity);
        }
    }

    /**
     * 新增组织内包。
     * 
     * @param departmentId
     *            组织ID
     */
    private void addDepartmantIncAth(Long departmentId, String userCd) {
        // 新增组织内包
        Timestamp curTM = new Timestamp(System.currentTimeMillis());
        PcfDepartmentIncAth departmentIncAth = new PcfDepartmentIncAth();
        departmentIncAth.setParentDepartmentId(departmentId);
        departmentIncAth.setDepartmentId(departmentId);
        departmentIncAth.setDepth(0);
        departmentIncAth.setDeleteFlag("0");
        departmentIncAth.setSortKey(0);
        departmentIncAth.setRecordDate(curTM);
        departmentIncAth.setCreateDate(curTM);
        departmentIncAth.setCreateUserCd(userCd);
        departmentIncAth.setRecordUserCd(userCd);
        departmentIncAthRepository.save(departmentIncAth);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteDepartment(Long departmentId) {
        List<PcfDepartmentIncAth> departmentIncAthList =
            getDepartmentIdListByParentId(departmentId);
        for (PcfDepartmentIncAth pcfDepartmentIncAth : departmentIncAthList) {
            // 删除组织表
            delete(pcfDepartmentIncAth.getDepartmentId());
            // 组织内包
            deleteDepartmentIncAthByDepartmentId(pcfDepartmentIncAth);
            // 组织所属
            deleteDepartmentAthByDepartemntId(pcfDepartmentIncAth);
        }
    }

    /**
     * 根据父组织节点取得所有父组织的的所有子组织。
     * 
     * @param departmentId
     * @return {@link PcfDepartmentIncAth}的集合
     */
    private List<PcfDepartmentIncAth> getDepartmentIdListByParentId(Long departmentId) {
        Searchable searchable = Searchable.newSearchable();
        searchable.addSearchParam("parentDepartmentId_eq", departmentId);
        searchable.removePageable();
        searchable.removeSort();
        List<PcfDepartmentIncAth> departmentIncAthList =
            Lists.newArrayList(departmentIncAthRepository.findAll(searchable).getContent());
        return departmentIncAthList;
    }

    /**
     * 根据组织ID删除组织内包数据。
     * 
     * @param pcfDepartmentIncAth
     *            {@link PcfDepartmentIncAth}
     */
    private void deleteDepartmentAthByDepartemntId(PcfDepartmentIncAth pcfDepartmentIncAth) {
        // 删除组织所属表
        StringBuffer deleteDepartmentAthSql = new StringBuffer();
        deleteDepartmentAthSql
            .append(" DELETE ")
            .append(" FROM ")
            .append("     PCF_DEPARTMENT_ATH_T A")
            .append(" WHERE ")
            .append("     A.DEPARTMENT_ID= ?");
        departmentAthRepository.deleteBySql(
            deleteDepartmentAthSql.toString(),
            pcfDepartmentIncAth.getDepartmentId());
    }

    /**
     * 根据组织ID删除组织所属数据。
     * 
     * @param pcfDepartmentIncAth
     *            {@link PcfDepartmentIncAth}
     */
    private void deleteDepartmentIncAthByDepartmentId(PcfDepartmentIncAth pcfDepartmentIncAth) {
        // 删除组织内包表
        StringBuffer deleteDepartmentIncAthSql = new StringBuffer();
        deleteDepartmentIncAthSql
            .append(" DELETE ")
            .append(" FROM ")
            .append("     PCF_DEPARTMENT_INC_ATH_T A")
            .append(" WHERE ")
            .append("     A.DEPARTMENT_ID= ? ");
        departmentIncAthRepository.deleteBySql(
            deleteDepartmentIncAthSql.toString(),
            pcfDepartmentIncAth.getDepartmentId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeDepartment(Long departmentId) {
        List<PcfDepartmentIncAth> departmentIncAthList =
            getDepartmentIdListByParentId(departmentId);
        for (PcfDepartmentIncAth pcfDepartmentIncAth : departmentIncAthList) {
            // 组织内包
            deleteDepartmentIncAthByDepartmentId(pcfDepartmentIncAth);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReportPage findDepartmentListWithOutIncAth(Searchable searchable) {
        StringBuffer sltFlds = new StringBuffer();
        // 取得项目
        sltFlds
            .append(" A.DEPARTMENT_ID,")
            .append(" A.DEPARTMENT_CD,")
            .append(" A.DEPARTMENT_NAME,")
            .append(" A.EMAIL_ADDRESS1,")
            .append(" A.RECORD_USER_CD,")
            .append(" TO_CHAR(A.RECORD_DATE,'yyyy-mm-dd hh24:mi:ss') RECORD_DATE,")
            .append(" DECODE(A.DELETE_FLAG,'0','有效','1','无效') AS DELETE_FLAG");
        StringBuffer fromTbl = new StringBuffer();
        // 关联表
        fromTbl
            .append(" FROM")
            .append("     PCF_DEPARTMENT_T A")
            .append(" WHERE ")
            .append("     NOT EXISTS ")
            .append("     ( ")
            .append("      SELECT ")
            .append("          0 ")
            .append("      FROM ")
            .append("          PCF_DEPARTMENT_INC_ATH_T B ")
            .append("      WHERE ")
            .append("          A.DEPARTMENT_ID= B.DEPARTMENT_ID ")
            .append("      ) ")
            .append(SearchConditions.DFT_GROUP)
            .append(" ORDER BY A.SORT_KEY ");
        SearchConditions conditions =
            SearchConditions.forNoGroupSearch(searchable, fromTbl.toString(), sltFlds.toString());
        return condQueryBO.searchPage(conditions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDepartmentToIncAth(Long[] departmentIdList, Long parentDepartmentId,
            String userCd) {

        for (Long departmentId : departmentIdList) {
            // 新增组织内包
            addDepartmantIncAth(departmentId, userCd);

            // 有父节点的情况下
            if (parentDepartmentId != null) {
                // 增加组织内包集合数据。
                addDepartmentIncAthTree(parentDepartmentId, departmentId, userCd);
            }
        }

    }
}
