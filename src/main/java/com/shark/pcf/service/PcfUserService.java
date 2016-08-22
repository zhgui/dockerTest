package com.shark.pcf.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.shark.common.entity.search.Searchable;
import com.shark.common.query.ReportPage;
import com.shark.common.service.IBaseService;
import com.shark.pcf.entity.PcfUser;
import com.shark.pcf.vo.PcfUserVO;

/**
 * 用户服务层接口
 * 
 * @author shark
 * @version 1.0
 * @since 1.0
 */
@Service
public interface PcfUserService extends IBaseService<PcfUser, Long> {
    /**
     * 根据检索条件，取得检索结果。
     * 
     * @param searchable
     *            {@link Searchable}
     * @return {@link ReportPage}
     */
    public ReportPage findByCond(Searchable searchable);

    /**
     * 保存用户信息。
     * 
     * @param userVO
     *            {@link PcfUserVO}
     * @return {@link PcfUserVO}
     */
    public PcfUserVO save(PcfUserVO userVO);

    /**
     * 修改用户信息。
     * 
     * @param userVO
     *            {@link PcfUserVO}
     * @return {@link PcfUserVO}
     */
    public PcfUserVO update(PcfUserVO userVO);

    /**
     * 修改或者保存用户信息。
     * 
     * @param userVO
     *            {@link PcfUserVO}
     * @param currentUserVO
     *            {@link PcfUserVO}
     * @return {@link PcfUserVO}
     */
    public PcfUserVO setUser(PcfUserVO userVO, PcfUserVO currentUserVO);

    /**
     * 彻底删除用户信息。
     * 
     * @param userIdList
     *            用户ID集合
     */
    public void deleteUser(Long[] userIdList);

    public PcfUserVO login(String username, String password);

    public Set<String> findStringPermissions(Long userId);

    public PcfUserVO findByUserCd(String userCd);

    public PcfUserVO findByUserId(Long userCd);

    public List<PcfUser> findByLikeUserCd(String userCd);

    /**
     * 修改用户的密码。
     * 
     * @param pcfUserVO
     *            {@link PcfUserVO}
     * @param password
     *            密码
     * @return {@link PcfUserVO}
     */
    public PcfUserVO updatePassword(PcfUserVO pcfUserVO, String password);
    
    /**
     * 根据用户账户和密码查询用户信息.
     * @param userCd
     * @param password
     * @return
     */
	public PcfUser findOneByUserCdAndUserPw(String userCd, String password);


}
