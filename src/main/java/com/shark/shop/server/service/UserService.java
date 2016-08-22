package com.shark.shop.server.service;

import org.springframework.stereotype.Service;

import com.shark.common.entity.search.Searchable;
import com.shark.common.query.ReportPage;
import com.shark.common.service.IBaseService;
import com.shark.common.service.Result;
import com.shark.shop.server.entity.User;

/**
 * 服务层接口
 * @version 1.0
 * @since 1.0
 */
@Service
public interface UserService extends IBaseService<User, Long>{
	/**
	 * 列表*/
	public ReportPage getMerchantByConsList(Searchable searchable);
	
	/**冻结解冻*/
	public Result<Object> MerchantAvailable(Long id,int status);

    public User findByUserCd(String userCd);
}
