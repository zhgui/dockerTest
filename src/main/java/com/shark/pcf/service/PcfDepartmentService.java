package com.shark.pcf.service;

import org.springframework.stereotype.Service;

import com.shark.common.entity.search.Searchable;
import com.shark.common.query.ReportPage;
import com.shark.pcf.vo.PcfDepartmentVO;

/**
 * 组织服务层接口
 * 
 * @since 1.0
 * @author shark
 * @version 1.0
 */
@Service
public interface PcfDepartmentService {
    /**
     * 根据检索条件，取得检索结果。
     * 
     * @param searchable
     *            {@link Searchable}
     * @return {@link ReportPage}
     */
    public ReportPage findByCond(Searchable searchable);

    /**
     * 保存组织信息。
     * 
     * @param departmentVO
     *            {@link PcfDepartmentVO}
     * @return {@link PcfDepartmentVO}
     */
    public PcfDepartmentVO save(PcfDepartmentVO departmentVO);

    /**
     * 修改组织信息。
     * 
     * @param departmentVO
     *            {@link PcfDepartmentVO}
     * @return {@link PcfDepartmentVO}
     */
    public PcfDepartmentVO update(PcfDepartmentVO departmentVO);

    /**
     * 修改或者保存组织信息。
     * 
     * @param departmentVO
     *            {@link PcfDepartmentVO}
     * @param userCd
     *            用户编号
     * @return {@link PcfDepartmentVO}
     */
    public PcfDepartmentVO setDepartment(PcfDepartmentVO departmentVO, String userCd);

    /**
     * 彻底删除组织及其下属组织以及关联组织内包和组织所属。
     * 
     * @param departmentId
     *            组织ID
     */
    public void deleteDepartment(Long departmentId);

    /**
     * 移除组织内包关系。
     * 
     * @param departmentId
     *            组织ID
     */
    public void removeDepartment(Long departmentId);

    /**
     * 取得不在无所属的组织集合。
     * 
     * @param searchable
     *            {@link Searchable}
     * @return {@link ReportPage}
     */
    public ReportPage findDepartmentListWithOutIncAth(Searchable searchable);

    /**
     * 将无所属组织添加给组织。
     * 
     * @param departmentIdList
     *            组织ID集合
     * @param parentDepartmentId
     *            父组织ID
     * @param userCd
     *            当前登录用户编号
     */
    public void addDepartmentToIncAth(Long[] departmentIdList, Long parentDepartmentId,
            String userCd);

    /**
     * 根据组织编号取得组织信息。
     * 
     * @param departmentCd
     * @return {@link PcfDepartmentVO}
     */
    public PcfDepartmentVO findByDepartmentCd(String departmentCd);
}
