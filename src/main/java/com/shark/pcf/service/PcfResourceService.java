package com.shark.pcf.service;

import com.shark.common.entity.search.Searchable;
import com.shark.common.plugin.web.controller.entity.ZTree;
import com.shark.common.query.ReportPage;
import com.shark.common.service.IBaseService;
import com.shark.pcf.entity.PcfResource;
import com.shark.pcf.vo.MenuVO;
import com.shark.pcf.vo.PcfResourceVO;

import java.util.List;

/**
 * Created by win7 on 2014/11/25.
 */
public interface PcfResourceService extends IBaseService<PcfResource, Long> {


    public ReportPage findByCond(Searchable searchable);

    public PcfResourceVO save(PcfResourceVO resourceVO);

    public PcfResourceVO update(PcfResourceVO resourceVO);


    public PcfResourceVO findOneVO(Long resourceId);


    public List<ZTree<Long>> findTree();

    public List<MenuVO> findMenus(Long userId);
}
