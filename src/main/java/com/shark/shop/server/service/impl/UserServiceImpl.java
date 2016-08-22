package com.shark.shop.server.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shark.common.entity.search.Searchable;
import com.shark.common.query.CondQueryBO;
import com.shark.common.query.ReportPage;
import com.shark.common.query.SearchConditions;
import com.shark.common.service.BaseService;
import com.shark.common.service.Result;
import com.shark.shop.server.entity.Merchant;
import com.shark.shop.server.entity.User;
import com.shark.shop.server.repository.MerchantRepository;
import com.shark.shop.server.repository.UserRepository;
import com.shark.shop.server.service.UserService;
/**
 *  服务层接口 
 * @since 1.0
 * @version 1.0
 */
@Service
public class UserServiceImpl extends BaseService<User, Long>  implements UserService{

    @Autowired
    private CondQueryBO condQueryBO;

    @Autowired
    private MerchantRepository merchantRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
	public ReportPage getMerchantByConsList(Searchable searchable) {
	  	
		StringBuffer sltFlds = new StringBuffer();		
	      sltFlds
	          .append(" a.merchId, ")
	          .append(" a.merchantName,  ")
	          .append(" a.businessLicense,  ")
	          .append(" IF(a.available='1','在用','停用') as availableValue , ")
	          .append(" a.available as available ,")
	          .append(" a.legalPerson as legalPerson ,  ")
	          .append(" a.identityNumber as identityNumber ,  ")
	          .append(" DATE_FORMAT(a.createTime,'%Y/%m/%d %H:%i') AS \"createTime\", ")
	          .append(" DATE_FORMAT(a.updateTime,'%Y/%m/%d %H:%i') AS \"updateTime\" ");
	      StringBuffer fromTbl = new StringBuffer();
	      // 关联表
	      fromTbl
	          .append(" FROM ")
	          .append("  business_merchant a")
	          .append(" WHERE  ")
	          .append(SearchConditions.DFT_GROUP)
	          .append(" ORDER BY a.modifyTime DESC ");
	      SearchConditions conditions =
	          SearchConditions.forNoGroupSearch(searchable, fromTbl.toString(), sltFlds.toString());
	      return condQueryBO.searchPage(conditions);
	}

	@Override
	public Result<Object> MerchantAvailable(Long id, int status) {
		Merchant m=merchantRepository.findOne(id);
		m.setUpdateTime(new Date());
		m.setAvailable(status);
		return Result.newSuccess();
	}

	
	 @Override
	 public User findByUserCd(String userCd) {
	        User user =userRepository.findByUserCd(userCd);
	        if (user == null) {
	            return null;
	        }	           
            return user;
	    }
   
}
