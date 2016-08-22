package com.shark.pcf.service;

import com.shark.common.entity.search.Searchable;
import com.shark.common.query.ReportPage;
import com.shark.common.service.BaseService;
import com.shark.common.service.IBaseService;
import com.shark.pcf.entity.PcfPermission;
import com.shark.pcf.vo.PcfPermissionVO;

/**
 * Created by win7 on 2014/11/30.
 */
public interface PcfPermissionService extends IBaseService<PcfPermission, Long> {

    public ReportPage findByCond(Searchable searchable);

    public PcfPermissionVO save(PcfPermissionVO permissionVO);

    public PcfPermissionVO update(PcfPermissionVO permissionVO);

}
