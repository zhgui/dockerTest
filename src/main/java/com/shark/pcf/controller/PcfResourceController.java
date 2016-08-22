package com.shark.pcf.controller;

import com.shark.common.entity.search.Searchable;
import com.shark.common.plugin.web.controller.entity.ZTree;
import com.shark.common.query.PageConvertUtils;
import com.shark.common.query.ReportPage;
import com.shark.common.utils.DateUtil;
import com.shark.common.web.controller.BaseController;
import com.shark.pcf.entity.PcfResource;
import com.shark.pcf.service.PcfResourceService;
import com.shark.pcf.vo.PcfResourceVO;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.List;

/**
 * Created by win7 on 2014/11/29.
 */
@Controller
@RequestMapping("/pcf/resource")
public class PcfResourceController extends BaseController<PcfResource, Long> {

    @Autowired
    private PcfResourceService resourceService;


    @RequestMapping("/toList")
    public String toList(Model model) {
        //查出系统中所有资源，组成ZTREE树状结构
        List<ZTree<Long>> zTrees = resourceService.findTree();
        model.addAttribute("zTrees", zTrees);
        return viewName("resourceList");
    }

    @RequiresPermissions("resource:view")
    @RequestMapping("/toView")
    public String toView(Model model, Long resourceId) {
        PcfResourceVO resource = resourceService.findOneVO(resourceId);
        model.addAttribute("resource", resource);
        return viewName("resourceView");
    }

    @RequiresPermissions("resource:update")
    @RequestMapping("/toUpdate")
    public String toUpdate(Model model, Long resourceId) {
        PcfResourceVO resource = resourceService.findOneVO(resourceId);
        model.addAttribute("resource", resource);
        return viewName("resourceUpdate");
    }

    @RequiresPermissions("resource:create")
    @RequestMapping("/toAdd")
    public String toAdd(Model model, Long resourceId) {
        PcfResourceVO resource = resourceService.findOneVO(resourceId);
        model.addAttribute("resource", resource);
        return viewName("resourceAdd");
    }

    @RequiresPermissions("resource:create")
    @RequestMapping("/add")
    public String add(Model model, PcfResourceVO resourceVO) {
        resourceVO.setRecordDate(DateUtil.getNowTime());
        resourceVO.setCreateDate(DateUtil.getNowTime());
        resourceVO.setCreateUserId(0l);
        resourceVO.setRecordUserId(0l);
        resourceService.save(resourceVO);
        model.addAttribute("resource", resourceVO);
        return viewName("resourceAdd");
    }

    @RequiresPermissions("resource:update")
    @RequestMapping("/update")
    public String update(Model model, PcfResourceVO resourceVO) {
        resourceService.update(resourceVO);
        model.addAttribute("resource", resourceVO);
        return viewName("resourceUpdate");
    }

    @RequestMapping("/list")
    public void list(Searchable searchable, HttpServletResponse response) {
        ReportPage reportPage = resourceService.findByCond(searchable);
        PageConvertUtils.convertToJqGrid(reportPage, response);
    }


}
