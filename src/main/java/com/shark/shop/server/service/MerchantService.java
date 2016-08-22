package com.shark.shop.server.service;

import org.springframework.stereotype.Service;

import com.shark.common.entity.search.Searchable;
import com.shark.common.query.ReportPage;
import com.shark.common.service.IBaseService;
import com.shark.common.service.Result;
import com.shark.pcf.vo.PcfUserVO;
import com.shark.shop.server.entity.Merchant;

/**
 * 服务层接口
 * @version 1.0
 * @since 1.0
 */
@Service
public interface MerchantService extends IBaseService<Merchant, Long>{
	
	/***
	 * 更新
	 */
	public Result<Merchant> createOrUpdateMerchant(Merchant merchant);  
	/**
	 * 列表*/
	public ReportPage getMerchantByConsList(Searchable searchable);
	
	/**冻结解冻*/
	public Result<Object> MerchantAvailable(Long[] ids,int status,PcfUserVO userVo); 
	
	/**删除恢复*/
	public Result<Object> deleteMerchant(Long[] ids); 
}
