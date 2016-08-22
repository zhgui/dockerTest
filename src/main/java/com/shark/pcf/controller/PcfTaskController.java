package com.shark.pcf.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.jsoup.helper.StringUtil;
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
import com.shark.common.web.bind.annotation.CurrentUser;
import com.shark.common.web.controller.BaseController;
import com.shark.common.web.validate.AjaxResponse;
import com.shark.pcf.entity.PcfTask;
import com.shark.pcf.service.PcfTaskService;
import com.shark.pcf.vo.PcfUserVO;


@Controller
@RequestMapping("/pcf/task")
public class PcfTaskController extends BaseController<PcfTask, Long> {

    private final Logger logger = LoggerFactory.getLogger(PcfTaskController.class);
    
    public final static Integer TYPE = -1;

    @Autowired
    private PcfTaskService pcfTaskService;


    @RequestMapping("/taskList")
    public String taskList(Model model) {
        return viewName("taskList");
    }
    
    @RequestMapping("/taskAdd")
    public String taskAdd(Model model) {
        return viewName("taskAdd");
    }
    
    @RequestMapping("/taskEditor")
    public String taskEditor(Model model, Long taskId) {
        PcfTask taskTmp = pcfTaskService.findOne(taskId);
        model.addAttribute("pcfTask", taskTmp);
        return viewName("taskEditor");
    }
    
    @RequestMapping("/taskDetail")
    public String taskDetail(Model model, Long taskId) {
        PcfTask taskTmp = pcfTaskService.findOne(taskId);
        model.addAttribute("pcfTask", taskTmp);
        return viewName("taskDetail");
    }
    
    
    @RequestMapping("/toTimeExpressionDetail")
    public String toTimeExpressionDetail(Model model,String timeExpressionValue) {
    	model.addAttribute("cron", timeExpressionValue);
        return viewName("timeExpressionDetail");
    }
    
    @RequestMapping("/taskByConsList")
    public void taskByConsList(Searchable searchable, HttpServletResponse response) {
        try {
            ReportPage reportPage = pcfTaskService.taskByConsList(searchable);
            PageConvertUtils.convertToJqGrid(reportPage, response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    @RequestMapping("/addTask")
    @ResponseBody
    public AjaxResponse addTask(@CurrentUser PcfUserVO userVO, Model model, PcfTask pcfTask) {
        try {
            if(null != pcfTask){
            	if (StringUtil.isBlank(pcfTask.getName())) {
                    return AjaxResponse.fail("请输入任务类");
                } 
            	if (TYPE.equals(pcfTask.getType()) ){
            		 return AjaxResponse.fail("请选择类型");
                }
            	List<PcfTask> pcfTaskListTmp = pcfTaskService.findOneByViewName(pcfTask.getViewName());
            	if(pcfTaskListTmp.size()>0){
            		return AjaxResponse.fail("已存在重复的任务名称,请重新输入任务名称");
            	}
            	PcfTask pcfTaskTmp = pcfTaskService.addTask(pcfTask);
            	return AjaxResponse.success("保存任务成功!");
            }else{
            	return AjaxResponse.fail("保存任务失败");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResponse.fail(e.getMessage());
        }
    }
    
    @RequestMapping("/taskUpdate")
    @ResponseBody
    public AjaxResponse taskUpdate(@CurrentUser PcfUserVO userVO, Model model, PcfTask pcfTask) {
        try {
            if(null != pcfTask){
            	if (StringUtil.isBlank(pcfTask.getName())) {
                    return AjaxResponse.fail("请输入任务类");
                } 
            	if (TYPE.equals(pcfTask.getType()) ){
            		 return AjaxResponse.fail("请选择类型");
                }
            	List<PcfTask> pcfTaskListTmp = pcfTaskService.findAnotherOneByViewNameAndId(pcfTask.getViewName(),pcfTask.getId());
            	if(pcfTaskListTmp.size()>0){
            		return AjaxResponse.fail("已存在重复的任务名称,请重新输入任务名称");
            	}
            	PcfTask pcfTaskTmp = pcfTaskService.updateTask(pcfTask);
            	return AjaxResponse.success("更新任务成功!");
            }else{
            	return AjaxResponse.fail("保存任务失败");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResponse.fail(e.getMessage());
        }
    }
    
    
}
