package com.shark.pcf.repository;

import org.springframework.stereotype.*;

import com.shark.common.repository.*;
import com.shark.pcf.entity.*;

/**
 * 用户数据访问层
 *
 * @author shark
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface PcfUserRepository extends BaseRepository<PcfUser, Long> {


    PcfUser findByUserCd(String userCd);
}
