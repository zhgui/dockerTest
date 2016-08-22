package com.shark.pcf.service;

import org.springframework.stereotype.Service;

import com.shark.common.entity.search.Searchable;
import com.shark.common.query.ReportPage;
import com.shark.common.service.IBaseService;
import com.shark.pcf.entity.PcfRoleAth;
import com.shark.pcf.entity.PcfUser;
import com.shark.pcf.vo.PcfRoleAthVO;
import com.shark.pcf.vo.PcfUserVO;

/**
 * 用户角色关系服务层接口
 * 
 * @since 1.0
 * @author shark
 * @version 1.0
 */
@Service
public interface PcfRoleAthService extends IBaseService<PcfRoleAth, Long>{
    /**
     * 根据检索条件，取得检索结果。
     * 
     * @param searchable
     *            {@link Searchable}
     * @return {@link ReportPage}
     */
    public ReportPage findByCond(Searchable searchable);

    /**
     * 保存用户角色关系信息。
     * 
     * @param userVO
     *            {@link PcfRoleAthVO}
     * @return {@link PcfRoleAthVO}
     */
    public PcfRoleAthVO save(PcfRoleAthVO roleAthVO);

    /**
     * 修改用户角色关系信息。
     * 
     * @param roleAthVO
     *            {@link PcfRoleAthVO}
     * @return {@link PcfRoleAthVO}
     */
    public PcfRoleAthVO update(PcfRoleAthVO roleAthVO);

    /**
     * 删除用户角色所属的信息。
     * 
     * @param roleAthList
     *            部门所属ID的数组
     */
    public void deleteRoleAth(Long[] roleAthList);

    /**
     * 给指定用户增加角色。
     * 
     * @param roleIdList
     *            角色集合
     * @param userId
     *            用户ID
     */
    public void addRoleToUser(Long[] roleIdList, Long userId, PcfUserVO pcfUserVO);
}
