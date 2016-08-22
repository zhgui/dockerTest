package com.shark.shop.server.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shark.common.entity.search.Searchable;
import com.shark.common.query.PageConvertUtils;
import com.shark.common.query.ReportPage;
import com.shark.common.service.Result;
import com.shark.common.web.bind.annotation.CurrentUser;
import com.shark.common.web.controller.BaseController;
import com.shark.common.web.validate.AjaxResponse;
import com.shark.pcf.vo.PcfUserVO;
import com.shark.shop.server.entity.Merchant;
import com.shark.shop.server.service.MerchantService;

/**
 * 控制层
 * @since 1.0
 * @version 1.0
 */
@Controller
@RequestMapping("/shop/server/merchant")
public class MerchantController extends BaseController<Merchant, Long> {
	
    @Autowired
    private MerchantService merchantService;

    private final Logger logger = LoggerFactory.getLogger(MerchantController.class);
    
    @RequiresPermissions("merchant:list")
    @RequestMapping("/list")
    public String scriptureList() {
        return viewName("list");
    }
    
    @RequiresPermissions("merchant:detail")
    @RequestMapping("/detail")
    public String scriptureDetail(Long merchId,Model model,ServletRequest req) {
    	Merchant merchant= merchantService.findOne(merchId);
		model.addAttribute("merchant", merchant);
        return viewName("detail");
    }
    
    
   /**
    * 列表*/
    @RequestMapping("/merchantByConsList")
    public void merchantByConsList(Searchable searchable, HttpServletResponse response) {
        try {
            ReportPage reportPage = merchantService.getMerchantByConsList(searchable);
            PageConvertUtils.convertToJqGrid(reportPage, response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    @RequiresPermissions("merchant:update")
    @RequestMapping("/createOrUpdate")
    @ResponseBody
    public AjaxResponse createOrUpdateMerchant(Merchant merchant) {
    	Result<Merchant> result=merchantService.createOrUpdateMerchant(merchant);
    	if(result.success()){
    		return AjaxResponse.success();	
    	}
    	return AjaxResponse.fail(result.getMessage());
    }
    
    
    /**
     * 冻结*/
    @RequiresPermissions("merchant:update")
    @RequestMapping("/frozen")
    @ResponseBody
    public AjaxResponse frozenMerchant(Long[] ids,@CurrentUser PcfUserVO userVo) {
    	Result<Object> result=merchantService.MerchantAvailable(ids,Merchant.disabled,userVo);
    	if(result.success()){
    		return AjaxResponse.success();	
    	}
    	return AjaxResponse.fail(result.getMessage());
    }
    
    /**
     * 解冻*/
    @RequiresPermissions("merchant:update")
    @RequestMapping("/unfrozen")
    @ResponseBody
    public AjaxResponse unfrozenMerchant(Long[] ids,@CurrentUser PcfUserVO userVo) {
    	Result<Object> result=merchantService.MerchantAvailable(ids,Merchant.disabled,userVo);
    	if(result.success()){
    		return AjaxResponse.success();	
    	}
    	return AjaxResponse.fail(result.getMessage());
    }
    
    /**
     * 删除*/
    @RequiresPermissions("merchant:delete")
    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResponse deleteMerchant(Long[] ids) {
    	Result<Object> result=merchantService.deleteMerchant(ids);
    	if(result.success()){
    		return AjaxResponse.success();	
    	}
    	return AjaxResponse.fail(result.getMessage());
    }
    
}
