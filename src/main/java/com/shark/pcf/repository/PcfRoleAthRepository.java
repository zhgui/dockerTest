package com.shark.pcf.repository;

import org.springframework.stereotype.Repository;

import com.shark.common.repository.BaseRepository;
import com.shark.pcf.entity.PcfRoleAth;

/**
 * 用户角色关系数据访问层
 * 
 * @since 1.0
 * @author shark
 * @version 1.0
 */
@Repository
public interface PcfRoleAthRepository extends BaseRepository<PcfRoleAth, Long> {
}
