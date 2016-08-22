package com.shark.pcf.controller;

import com.shark.common.entity.search.SearchOperator;
import com.shark.common.entity.search.Searchable;
import com.shark.common.plugin.web.controller.entity.ZTree;
import com.shark.common.query.PageConvertUtils;
import com.shark.common.query.ReportPage;
import com.shark.common.web.controller.BaseController;
import com.shark.common.web.validate.AjaxResponse;
import com.shark.pcf.entity.PcfPermission;
import com.shark.pcf.entity.PcfRole;
import com.shark.pcf.service.PcfPermissionService;
import com.shark.pcf.service.PcfResourceService;
import com.shark.pcf.service.PcfRoleResourcePermissionService;
import com.shark.pcf.service.PcfRoleService;
import com.shark.pcf.type.ResultType;
import com.shark.pcf.vo.PcfRoleResourcePermissionVO;
import com.shark.pcf.vo.PcfRoleVO;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by win7 on 2014/11/30.
 */
@Controller
@RequestMapping("/pcf/role")
public class PcfRoleController extends BaseController<PcfRole, Long> {

    private final Logger logger = LoggerFactory.getLogger(PcfRoleController.class);

    @Autowired
    private PcfRoleService roleService;

    @Autowired
    private PcfPermissionService permissionService;

    @Autowired
    private PcfResourceService resourceService;

    @Autowired
    private PcfRoleService pcfRoleService;

    @Autowired
    private PcfRoleResourcePermissionService roleResourcePermissionService;

    @RequestMapping("/toList")
    public String toList(Model model) {
        return viewName("roleList");
    }

    @RequestMapping("/list")
    public void list(Searchable searchable, HttpServletResponse response) {
        ReportPage reportPage = pcfRoleService.findByCond(searchable);
        PageConvertUtils.convertToJqGrid(reportPage, response);
    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        //查询可用的资源
        List<ZTree<Long>> zTrees = resourceService.findTree();
        //查询可用的权限
        Searchable searchable = Searchable.newSearchable().addSearchFilter("deleteFlag", SearchOperator.eq, "0").addSort(Sort.Direction.ASC, "sortKey");
        List<PcfPermission> permissionList = permissionService.findAllWithSort(searchable);
        model.addAttribute("resources", zTrees);
        model.addAttribute("permission", permissionList);
        return viewName("roleAdd");
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Long roleId, Model model) {
        PcfRole role = roleService.findOne(roleId);
        //查询可用的资源
        List<ZTree<Long>> zTrees = resourceService.findTree();
        //查询可用的权限
        Searchable searchable = Searchable.newSearchable().addSearchFilter("deleteFlag", SearchOperator.eq, "0").addSort(Sort.Direction.ASC, "sortKey");
        List<PcfPermission> permissionList = permissionService.findAllWithSort(searchable);
        //查询可用的权限
        List<PcfRoleResourcePermissionVO> rrpList = roleResourcePermissionService.findByRoleId(roleId);
        model.addAttribute("resources", zTrees);
        model.addAttribute("permission", permissionList);
        model.addAttribute("rrpList", rrpList);
        model.addAttribute("role", role);
        return viewName("roleUpdate");
    }

    @RequiresPermissions("role:update")
    @RequestMapping("/addAndAuth")
    @ResponseBody
    public AjaxResponse addAndGrant(PcfRoleVO roleVO, Long[] resourceId, HttpServletRequest request) {
        try {
            Date curDT = new Date();
            roleVO.setRecordDate(curDT);
            roleVO.setCreateDate(curDT);
            roleVO.setCreateUserId(0l);
            roleVO.setRecordUserId(0l);
            String[] permissionIds = request.getParameterValues("permissionIds");
            if (permissionIds != null && permissionIds.length > 0) {
                List<PcfRoleResourcePermissionVO> roleResourcePermissionVOs = new ArrayList<PcfRoleResourcePermissionVO>();
                for (int i = 0; i < resourceId.length; i++) {
                    PcfRoleResourcePermissionVO rrpVO = new PcfRoleResourcePermissionVO();
                    rrpVO.setPermissionIds(permissionIds[i]);
                    rrpVO.setResourceId(resourceId[i]);
                    rrpVO.setRoleId(roleVO.getRoleId());
                    rrpVO.setCreateUserId(roleVO.getCreateUserId());
                    rrpVO.setCreateDate(roleVO.getCreateDate());
                    roleResourcePermissionVOs.add(rrpVO);
                }
                roleVO.setPcfRoleResourcePermissionVOs(roleResourcePermissionVOs);
            }
            roleVO = pcfRoleService.addAndGrant(roleVO);
            return  AjaxResponse.success("角色新增成功", ResultType.RESULT_OK,roleVO.getId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResponse.fail(e.getMessage());
        }
    }
}
