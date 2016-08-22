package com.shark.pcf.repository;

import org.springframework.stereotype.Repository;

import com.shark.common.repository.BaseRepository;
import com.shark.pcf.entity.PcfDepartment;

/**
 * 组织数据访问层
 * 
 * @since 1.0
 * @author shark
 * @version 1.0
 */
@Repository
public interface PcfDepartmentRepository extends BaseRepository<PcfDepartment, Long> {

    PcfDepartment findByDepartmentCd(String departmentCd);
}
