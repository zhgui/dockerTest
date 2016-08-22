package com.shark.pcf.service.task;

import org.jboss.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 测试任务
 * @author User
 *
 */
public class QuartzJobC  implements Job {

	Logger logger = Logger.getLogger(QuartzJobC.class);
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info(">>>>>>>>>>>>>JobC>>>>>>>>>>>>>>>>>>>");
		
	}

}
