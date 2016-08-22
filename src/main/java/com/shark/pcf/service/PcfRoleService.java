package com.shark.pcf.service;

import com.shark.common.entity.search.Searchable;
import com.shark.common.query.ReportPage;
import com.shark.common.service.IBaseService;
import com.shark.pcf.entity.PcfRole;
import com.shark.pcf.entity.PcfRoleResourcePermission;
import com.shark.pcf.vo.PcfRoleVO;

import java.util.List;
import java.util.Set;

/**
 * Created by win7 on 2014/11/30.
 */
public interface PcfRoleService extends IBaseService<PcfRole, Long> {

    public ReportPage findByCond(Searchable searchable);

    public PcfRoleVO save(PcfRoleVO roleVO);

    public PcfRoleVO update(PcfRoleVO roleVO);

    public PcfRoleVO addAndGrant(PcfRoleVO roleVO);

    public Set<PcfRole> findRoles(Long userId);

    public Set<PcfRoleVO> findRoleVos(Long userId);

    public Set<String> findStringRoles(Long userId);



}
