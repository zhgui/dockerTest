package com.shark.pcf.service.impl;

import com.shark.common.entity.search.SearchOperator;
import com.shark.common.entity.search.Searchable;
import com.shark.common.service.BaseService;
import com.shark.common.utils.ReflectUtils;
import com.shark.pcf.entity.PcfPermission;
import com.shark.pcf.entity.PcfResource;
import com.shark.pcf.entity.PcfRoleResourcePermission;
import com.shark.pcf.repository.PcfRoleResourcePermissionRepository;
import com.shark.pcf.service.PcfPermissionService;
import com.shark.pcf.service.PcfResourceService;
import com.shark.pcf.service.PcfRoleResourcePermissionService;
import com.shark.pcf.vo.PcfRoleResourcePermissionVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by win7 on 2014/12/14.
 */
@Service
public class PcfRoleResourcePermissionServiceImpl extends BaseService<PcfRoleResourcePermission, Long> implements PcfRoleResourcePermissionService {


    @Autowired
    private PcfResourceService resourceService;

    @Autowired
    private PcfRoleResourcePermissionRepository roleResourcePermissionRepository;

    @Autowired
    private PcfPermissionService permissionService;

    @Override
    public List<PcfRoleResourcePermissionVO> findByRoleId(Long roleId) {
        //查询可用的权限
        Searchable searchable = Searchable.newSearchable().addSearchFilter("roleId", SearchOperator.eq, roleId).addSort(Sort.Direction.ASC, "createDate");

        List<PcfRoleResourcePermission> rrpList = findAllWithSort(searchable);
        List<PcfRoleResourcePermissionVO> roleResourcePermissionVOs = new ArrayList<PcfRoleResourcePermissionVO>();
        for (PcfRoleResourcePermission rrp : rrpList) {
            PcfRoleResourcePermissionVO vo = new PcfRoleResourcePermissionVO();
            ReflectUtils.copyAllPropertiesByName(rrp, vo, true);
            if (StringUtils.isNotBlank(rrp.getPermissionIds())) {
                String[] ids = rrp.getPermissionIds().split(",");
                String permissionNames = "";
                for (String id : ids) {
                    PcfPermission permission = permissionService.findOne(Long.valueOf(id));
                    permissionNames += permission.getPermissionName() + "[" + permission.getPermission() + "]，";
                }
                permissionNames = permissionNames.substring(0, permissionNames.length() - 1);
                vo.setPermissionNames(permissionNames);
            }
            PcfResource resource = resourceService.findOne(rrp.getResourceId());
            if (resource != null) {
                vo.setResourceName(resource.getResourceName());
                roleResourcePermissionVOs.add(vo);
            }

        }
        return roleResourcePermissionVOs;
    }


    public List<PcfRoleResourcePermission> findByRole(Long roleId) {
        return roleResourcePermissionRepository.findByRoleId(roleId);
    }


    public Set<String> findStringPermissions(Long userId) {
        String sql = "select distinct rrp.*,s.identify resource_identify,s.resource_name \n" +
                "  from pcf_role_ath_t ra, pcf_role_t r, pcf_role_resource_permission rrp,pcf_resource_t s\n" +
                " where ra.role_id = r.role_id\n" +
                "   and r.role_id = rrp.role_id\n" +
                "   and s.resource_id = rrp.resource_id" +
                "   and ra.user_id = ?";
        List<PcfRoleResourcePermissionVO> list = roleResourcePermissionRepository.findBySQL(sql, PcfRoleResourcePermissionVO.class, userId);
        Set<String> permissions = new HashSet<String>();
        Searchable searchable = Searchable.newSearchable();
        searchable.addSearchFilter("deleteFlag", SearchOperator.eq, PcfPermission.DELETEFLAG_VAILD);
        List<PcfPermission> permissionList = permissionService.findAllWithNoPageNoSort(searchable);
        for (PcfRoleResourcePermissionVO rrpVO : list) {
            if (StringUtils.isNotBlank(rrpVO.getPermissionIds())) {
                String[] pids = rrpVO.getPermissionIds().split(",");
                for (String pid : pids) {
                    for (PcfPermission permission : permissionList) {
                        if (pid.equals(permission.getId().toString())) {
                            permissions.add(rrpVO.getResourceIdentify() + ":" + permission.getPermission());
                        }
                    }
                }
            }
        }
        return permissions;
    }
}
