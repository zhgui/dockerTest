package com.shark.pcf.repository;

import com.shark.common.repository.BaseRepository;
import com.shark.pcf.entity.PcfRoleResourcePermission;

import java.util.List;

/**
 * Created by win7 on 2014/12/14.
 */
public interface PcfRoleResourcePermissionRepository extends BaseRepository<PcfRoleResourcePermission, Long> {

    public List<PcfRoleResourcePermission> findByRoleId(Long roleId);

}
