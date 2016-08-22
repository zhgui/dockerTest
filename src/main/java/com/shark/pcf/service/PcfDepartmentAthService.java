package com.shark.pcf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shark.common.entity.search.Searchable;
import com.shark.common.plugin.web.controller.entity.ZTree;
import com.shark.common.query.ReportPage;
import com.shark.common.service.IBaseService;
import com.shark.pcf.entity.PcfDepartmentAth;
import com.shark.pcf.entity.PcfUser;
import com.shark.pcf.vo.PcfDepartmentAthVO;
import com.shark.pcf.vo.PcfRoleAthVO;
import com.shark.pcf.vo.PcfUserVO;

/**
 * 组织用户关系服务层接口
 * 
 * @since 1.0
 * @author shark
 * @version 1.0
 */
@Service
public interface PcfDepartmentAthService extends IBaseService<PcfDepartmentAth, Long>{
    /**
     * 根据检索条件，取得检索结果。
     * 
     * @param searchable
     *            {@link Searchable}
     * @return {@link ReportPage}
     */
    public ReportPage findByCond(Searchable searchable);

    /**
     * 根据查询条件，取得所属组织的用户列表。
     * 
     * @param searchable
     *            {@link Searchable}
     * @return {@link ReportPage}
     */
    public ReportPage findUserAthListByCond(Searchable searchable);

    /**
     * 保存组织用户关系信息。
     * 
     * @param departmentAthVO
     *            {@link PcfDepartmentAthVO}
     * @return {@link PcfDepartmentAthVO}
     */
    public PcfDepartmentAthVO save(PcfDepartmentAthVO departmentAthVO);

    /**
     * 修改组织用户关系信息。
     * 
     * @param departmentAthVO
     *            {@link PcfRoleAthVO}
     * @return {@link PcfRoleAthVO}
     */
    public PcfDepartmentAthVO update(PcfDepartmentAthVO departmentAthVO);

    /**
     * 删除部门所属的信息。
     * 
     * @param departmentAthList
     *            部门所属ID的数组
     */
    public void deleteDepartmentAth(Long[] departmentAthList);

    /**
     * 取得组织树形结构数据。
     * 
     * @return 组织树形结构数据
     */
    public List<ZTree<Long>> findTreeData();

    /**
     * 给指定的用户增加组织。
     * 
     * @param departmentIdList
     *            组织ID集合
     * @param userId
     *            用户ID
     * @param pcfUser
     *            {@link PcfUser}
     */
    public void addDepartmentToUser(Long[] departmentIdList, Long userId, PcfUserVO pfUserVO);

    /**
     * 给指定的组织增加用户。
     * 
     * @param userIdList
     *            用户ID集合
     * @param departmentId
     *            组织ID
     */
    public void addUserToDepartment(Long[] userIdList, Long departmentId, PcfUserVO pfUserVO);

    /**
     * 给用户设置主所属组织。
     * 
     * @param departmentAthIdList
     *            组织所属ID列表
     * @param userId
     *            用户ID
     */
    public void setDepartmentMainToUser(Long[] departmentAthIdList, Long userId);
}
