package com.shark.pcf.service.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shark.common.utils.DateUtil;
import com.shark.pcf.entity.PcfTask;
import com.shark.pcf.service.PcfTaskService;

public class QuartzTask {


    private final Logger logger = LoggerFactory.getLogger(QuartzTask.class);
    
    int i=1;
    
    @Resource
    PcfTaskService taskService;
    
    public void activityStatus() {
        try {
            String dateTime = DateUtil.getStringDateShort();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            long dif = df.parse(dateTime).getTime() - 86400 * 1000;//减一天
            Date date = new Date();
            date.setTime(dif);
            String yesterdayDate= df.format(date);
            logger.info("当前时间"+dateTime);
            logger.info("报名截止时间"+yesterdayDate);
            logger.info("开始执行修改报名结束任务");
             logger.info("报名结束任务执行完毕");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    public void feedBackStatus() {
         try {
             String dateTime = DateUtil.getStringDateShort();
             DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
             long dif = df.parse(dateTime).getTime() - 86400 * 1000;//减一天
             Date date = new Date();
             date.setTime(dif);
             String yesterdayDate= df.format(date);
             logger.info("当前时间"+dateTime);
             logger.info("报名截止时间"+yesterdayDate);
             logger.info("开始执行反馈结束任务");
              logger.info("反馈结束任务执行完毕");
         } catch (Exception e) {
             logger.error(e.getMessage(), e);
         }
     }
    
	//每隔5秒执行一次：*/5 * * * * ?
	//每隔1分钟执行一次：0 */1 * * * ?
	//每隔30秒执行一次: 0/30 * * * * ?
	//每天23点执行一次：0 0 23 * * ?
	//每天凌晨1点执行一次：0 0 1 * * ?
	//每月1号凌晨1点执行一次：0 0 1 1 * ?
	//每月最后一天23点执行一次：0 0 23 L * ?
	//每周星期天凌晨1点实行一次：0 0 1 ? * L
	//在26分、29分、33分执行一次：0 26,29,33 * * * ?
	//每天的0点、13点、18点、21点都执行一次：0 0 0,13,18,21 * * ?
    public void executeAllTaskList() {
        try {
        	 
             logger.info("***********扫描任务"+i+"开始*************");
             List<PcfTask> taskList = taskService.getPcfTaskList();
	         for(PcfTask tmp: taskList){
	     		if(tmp.getStatus().equals(PcfTask.PCF_TASK_STATUS_EXECUTE)){
	     			Class clatmp = Class.forName(tmp.getName());
	     			QuartzManager.removeJob(tmp.getViewName());
	     			QuartzManager.addJob(tmp.getViewName(),clatmp, tmp.getTimeExpressionValue());
	     			logger.info("******************"+tmp.getViewName()+"***任务开始执行*********");
	     			i++;
	     		}else if(tmp.getStatus().equals(PcfTask.PCF_TASK_STATUS_STOP)){
	     			Class clatmp = Class.forName(tmp.getName());
	     			QuartzManager.removeJob(tmp.getViewName());
	     			logger.info("***********"+tmp.getViewName()+"***任务停止执行******结束*********");
	     		}
	     	 }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
