package com.shark.shop.server.repository;

import org.springframework.stereotype.Repository;

import com.shark.common.repository.BaseRepository;
import com.shark.shop.server.entity.User;

/**
 * 数据访问层
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface UserRepository extends BaseRepository<User, Long> {
	
	User findByUserCd(String userCd);
}
