package com.shark.pcf.repository;

import com.shark.common.repository.BaseRepository;
import com.shark.pcf.entity.PcfRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by win7 on 2014/11/30.
 */
@Repository
public interface PcfRoleRepository extends BaseRepository<PcfRole, Long> {


    @Query("select r from PcfRole r,PcfRoleAth ra where r.roleId = ra.roleId and ra.userId= :userId")
    public List<PcfRole> findByUserId(@Param("userId") Long userId);

}

