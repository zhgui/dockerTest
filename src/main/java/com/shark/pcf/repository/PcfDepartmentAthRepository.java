package com.shark.pcf.repository;

import org.springframework.stereotype.Repository;

import com.shark.common.repository.BaseRepository;
import com.shark.pcf.entity.PcfDepartmentAth;

/**
 * 组织用户关系数据访问层
 * 
 * @since 1.0
 * @author shark
 * @version 1.0
 */
@Repository
public interface PcfDepartmentAthRepository extends BaseRepository<PcfDepartmentAth, Long> {
    // public List getDepartmentTreeData();
}
