package com.shark.pcf.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.shark.common.entity.AbstractEntity;

/**
 * 任务实体
 *
 * @author shark
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "PCF_TASK_T")
public class PcfTask extends AbstractEntity<Long> {


	//任务执行状态
	public static final Integer PCF_TASK_STATUS_EXECUTE = 1;
	//任务停止执行
	public static final Integer PCF_TASK_STATUS_STOP = 2;
    /**
	 * 
	 */
	private static final long serialVersionUID = -3716803113504224233L;


	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = 0)
    private Long id;


    @Basic
    @Column(name = "NAME", nullable = true, insertable = true, updatable = true, length = 300)
    private String name;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @Basic
    @Column(name = "GROUPS", nullable = true, insertable = true, updatable = true, length = 300)
    private String groups;

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	@Basic
    @Column(name = "TIME_EXPRESSION_NAME", nullable = true, insertable = true, updatable = true, length = 300)
    private String timeExpressionName;
	
    public String getTimeExpressionName() {
		return timeExpressionName;
	}

	public void setTimeExpressionName(String timeExpressionName) {
		this.timeExpressionName = timeExpressionName;
	}
	
	@Basic
    @Column(name = "TIME_EXPRESSION_VALUE", nullable = true, insertable = true, updatable = true, length = 300)
    private String timeExpressionValue;

	public String getTimeExpressionValue() {
		return timeExpressionValue;
	}

	public void setTimeExpressionValue(String timeExpressionValue) {
		this.timeExpressionValue = timeExpressionValue;
	}

	@Basic
    @Column(name = "STATUS", nullable = true, insertable = true, updatable = true, length = 2)
    private Integer status;

    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Basic
    @Column(name = "REMARK", nullable = true, insertable = true, updatable = true, length = 3000)
    private String remark;

    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Basic
    @Column(name = "TYPE", nullable = true, insertable = true, updatable = true, length = 2)
    private Integer type;

    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Basic
    @Column(name = "VIEW_NAME", nullable = true, insertable = true, updatable = true, length = 10)
    private String viewName;

    public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	@Basic
    @Column(name = "TIME_EXPRESSION_ID", nullable = true, insertable = true, updatable = true, length = 10)
    private String timeExpressionId;
   
	public String getTimeExpressionId() {
		return timeExpressionId;
	}

	public void setTimeExpressionId(String timeExpressionId) {
		this.timeExpressionId = timeExpressionId;
	}

	@Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
