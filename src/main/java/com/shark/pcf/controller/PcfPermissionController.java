package com.shark.pcf.controller;

import com.shark.common.entity.search.Searchable;
import com.shark.common.query.PageConvertUtils;
import com.shark.common.query.ReportPage;
import com.shark.common.report.usermode.DocReport;
import com.shark.common.report.usermode.DocReportFactory;
import com.shark.common.utils.DateUtil;
import com.shark.common.utils.ExcelUtils;
import com.shark.common.web.controller.BaseController;
import com.shark.common.web.validate.AjaxResponse;
import com.shark.pcf.entity.PcfPermission;
import com.shark.pcf.service.impl.PcfPermissionServiceImpl;
import com.shark.pcf.type.ResultType;
import com.shark.pcf.vo.PcfPermissionVO;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by win7 on 2014/11/25.
 */
@Controller
@RequestMapping("/pcf/permission")
public class PcfPermissionController extends BaseController<PcfPermission, Long> {

    private final Logger logger = LoggerFactory.getLogger(PcfPermissionController.class);
    //判断是否显示权限详情页面上的返回按钮.
    public static final String FLAG = "1";

    @Autowired
    private PcfPermissionServiceImpl permissionService;


    @RequestMapping("/toList")
    public String toList() {
        return viewName("permissionList");
    }

    @RequestMapping("/list")
    public void list(Searchable searchable, HttpServletResponse response) {
        ReportPage reportPage = permissionService.findByCond(searchable);
        PageConvertUtils.convertToJqGrid(reportPage, response);
    }

    @RequestMapping("/listExport")
    public void export(Searchable searchable, HttpServletResponse response) throws Exception {
        searchable.setPage(0, Integer.MAX_VALUE);
        ReportPage reportPage = permissionService.findByCond(searchable);
        DocReport dr = new DocReportFactory().createReport();
        dr.title("权限列表")
                .header("权限名称", "权限标识", "备注", "排序", "修改时间", "状态")
                .body("PERMISSION_NAME", "PERMISSION", "NOTES", "SORT_KEY", "RECORD_DATE", "DELETE_FLAG_VALUE")
                .dataMaps(reportPage.getResult());
        ExcelUtils.docReport2Web(dr, response);
    }

    @RequiresPermissions("permission:create")
    @RequestMapping("/toAdd")
    public String toAdd() {
        return viewName("permissionAdd");
    }

    @RequiresPermissions("permission:detail")
    @RequestMapping("/toDetail")
    public String toDetail(Long permissionId,String flag, Model model) {
        PcfPermission permission = permissionService.findOne(permissionId);
        if(FLAG.equals(flag)){
        	 model.addAttribute("flag", "hide");
        }else{
        	 model.addAttribute("flag", "show");
        }
        model.addAttribute("permission", permission);
        return viewName("permissionDetail");
    }
    
    @RequiresPermissions("permission:update")
    @RequestMapping("/toUpdate")
    public String toUpdate(Long permissionId, Model model) {
        PcfPermission permission = permissionService.findOne(permissionId);
        model.addAttribute("permission", permission);
        return viewName("permissionUpdate");
    }

    @RequiresPermissions("permission:create")
    @RequestMapping("/add")
    @ResponseBody
    public AjaxResponse add(PcfPermissionVO permissionVO, Model model) {
        try {
            Timestamp curTM = new Timestamp(System.currentTimeMillis());
            permissionVO.setRecordDate(curTM);
            permissionVO.setCreateDate(curTM);
            permissionVO = permissionService.save(permissionVO);
            //model.addAttribute("permission", permissionVO);
            //  model.addAttribute(Constants.MESSAGE, "添加成功");
            return AjaxResponse.success("添加成功",ResultType.RESULT_OK, permissionVO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResponse.fail(e.getMessage());
        }
    }

    @RequiresPermissions("permission:update")
    @RequestMapping("/update")
    @ResponseBody
    public AjaxResponse update(PcfPermissionVO permissionVO, Model model) {
        try {
            permissionVO.setRecordDate(DateUtil.getNowTime());
            permissionVO = permissionService.update(permissionVO);
            return AjaxResponse.success("修改成功",ResultType.RESULT_OK, permissionVO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResponse.fail(e.getMessage());
        }
    }
    
    @RequiresPermissions("permission:delete")
    @RequestMapping("/toDelete")
    @ResponseBody
    public AjaxResponse toDelete(Long permissionId, Model model) {
        try {
        	if(!StringUtils.isEmpty(permissionId)){
        		PcfPermission pcfPermission =  permissionService.findOne(permissionId);
        		if(null != pcfPermission){
        			permissionService.delete(permissionId);
        			return AjaxResponse.success("修改成功");
        		}else{
        			return AjaxResponse.fail("删除失败");
        		}
        	}else{
        		return AjaxResponse.fail("删除失败");
        	}
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResponse.fail(e.getMessage());
        }
    }


}
