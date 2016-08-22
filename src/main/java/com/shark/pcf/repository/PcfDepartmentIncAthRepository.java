package com.shark.pcf.repository;

import org.springframework.stereotype.Repository;

import com.shark.common.repository.BaseRepository;
import com.shark.pcf.entity.PcfDepartmentIncAth;

/**
 * 组织内包数据访问层
 * 
 * @since 1.0
 * @author shark
 * @version 1.0
 */
@Repository
public interface PcfDepartmentIncAthRepository extends BaseRepository<PcfDepartmentIncAth, Long> {
}
