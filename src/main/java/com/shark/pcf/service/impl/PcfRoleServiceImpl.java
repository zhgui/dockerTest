package com.shark.pcf.service.impl;

import com.shark.common.entity.search.SearchOperator;
import com.shark.common.entity.search.Searchable;
import com.shark.common.exception.FatalBizException;
import com.shark.common.query.CondQueryBO;
import com.shark.common.query.ReportPage;
import com.shark.common.query.SearchConditions;
import com.shark.common.service.BaseService;
import com.shark.common.utils.ReflectUtils;
import com.shark.pcf.entity.*;
import com.shark.pcf.repository.PcfRoleRepository;
import com.shark.pcf.service.PcfPermissionService;
import com.shark.pcf.service.PcfResourceService;
import com.shark.pcf.service.PcfRoleResourcePermissionService;
import com.shark.pcf.service.PcfRoleService;
import com.shark.pcf.vo.PcfRoleResourcePermissionVO;
import com.shark.pcf.vo.PcfRoleVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by win7 on 2014/11/25.
 */
@Service
public class PcfRoleServiceImpl extends BaseService<PcfRole, Long> implements PcfRoleService {

    @Autowired
    private CondQueryBO condQueryBO;

    @Autowired
    private PcfRoleRepository roleRepository;

    @Autowired
    private PcfPermissionService permissionService;

    @Autowired
    private PcfResourceService resourceService;

    @Autowired
    private PcfRoleResourcePermissionService roleResourcePermissionService;

    public ReportPage findByCond(Searchable searchable) {
    	String sltFlds = " ROLE_ID,ROLE_NAME,ROLE,NOTES,DELETE_FLAG,SORT_KEY,CREATE_USER_ID,CREATE_DATE,RECORD_USER_ID,date_format(RECORD_DATE,'%Y-%m-%d %H:%i:%s') RECORD_DATE";
        String fromTbl = " from PCF_ROLE_T where  " + SearchConditions.DFT_GROUP + "  order by SORT_KEY ";
        SearchConditions conditions = SearchConditions.forNoGroupSearch(searchable, fromTbl, sltFlds);
        return condQueryBO.searchPage(conditions);
    }


    public PcfRoleVO save(PcfRoleVO roleVO) {
        PcfRole role = new PcfRole();
        ReflectUtils.copyAllPropertiesByName(roleVO, role, true);
        role = roleRepository.save(role);
        ReflectUtils.copyAllPropertiesByName(role, roleVO, true);
        return roleVO;
    }

    public PcfRoleVO update(PcfRoleVO roleVO) {
        PcfRole role = new PcfRole();
        ReflectUtils.copyAllPropertiesByName(roleVO, role, false);
        role = roleRepository.save(role);
        ReflectUtils.copyAllPropertiesByName(role, roleVO, true);
        return roleVO;
    }

    @Override
    public PcfRoleVO addAndGrant(PcfRoleVO roleVO) {
        PcfRole role = new PcfRole();
        ReflectUtils.copyAllPropertiesByName(roleVO, role, true);
        role = roleRepository.save(role);

        Searchable searchable = Searchable.newSearchable().addSearchFilter("roleId", SearchOperator.eq, role.getRoleId());
        List<PcfRoleResourcePermission> roleResourcePermissions = roleResourcePermissionService.findAllWithSort(searchable);

        for (PcfRoleResourcePermission rrp : roleResourcePermissions) {
            roleResourcePermissionService.delete(rrp);
        }
        //增加角色资源权限表
        if (roleVO.getPcfRoleResourcePermissionVOs() != null) {
            for (PcfRoleResourcePermissionVO rrpVO : roleVO.getPcfRoleResourcePermissionVOs()) {

                FatalBizException.throwWhenTrue(StringUtils.isBlank(rrpVO.getPermissionIds()), "权限ID不能为空");
                FatalBizException.throwWhenNull(rrpVO.getResourceId(), "资源ID不能为空");
                String[] permissionIds = rrpVO.getPermissionIds().split(",");
                for (String permissionId : permissionIds) {
                    PcfPermission permission = permissionService.findOne(Long.valueOf(permissionId));
                    FatalBizException.throwWhenNull(permission, "权限ID【" + permissionId + "】在系统中不存在");
                }
                //检查相应的ID是否存在
                PcfResource resource = resourceService.findOne(rrpVO.getResourceId());
                FatalBizException.throwWhenNull(resource, "权限ID【" + rrpVO.getResourceId() + "】在系统中不存在");

                rrpVO.setRoleId(role.getRoleId());
                PcfRoleResourcePermission rrp = new PcfRoleResourcePermission();
                ReflectUtils.copyAllPropertiesByName(rrpVO, rrp, true);
                roleResourcePermissionService.save(rrp);
                ReflectUtils.copyAllPropertiesByName(rrp, rrpVO, true);
            }
        }
        ReflectUtils.copyAllPropertiesByName(role, roleVO, true);

        return roleVO;
    }


    public Set<PcfRole> findRoles(Long userId) {
        Set<PcfRole> roles = new HashSet<PcfRole>();
        roles.addAll(roleRepository.findByUserId(userId));
        return roles;
    }

    @Override
    public Set<PcfRoleVO> findRoleVos(Long userId) {
        Set<PcfRoleVO> roleVos = new HashSet<PcfRoleVO>();

        Set<PcfRole> roles = findRoles(userId);
        for (PcfRole role : roles) {
            PcfRoleVO roleVO = new PcfRoleVO();
            ReflectUtils.copyAllPropertiesByName(role, roleVO, true);
            roleVos.add(roleVO);
        }
        return roleVos;
    }

    @Override
    public Set<String> findStringRoles(Long userId) {
        Set<String> roleVos = new HashSet<String>();

        Set<PcfRole> roles = findRoles(userId);
        for (PcfRole role : roles) {
            roleVos.add(role.getRole());
        }
        return roleVos;
    }
}
