package com.shark.pcf.vo;

import com.shark.pcf.entity.PcfRole;
import com.shark.pcf.entity.PcfRoleResourcePermission;

import java.util.List;

/**
 * Created by win7 on 2014/11/30.
 */
public class PcfRoleVO extends PcfRole {

    private List<PcfRoleResourcePermissionVO> pcfRoleResourcePermissionVOs;


    public List<PcfRoleResourcePermissionVO> getPcfRoleResourcePermissionVOs() {
        return pcfRoleResourcePermissionVOs;
    }

    public void setPcfRoleResourcePermissionVOs(List<PcfRoleResourcePermissionVO> pcfRoleResourcePermissionVOs) {
        this.pcfRoleResourcePermissionVOs = pcfRoleResourcePermissionVOs;
    }
}
