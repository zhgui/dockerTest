package com.shark.pcf.vo;

/**
 * 组织树数据VO
 * 
 * @since 1.0
 * @author shark
 * @version 1.0
 */
public class PcfDepartmentTreeVO {
    // 组织编号
    private String departmentCd;
    // 组织名
    private String departmentName;
    // 组织ID
    private Long departmentId;
    // 父组织ID
    private Long parentDepartmentId;
    // 层级
    private Long depth;

    public String getDepartmentCd() {
        return departmentCd;
    }

    public void setDepartmentCd(String departmentCd) {
        this.departmentCd = departmentCd;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getParentDepartmentId() {
        return parentDepartmentId;
    }

    public void setParentDepartmentId(Long parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
    }

    public Long getDepth() {
        return depth;
    }

    public void setDepth(Long depth) {
        this.depth = depth;
    }
}
