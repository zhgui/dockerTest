package com.shark.pcf.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shark.common.entity.search.Searchable;
import com.shark.common.query.CondQueryBO;
import com.shark.common.query.ReportPage;
import com.shark.common.query.SearchConditions;
import com.shark.common.service.BaseService;
import com.shark.common.utils.CollectionsUtil;
import com.shark.common.utils.ReflectUtils;
import com.shark.pcf.entity.PcfRoleAth;
import com.shark.pcf.repository.PcfRoleAthRepository;
import com.shark.pcf.service.PcfRoleAthService;
import com.shark.pcf.vo.PcfRoleAthVO;
import com.shark.pcf.vo.PcfUserVO;

/**
 * 用户服务层接口
 * 
 * @since 1.0
 * @author shark
 * @version 1.0
 */
@Service
public class PcfRoleAthServiceImpl extends BaseService<PcfRoleAth, Long> implements
        PcfRoleAthService {

    @Autowired
    private CondQueryBO condQueryBO;

    @Autowired
    private PcfRoleAthRepository roleAthRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public ReportPage findByCond(Searchable searchable) {
        StringBuffer sltFlds = new StringBuffer();
        // 取得项目
        sltFlds
            .append(" B.ROLE_ATH_ID ,")
            .append(" A.ROLE_NAME AS ROLE_NAME,")
            .append(" A.ROLE AS ROLE");
        StringBuffer fromTbl = new StringBuffer();
        // 关联表
        fromTbl
            .append(" FROM ")
            .append(" PCF_ROLE_T A, ")
            .append(" PCF_ROLE_ATH_T B ")
            .append(" WHERE ")
            .append(" A.ROLE_ID=B.ROLE_ID ")
            .append(SearchConditions.DFT_GROUP)
            .append(" ORDER BY B.SORT_KEY ");
        SearchConditions conditions =
            SearchConditions.forNoGroupSearch(searchable, fromTbl.toString(), sltFlds.toString());
        return condQueryBO.searchPage(conditions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PcfRoleAthVO save(PcfRoleAthVO roleAthVO) {
        PcfRoleAth roleAth = new PcfRoleAth();
        ReflectUtils.copyAllPropertiesByName(roleAthVO, roleAth, true);
        roleAth = roleAthRepository.save(roleAth);
        ReflectUtils.copyAllPropertiesByName(roleAth, roleAthVO, true);
        return roleAthVO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PcfRoleAthVO update(PcfRoleAthVO roleAthVO) {
        PcfRoleAth roleAth = new PcfRoleAth();
        ReflectUtils.copyAllPropertiesByName(roleAthVO, roleAth, false);
        roleAth = roleAthRepository.save(roleAth);
        ReflectUtils.copyAllPropertiesByName(roleAth, roleAthVO, true);
        return roleAthVO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteRoleAth(Long[] ids) {
        delete(ids);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addRoleToUser(Long[] roleIdList, Long userId, PcfUserVO pcfUserVO) {
        PcfRoleAth roleAth;
        for (Long roleId : roleIdList) {

            if (isRoleExists(roleId, userId)) {
                // 新增角色所屬
                roleAth = new PcfRoleAth();
                roleAth.setRoleId(roleId);
                roleAth.setUserId(userId);
                roleAth.setDeleteFlag("0");
                roleAth.setSortKey(0);
                Timestamp curTM = new Timestamp(System.currentTimeMillis());
                roleAth.setCreateDate(curTM);
                roleAth.setCreateUserCd(pcfUserVO.getUserCd());
                roleAth.setRecordDate(curTM);
                roleAth.setRecordUserCd(pcfUserVO.getUserCd());
                save(roleAth);
            }
        }

    }

    /**
     * 判斷角兒所屬存在與否。
     * 
     * @param roleId
     *            角色ID
     * @param userId
     *            用戶ID
     * @return false:存在 true:不存在
     */
    private boolean isRoleExists(Long roleId, Long userId) {
        Searchable searchable = Searchable.newSearchable();
        searchable.addSearchParam("roleId_eq", roleId);
        searchable.addSearchParam("userId_eq", userId);
        List<PcfRoleAth> pcfRoleAthList = findAllWithNoPageNoSort(searchable);
        return CollectionsUtil.isEmpty(pcfRoleAthList);

    }
}
