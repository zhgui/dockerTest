package com.shark.pcf.service;

import com.shark.common.service.IBaseService;
import com.shark.pcf.entity.PcfRoleResourcePermission;
import com.shark.pcf.vo.PcfRoleResourcePermissionVO;

import java.util.List;
import java.util.Set;

/**
 * Created by win7 on 2014/12/14.
 */
public interface PcfRoleResourcePermissionService extends IBaseService<PcfRoleResourcePermission, Long> {

    List<PcfRoleResourcePermissionVO> findByRoleId(Long roleId);

    public List<PcfRoleResourcePermission> findByRole(Long roleId);

    public Set<String> findStringPermissions(Long userId);
}
