package com.shark.pcf.service;

import org.springframework.stereotype.Service;

import com.shark.common.entity.search.Searchable;
import com.shark.common.query.ReportPage;

/**
 * 组织内包服务层接口
 * 
 * @since 1.0
 * @author shark
 * @version 1.0
 */
@Service
public interface PcfDepartmentIncAthService {

    /**
     * 根据查询条件，取得所属组织的用户列表。
     * 
     * @param departmentId
     *            组织ID
     * @return {@link ReportPage}
     */
    public ReportPage findDepartmentListByDepartment(Searchable searchable);
}
