package com.shark.pcf.service;

import java.util.List;

import com.shark.common.entity.search.Searchable;
import com.shark.common.query.ReportPage;
import com.shark.pcf.entity.PcfTask;

public interface PcfTaskService {

	List<PcfTask> getPcfTaskList();

	/**
	 * 查询任务列表信息
	 * @param searchable
	 * @return
	 */
	ReportPage taskByConsList(Searchable searchable);

	/**
	 * 添加任务
	 * @param pcfTask
	 * @return
	 */
	PcfTask addTask(PcfTask pcfTask);

	/**
	 * 根据任务类ID,获取任务类对象
	 * @param taskId
	 * @return
	 */
	PcfTask findOne(Long taskId);

	/**
	 * 更新任务对象.
	 * @param pcfTask
	 * @return
	 */
	PcfTask updateTask(PcfTask pcfTask);

	/**
	 * 根据状态参数,查询对象列表.
	 * @param status
	 * @return
	 */
	List<PcfTask> getPcfTaskList(Integer status);

	/**
	 * 根据任务名称查询.
	 * @param viewName
	 * @return
	 */
	List<PcfTask> findOneByViewName(String viewName);

	/**
	 * 查询任务名称相同,ID不同任务
	 * @param viewName
	 * @param id
	 * @return
	 */
	List<PcfTask> findAnotherOneByViewNameAndId(String viewName, Long id);
}