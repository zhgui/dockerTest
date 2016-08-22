package com.shark.pcf.repository;

import com.shark.common.repository.BaseRepository;
import com.shark.pcf.entity.PcfPermission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by win7 on 2014/11/25.
 */
@Repository
public interface PcfPermissionRepository extends BaseRepository<PcfPermission, Long> {

    List<PcfPermission> findByPermission(String permission);

    List<PcfPermission> findByPermissionAndPermissionIdNot(String permission, Long permissionId);

}
