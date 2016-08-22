package com.shark.pcf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shark.common.entity.search.Searchable;
import com.shark.common.exception.FatalBizException;
import com.shark.common.query.CondQueryBO;
import com.shark.common.query.ReportPage;
import com.shark.common.query.SearchConditions;
import com.shark.common.service.BaseService;
import com.shark.common.utils.ReflectUtils;
import com.shark.pcf.entity.PcfPermission;
import com.shark.pcf.repository.PcfPermissionRepository;
import com.shark.pcf.service.PcfPermissionService;
import com.shark.pcf.vo.PcfPermissionVO;

/**
 * Created by win7 on 2014/11/25.
 */
@Service
public class PcfPermissionServiceImpl extends BaseService<PcfPermission, Long> implements
        PcfPermissionService {

    @Autowired
    private CondQueryBO condQueryBO;

    @Autowired
    private PcfPermissionRepository permissionRepository;

    public ReportPage findByCond(Searchable searchable) {
        StringBuffer sltFlds = new StringBuffer();
        sltFlds
            .append(" PERMISSION_ID,   ")
            .append(" PERMISSION_NAME,   ")
            .append(" PERMISSION,   ")
            .append(" NOTES,   ")
            .append(" DELETE_FLAG,   ")
            .append(" SORT_KEY,   ")
            .append(" if(DELETE_FLAG=0,'有效','无效') DELETE_FLAG_VALUE, ")
            .append(" DATE_FORMAT(RECORD_DATE,'%Y-%m-%d %H:%i:%s') RECORD_DATE   ");
        String fromTbl =
            " from PCF_PERMISSION_T where  " + SearchConditions.DFT_GROUP + "  order by SORT_KEY ";
        SearchConditions conditions =
            SearchConditions.forNoGroupSearch(searchable, fromTbl, sltFlds.toString());
        return condQueryBO.searchPage(conditions);
    }

    public PcfPermissionVO save(PcfPermissionVO permissionVO) {
        // 检查是否有重复名
        List<PcfPermission> permissionList =
            permissionRepository.findByPermission(permissionVO.getPermission());
        FatalBizException.throwWhenFalse(
            permissionList.isEmpty(),
            "权限标识【" + permissionVO.getPermission() + "】已在系统中存在");
        PcfPermission permission = new PcfPermission();
        ReflectUtils.copyAllPropertiesByName(permissionVO, permission, true);
        permission = permissionRepository.save(permission);
        ReflectUtils.copyAllPropertiesByName(permission, permissionVO, true);
        return permissionVO;
    }

    public PcfPermissionVO update(PcfPermissionVO permissionVO) {
        // 检查是否有重复名
        List<PcfPermission> permissionList =
            permissionRepository.findByPermissionAndPermissionIdNot(
                permissionVO.getPermission(),
                permissionVO.getPermissionId());
        FatalBizException.throwWhenFalse(
            permissionList.isEmpty(),
            "权限标识【" + permissionVO.getPermission() + "】已在系统中存在");
        PcfPermission permission = new PcfPermission();
        ReflectUtils.copyAllPropertiesByName(permissionVO, permission, false);
        permission = permissionRepository.save(permission);
        ReflectUtils.copyAllPropertiesByName(permission, permissionVO, true);
        return permissionVO;
    }

}
