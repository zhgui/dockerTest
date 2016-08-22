package com.shark.pcf.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.common.collect.Sets;
import com.shark.common.entity.search.Searchable;
import com.shark.common.exception.FatalBizException;
import com.shark.common.query.CondQueryBO;
import com.shark.common.query.ReportPage;
import com.shark.common.query.SearchConditions;
import com.shark.common.service.BaseService;
import com.shark.common.utils.DateUtil;
import com.shark.common.utils.ReflectUtils;
import com.shark.common.utils.security.Md5Utils;
import com.shark.pcf.entity.PcfPermission;
import com.shark.pcf.entity.PcfResource;
import com.shark.pcf.entity.PcfRole;
import com.shark.pcf.entity.PcfRoleResourcePermission;
import com.shark.pcf.entity.PcfUser;
import com.shark.pcf.repository.PcfDepartmentAthRepository;
import com.shark.pcf.repository.PcfRoleAthRepository;
import com.shark.pcf.repository.PcfUserRepository;
import com.shark.pcf.service.PcfPermissionService;
import com.shark.pcf.service.PcfResourceService;
import com.shark.pcf.service.PcfRoleResourcePermissionService;
import com.shark.pcf.service.PcfRoleService;
import com.shark.pcf.service.PcfUserService;
import com.shark.pcf.vo.PcfUserVO;

/**
 * 用户服务层接口
 * 
 * @author shark
 * @version 1.0
 * @since 1.0
 */
@Service
public class PcfUserServiceImpl extends BaseService<PcfUser, Long> implements PcfUserService {

    @Autowired
    private CondQueryBO condQueryBO;

    @Autowired
    private PcfUserRepository userRepository;

    @Autowired
    private PcfDepartmentAthRepository departmentAthRepository;

    @Autowired
    private PcfRoleAthRepository roleAthRepository;

    @Autowired
    private PcfPermissionService permissionService;

    @Autowired
    private PcfPasswordServiceImpl pcfPasswordService;

    @Autowired
    private PcfResourceService resourceService;

    @Autowired
    private PcfRoleResourcePermissionService roleResourcePermissionService;

    @Autowired
    private PcfRoleService roleService;

    /**
     * {@inheritDoc}
     */
    @Override
    public ReportPage findByCond(Searchable searchable) {
        StringBuffer sltFlds = new StringBuffer();
        // 取得项目
        sltFlds
            .append(" USER_ID,")
            .append(" USER_CD,")
            .append(" RECORD_USER_CD,")
            .append(" DATE_FORMAT(RECORD_DATE,'%Y-%m-%d %H:%i:%s') AS RECORD_DATE,")
            .append(" DELETE_FLAG, ")
            .append(" DELETE_FLAG AS DELETE_FLAG_VALUE ");
        StringBuffer fromTbl = new StringBuffer();
        // 关联表
        fromTbl
            .append(" FROM")
            .append(" PCF_USER_T")
            .append(" WHERE ")
            .append(SearchConditions.DFT_GROUP);
        // .append(" order by SORT_KEY ");
        SearchConditions conditions =
            SearchConditions.forNoGroupSearch(searchable, fromTbl.toString(), sltFlds.toString());
        return condQueryBO.searchPage(conditions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PcfUserVO save(PcfUserVO userVO) {
        PcfUser user = new PcfUser();
        ReflectUtils.copyAllPropertiesByName(userVO, user, true);
        String hashUserPassword = userVO.getUserCd().concat(userVO.getPassword()).toString();
        user.setPassword(Md5Utils.hash(Md5Utils.hash((Md5Utils.hash(hashUserPassword)))));
        user = userRepository.save(user);
        ReflectUtils.copyAllPropertiesByName(user, userVO, true);
        return userVO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PcfUserVO update(PcfUserVO userVO) {
        PcfUser user = userRepository.findOne(userVO.getUserId());
        String userPassword = user.getPassword();
        Date createDate = user.getCreateDate();
        String createUserCd = user.getCreateUserCd();
        ReflectUtils.copyAllPropertiesByName(userVO, user, true);
        user.setCreateDate(createDate);
        user.setCreateUserCd(createUserCd);
        // 密码相同时，依旧使用原先的密码
        if (userPassword.equals(userVO.getPassword())) {
            user.setPassword(userPassword);
        } else {
            String hashUserPassword = userVO.getUserCd().concat(userVO.getPassword()).toString();
            user.setPassword(Md5Utils.hash(Md5Utils.hash((Md5Utils.hash(hashUserPassword)))));
        }

        user = userRepository.save(user);
        ReflectUtils.copyAllPropertiesByName(user, userVO, true);
        return userVO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PcfUserVO setUser(PcfUserVO userVO, PcfUserVO currentUserVO) {

        if (userVO.getUserId() == null) {
            // 取得用户实体，判断重复编号
            PcfUserVO userVOCheck = findByUserCd(userVO.getUserCd());
            if (userVOCheck != null) {
                return userVO;
            }
            Timestamp curTM = new Timestamp(System.currentTimeMillis());
            userVO.setRecordDate(curTM);
            userVO.setCreateDate(curTM);
            userVO.setCreateUserCd(currentUserVO.getUserCd());
            userVO.setRecordUserCd(currentUserVO.getUserCd());
            userVO = save(userVO);
        } else {
            Timestamp curTM = new Timestamp(System.currentTimeMillis());
            userVO.setRecordDate(DateUtil.getNowTime());
            userVO.setRecordUserCd(currentUserVO.getUserCd());
            userVO = update(userVO);
        }
        return userVO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PcfUserVO updatePassword(PcfUserVO pcfUserVO, String password) {
        // 设置重置的密码
        pcfUserVO.setPassword(password);
        return save(pcfUserVO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteUser(Long[] userIdList) {
        for (Long userId : userIdList) {
            // Delete user info
            delete(userId);
            // Delete role ath info
            deleteRoleAthByUserId(userId);
            // Delete department ath info
            deleteDepartmentAthByUserId(userId);
        }

    }

    /**
     * 根据用户ID删除用户组织信息。
     * 
     * @param userId
     *            用户ID
     */
    private void deleteDepartmentAthByUserId(Long userId) {
        // 删除用户组织所属表
        StringBuffer deleteDepartmentAthSql = new StringBuffer();
        deleteDepartmentAthSql
            .append(" DELETE ")
            .append(" FROM ")
            .append("     PCF_DEPARTMENT_ATH_T A")
            .append(" WHERE ")
            .append("     A.USER_ID= ?");
        departmentAthRepository.deleteBySql(deleteDepartmentAthSql.toString(), userId);
    }

    /**
     * 根据用户ID删除用户角色信息。
     * 
     * @param userId
     *            用户ID
     */
    private void deleteRoleAthByUserId(Long userId) {
        // 删除用户组织所属表
        StringBuffer deleteRoleAthSql = new StringBuffer();
        deleteRoleAthSql
            .append(" DELETE ")
            .append(" FROM ")
            .append("     PCF_ROLE_ATH_T A")
            .append(" WHERE ")
            .append("     A.USER_ID= ?");
        roleAthRepository.deleteBySql(deleteRoleAthSql.toString(), userId);
    }

    public PcfUserVO login(String username, String password) {

        FatalBizException.throwWhenTrue(
            StringUtils.isEmpty(username) || StringUtils.isEmpty(password),
            "用户名或密码不能为空");

        // 密码如果不在指定范围内 肯定错误
        FatalBizException.throwWhenTrue(
            password.length() < PcfUser.PASSWORD_MIN_LENGTH
                || password.length() > PcfUser.PASSWORD_MAX_LENGTH,
            "密码长度应在" + PcfUser.PASSWORD_MIN_LENGTH + "-" + PcfUser.PASSWORD_MAX_LENGTH + "位之间");

        PcfUser user = null;

        // 此处需要走代理对象，目的是能走缓存切面
        if (maybeUsername(username)) {
            user = findByUsername(username);
        }

        FatalBizException.throwWhenTrue(user == null, "用户名不存在");

        pcfPasswordService.validate(user, password);

        FatalBizException.throwWhenTrue(
            Boolean.TRUE.equals(PcfUser.DELETEFLAG_INVAILD.equals(user.getDeleteFlag())),
            "此用户已被禁用");

        PcfUserVO userVO = new PcfUserVO();
        ReflectUtils.copyAllPropertiesByName(user, userVO, true);
        return userVO;
    }

    public PcfUser findByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        return userRepository.findByUserCd(username);
    }

    private boolean maybeUsername(String username) {
        // 如果用户名不在指定范围内也是错误的
        if (username.length() < PcfUser.USERNAME_MIN_LENGTH
            || username.length() > PcfUser.USERNAME_MAX_LENGTH) {
            return false;
        }

        return true;
    }

    /**
     * 根据角色获取 权限字符串 如sys:admin
     * 
     * @param userId
     * @return
     */
    public Set<String> findStringPermissions(Long userId) {
        Set<String> permissions = Sets.newHashSet();

        Set<PcfRole> roles = roleService.findRoles(userId);
        for (PcfRole role : roles) {

            List<PcfRoleResourcePermission> roleResourcePermissions =
                roleResourcePermissionService.findByRole(role.getRoleId());
            for (PcfRoleResourcePermission rrp : roleResourcePermissions) {
                PcfResource resource = resourceService.findOne(rrp.getResourceId());
                if (resource == null) {
                    continue;
                }
                String actualResourceIdentity = resource.getIdentify();
                // 不可用 即没查到 或者标识字符串不存在
                if (StringUtils.isEmpty(actualResourceIdentity)
                    || PcfResource.DELETEFLAG_INVAILD.equals(resource.getDeleteFlag())) {
                    continue;
                }

                String[] permissionIds = rrp.getPermissionIds().split(",");
                for (String permissionId : permissionIds) {
                    PcfPermission permission =
                        permissionService.findOne(Long.valueOf(permissionId));

                    // 不可用
                    if (permission == null
                        || PcfPermission.DELETEFLAG_INVAILD.equals(permission.getDeleteFlag())) {
                        continue;
                    }
                    permissions.add(actualResourceIdentity + ":" + permission.getPermission());

                }
            }

        }

        return permissions;
    }

    @Override
    public PcfUserVO findByUserCd(String userCd) {
        PcfUser user = userRepository.findByUserCd(userCd);
        if (user == null) {
            return null;
        } else {
            PcfUserVO userVO = new PcfUserVO();
            ReflectUtils.copyAllPropertiesByName(user, userVO, true);
            return userVO;
        }
    }

    @Override
    public PcfUserVO findByUserId(Long userId) {
        PcfUser user = userRepository.findOne(userId);
        if (user == null) {
            return null;
        } else {
            PcfUserVO userVO = new PcfUserVO();
            ReflectUtils.copyAllPropertiesByName(user, userVO, true);
            return userVO;
        }
    }

    @Override
    public List<PcfUser> findByLikeUserCd(String userCd) {
        Searchable searchable = Searchable.newSearchable();
        searchable.addSearchParam("userCd_like", userCd);
        List<PcfUser> pcfUserList = findAllWithNoPageNoSort(searchable);
        return pcfUserList;
    }
    
	@Override
	public PcfUser findOneByUserCdAndUserPw(String userCd, String password) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select *  ")
		   .append(" from  ")
		   .append(" pcf_user_t  ")
		   .append(" where  ")
		   .append(" user_cd= \'"+userCd+"\'  ")
		   .append(" and password= \'"+password+"\'  ")
		   .append(" and delete_flag = 0 ");
		PcfUser pcfUser = this.findOneBySQL(sql.toString(), PcfUser.class);
		return pcfUser;
	}

}
