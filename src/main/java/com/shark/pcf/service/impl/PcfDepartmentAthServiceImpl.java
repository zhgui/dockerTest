package com.shark.pcf.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shark.common.entity.search.Searchable;
import com.shark.common.plugin.web.controller.entity.ZTree;
import com.shark.common.query.CondQueryBO;
import com.shark.common.query.ReportPage;
import com.shark.common.query.SearchConditions;
import com.shark.common.service.BaseService;
import com.shark.common.utils.CollectionsUtil;
import com.shark.common.utils.ReflectUtils;
import com.shark.pcf.entity.PcfDepartmentAth;
import com.shark.pcf.repository.PcfDepartmentAthRepository;
import com.shark.pcf.service.PcfDepartmentAthService;
import com.shark.pcf.vo.PcfDepartmentAthVO;
import com.shark.pcf.vo.PcfDepartmentTreeVO;
import com.shark.pcf.vo.PcfUserVO;

/**
 * 组织用户关系服务层接口
 * 
 * @since 1.0
 * @author shark
 * @version 1.0
 */
@Service
public class PcfDepartmentAthServiceImpl extends BaseService<PcfDepartmentAth, Long> implements
        PcfDepartmentAthService {

    @Autowired
    private CondQueryBO condQueryBO;

    @Autowired
    private PcfDepartmentAthRepository departmentAthRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public ReportPage findByCond(Searchable searchable) {
        StringBuffer sltFlds = new StringBuffer();
        // 取得项目
        sltFlds
            .append(" B.DEPARTMENT_ATH_ID,")
            .append(" A.DEPARTMENT_CD,")
            .append(" A.DEPARTMENT_NAME,")
            .append(" DECODE(B.DEPARTMENT_MAIN,'0',' ','1','主') AS DEPARTMENT_MAIN");
        StringBuffer fromTbl = new StringBuffer();
        // 关联表
        fromTbl
            .append(" FROM ")
            .append(" PCF_DEPARTMENT_T A,")
            .append(" PCF_DEPARTMENT_ATH_T B")
            .append(" WHERE ")
            .append(" A.DEPARTMENT_ID=B.DEPARTMENT_ID ")
            .append(SearchConditions.DFT_GROUP);
        // .append(" ORDER BY B.SORT_KEY ");
        SearchConditions conditions =
            SearchConditions.forNoGroupSearch(searchable, fromTbl.toString(), sltFlds.toString());
        return condQueryBO.searchPage(conditions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReportPage findUserAthListByCond(Searchable searchable) {
        StringBuffer sltFlds = new StringBuffer();
        // 取得项目
        sltFlds
            .append(" A.DEPARTMENT_ATH_ID,")
            .append(" B.USER_CD,")
            .append(" B.USER_NAME,")
            .append(" C.DEPARTMENT_NAME,")
            .append(" B.EMAIL_ADDRESS1,")
            .append(" B.RECORD_USER_CD,")
            .append(" TO_CHAR(A.RECORD_DATE,'yyyy-mm-dd hh24:mi:ss') RECORD_DATE,")
            .append(" IF(A.DELETE_FLAG='1','有效','无效') AS DELETE_FLAG");
        StringBuffer fromTbl = new StringBuffer();
        // 关联表
        fromTbl
            .append(" FROM")
            .append(" PCF_DEPARTMENT_ATH_T A,")
            .append(" PCF_USER_T B,")
            .append(" PCF_DEPARTMENT_T C")
            .append(" WHERE ")
            .append(" A.USER_ID=B.USER_ID ")
            .append(" AND ")
            .append(" A.DEPARTMENT_ID=C.DEPARTMENT_ID ")
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
    public PcfDepartmentAthVO save(PcfDepartmentAthVO departmentAthVO) {
        PcfDepartmentAth departmentAth = new PcfDepartmentAth();
        ReflectUtils.copyAllPropertiesByName(departmentAthVO, departmentAth, true);
        departmentAth = departmentAthRepository.save(departmentAth);
        ReflectUtils.copyAllPropertiesByName(departmentAth, departmentAthVO, true);
        return departmentAthVO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PcfDepartmentAthVO update(PcfDepartmentAthVO departmentAthVO) {
        PcfDepartmentAth departmentAth = new PcfDepartmentAth();
        ReflectUtils.copyAllPropertiesByName(departmentAthVO, departmentAth, false);
        departmentAth = departmentAthRepository.save(departmentAth);
        ReflectUtils.copyAllPropertiesByName(departmentAth, departmentAthVO, true);
        return departmentAthVO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteDepartmentAth(Long[] ids) {
        delete(ids);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ZTree<Long>> findTreeData() {
        StringBuffer getDepartmentTreeDataSql = new StringBuffer();
        getDepartmentTreeDataSql
            .append(" SELECT ")
            .append("     C.DEPARTMENT_CD, ")
            .append("     C.DEPARTMENT_NAME, ")
            .append("     C.DEPARTMENT_ID, ")
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
        List<PcfDepartmentTreeVO> treelist =
            departmentAthRepository.findBySQL(
                getDepartmentTreeDataSql.toString(),
                PcfDepartmentTreeVO.class);
        // 取得组织树形结构数据
        // 用户显示到页面的树形结构数据处理
        List<ZTree<Long>> trees = new ArrayList<ZTree<Long>>();
        for (PcfDepartmentTreeVO pcfDepartmentTreeVO : treelist) {
            ZTree<Long> zTree = new ZTree<Long>();
            zTree.setIconSkin("");
            zTree.setId(pcfDepartmentTreeVO.getDepartmentId().longValue());
            zTree.setIsParent(false);
            zTree.setName(pcfDepartmentTreeVO.getDepartmentName());
            zTree.setNocheck(true);
            zTree.setOpen(false);
            // 父节点为null时，及为顶层节点
            if (pcfDepartmentTreeVO.getParentDepartmentId() == null) {
                zTree.setpId(pcfDepartmentTreeVO.getDepartmentId());
            } else {
                zTree.setpId(pcfDepartmentTreeVO.getParentDepartmentId());
            }
            zTree.setRoot(pcfDepartmentTreeVO.getParentDepartmentId() == null);
            trees.add(zTree);
        }
        return trees;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDepartmentToUser(Long[] departmentIdList, Long userId, PcfUserVO pcfUserVO) {
        for (Long departmentId : departmentIdList) {
            addDepartmentAth(userId, departmentId, pcfUserVO);
        }
    }

    /**
     * 增加組織所屬。
     * 
     * @param userId
     *            用戶ID
     * @param departmentId
     *            組織ID
     */
    private void addDepartmentAth(Long userId, Long departmentId, PcfUserVO pcfUserVO) {
        PcfDepartmentAth departmentAth;
        // 判断所属数据存在与否
        if (isDepartmentAthExists(departmentId, userId)) {

            // 新增所属信息
            departmentAth = new PcfDepartmentAth();
            departmentAth.setDepartmentId(departmentId);
            departmentAth.setUserId(userId);
            departmentAth.setDepartmentMain("0");
            departmentAth.setDeleteFlag("0");
            departmentAth.setSortKey(0);
            Timestamp curTM = new Timestamp(System.currentTimeMillis());
            departmentAth.setCreateDate(curTM);
            departmentAth.setCreateUserCd(pcfUserVO.getUserCd());
            departmentAth.setRecordDate(curTM);
            departmentAth.setRecordUserCd(pcfUserVO.getUserCd());
            save(departmentAth);
        }
    }

    /**
     * 判断用户所属信息存在。
     * 
     * @param departmentId
     *            部门ID
     * @param userId
     *            用户ID
     */
    private boolean isDepartmentAthExists(Long departmentId, Long userId) {
        Searchable searchable = Searchable.newSearchable();
        searchable.addSearchParam("departmentId_eq", departmentId);
        searchable.addSearchParam("userId_eq", userId);
        List<PcfDepartmentAth> pcfDepartmentAthList = findAllWithNoPageNoSort(searchable);
        return CollectionsUtil.isEmpty(pcfDepartmentAthList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addUserToDepartment(Long[] userIdList, Long departmentId, PcfUserVO pcfUserVO) {
        for (Long userId : userIdList) {
            addDepartmentAth(userId, departmentId, pcfUserVO);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDepartmentMainToUser(Long[] departmentAthIdList, Long userId) {
        // 先将用户的所有的所属组织的主所属设置为非主所属
        Searchable searchable = Searchable.newSearchable();
        searchable.addSearchParam("userId_eq", userId);
        List<PcfDepartmentAth> pcfDepartmentAthList = findAllWithNoPageNoSort(searchable);
        for (PcfDepartmentAth pcfDepartmentAth : pcfDepartmentAthList) {
            pcfDepartmentAth.setDepartmentMain("0");
            update(pcfDepartmentAth);
        }

        // 设定为主所属
        for (Long departmentAthId : departmentAthIdList) {
            PcfDepartmentAth pcfDepartmentAth = findOne(departmentAthId);
            if (pcfDepartmentAth != null) {
                pcfDepartmentAth.setDepartmentMain("1");
                save(pcfDepartmentAth);
            }
        }
    }
}
