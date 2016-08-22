package com.shark.shop.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shark.common.entity.search.Searchable;
import com.shark.common.query.CondQueryBO;
import com.shark.common.query.ReportPage;
import com.shark.common.query.SearchConditions;
import com.shark.common.service.BaseService;
import com.shark.common.service.Result;
import com.shark.common.utils.ReflectUtils;
import com.shark.pcf.vo.PcfUserVO;
import com.shark.shop.server.entity.Merchant;
import com.shark.shop.server.repository.MerchantRepository;
import com.shark.shop.server.service.MerchantService;

/**
 *  服务层接口 
 * @since 1.0
 * @version 1.0
 */
@Service
public class MerchantServiceImpl extends BaseService<Merchant, Long>  implements MerchantService{

    @Autowired
    private CondQueryBO condQueryBO;

    @Autowired
    private MerchantRepository merchantRepository;
    
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
	          .append(" WHERE  a.deleteFlag=0 ")
	          .append(SearchConditions.DFT_GROUP)
	          .append(" ORDER BY a.updateTime DESC ");
	      SearchConditions conditions =
	          SearchConditions.forNoGroupSearch(searchable, fromTbl.toString(), sltFlds.toString());
	      return condQueryBO.searchPage(conditions);
	}
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Result<Object> MerchantAvailable(Long[] ids,int status,PcfUserVO userVo) {
		List<Merchant> list=new ArrayList();
		if(ids.length>0){
			for(long id : ids){
				Merchant m=merchantRepository.findOne(id);
				if(m!=null){
					m.setOperator(userVo.getUserCd());
					m.setOperatorTime(new Date());
					m.setUpdateTime(new Date());
					m.setAvailable(status);
				    list.add(m);
				}
			}
			merchantRepository.save(list);
		}		
		return Result.newSuccess();
	}

	@Override
	public Result<Merchant> createOrUpdateMerchant(Merchant merchant) {
		Merchant result;
		if(merchant.getMerchId()!=null){
			merchant.setUpdateTime(new Date());
			Merchant m=merchantRepository.findOne(merchant.getId());
			ReflectUtils.copyAllPropertiesByName(merchant,m,false);
			result=merchantRepository.save(m);
		}else{
			result=merchantRepository.save(merchant);
		}		
		if(result!=null){
			return Result.newSuccess();
		}
		return Result.newFailure("", "更新失败");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Result<Object> deleteMerchant(Long[] ids) {
		List<Merchant> list=new ArrayList();
		if(ids.length>0){
			for(long id : ids){
				Merchant m=merchantRepository.findOne(id);
				if(m!=null){
					m.setDeleteFlag(true);
				    list.add(m);
				}
			}
			merchantRepository.save(list);
		}		
		return Result.newSuccess();
	}

   
}
