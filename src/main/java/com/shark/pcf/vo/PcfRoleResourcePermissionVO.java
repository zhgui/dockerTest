package com.shark.pcf.vo;

import com.shark.pcf.entity.PcfRoleResourcePermission;

/**
 * Created by win7 on 2014/12/11.
 */
public class PcfRoleResourcePermissionVO extends PcfRoleResourcePermission {

    private String permissionNames;
    private String resourceName;
    private String resourceIdentify;

    public String getPermissionNames() {
        return permissionNames;
    }

    public void setPermissionNames(String permissionNames) {
        this.permissionNames = permissionNames;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceIdentify() {
        return resourceIdentify;
    }

    public void setResourceIdentify(String resourceIdentify) {
        this.resourceIdentify = resourceIdentify;
    }
}
