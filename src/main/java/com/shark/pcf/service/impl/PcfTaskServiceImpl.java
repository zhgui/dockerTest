package com.shark.pcf.service.impl;

import java.util.List;

import com.shark.common.entity.search.Searchable;
import com.shark.common.query.CondQueryBO;
import com.shark.common.query.ReportPage;
import com.shark.common.query.SearchConditions;
import com.shark.common.service.BaseService;
import com.shark.pcf.entity.PcfTask;
import com.shark.pcf.repository.PcfTaskRepository;
import com.shark.pcf.service.PcfTaskService;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PcfTaskServiceImpl extends BaseService<PcfTask, Long> implements PcfTaskService {

	private Logger logger = Logger.getLogger(PcfTaskServiceImpl.class);
    @Resource
    private PcfTaskRepository pcfTaskRepository;
    
    @Autowired
    private CondQueryBO condQueryBO;

	@Override
	public List<PcfTask> getPcfTaskList() {
		List<PcfTask> pcfTaskList = pcfTaskRepository.findAll();
		return pcfTaskList;
	}
	
	@Override
	public List<PcfTask> getPcfTaskList(Integer status) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" select * ")
		   .append(" from ")
		   .append(" pcf_task_t ")
		   .append(" where ")
		   .append(" status= "+status+" ");
		logger.info(" this sql is  "+sbf.toString()+"  ");
		List<PcfTask> pcfTaskList = this.findBySQL(sbf.toString(), PcfTask.class);
		return pcfTaskList;
	}

	/**
	 * 查询任务列表信息
	 */
	@Override
	public ReportPage taskByConsList(Searchable searchable) {
		StringBuffer sltFlds = new StringBuffer();
        // 取得项目
        sltFlds
            .append(" ID, ")
            .append(" NAME, ")
            .append(" GROUPS, ")
            .append(" TIME_EXPRESSION_NAME, ")
            .append(" TIME_EXPRESSION_VALUE, ")
            .append(" IF(STATUS=1,'启用','停用') AS STATUS_VALUE , ")
            .append(" STATUS , ")
            .append(" REMARK, ")
            .append(" TYPE, ")
            .append(" VIEW_NAME, ")
            .append(" TIME_EXPRESSION_ID ");

        StringBuffer fromTbl = new StringBuffer();
        // 关联表
        fromTbl
            .append(" FROM ")
            .append(" PCF_TASK_T ")
            .append(" WHERE  ")
            .append(SearchConditions.DFT_GROUP)
            .append(" ORDER BY ID DESC ");
        SearchConditions conditions =
            SearchConditions.forNoGroupSearch(searchable, fromTbl.toString(), sltFlds.toString());
        return condQueryBO.searchPage(conditions);
	}

	@Override
	public PcfTask addTask(PcfTask pcfTask) {
		PcfTask pcfTaskTmp = this.save(pcfTask);
		return pcfTaskTmp;
	}

	@Override
	public PcfTask updateTask(PcfTask pcfTask) {
		PcfTask pcfTaskTmp =this.update(pcfTask);
		return pcfTaskTmp;
	}

	@Override
	public List<PcfTask> findOneByViewName(String viewName) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" select * ")
		   .append(" from ")
		   .append(" pcf_task_t ")
		   .append(" where ")
		   .append(" view_name= \'"+viewName+"\' ");
		List<PcfTask> pcfTaskList = this.findBySQL(sbf.toString(), PcfTask.class);
		return pcfTaskList;
	}

	@Override
	public List<PcfTask> findAnotherOneByViewNameAndId(String viewName, Long id) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" select * ")
		   .append(" from ")
		   .append(" pcf_task_t ")
		   .append(" where ")
		   .append(" view_name= \'"+viewName+"\' ")
		   .append(" And id <>"+id+" ");
		List<PcfTask> pcfTaskList = this.findBySQL(sbf.toString(), PcfTask.class);
		return pcfTaskList;
	}

}