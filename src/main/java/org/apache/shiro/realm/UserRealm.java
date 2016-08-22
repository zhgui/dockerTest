package org.apache.shiro.realm;

import com.shark.pcf.service.PcfRoleResourcePermissionService;
import com.shark.pcf.service.PcfRoleService;
import com.shark.pcf.service.PcfUserService;
import com.shark.pcf.vo.PcfUserVO;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private PcfUserService userService;

    @Autowired
    private PcfRoleResourcePermissionService roleResourcePermissionService;

    @Autowired
    private PcfRoleService roleService;
//    @Autowired
//    private UserAuthService userAuthService;

    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        logger.info("读取【{}】授权信息", username);
        PcfUserVO userVO = userService.findByUserCd(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        authorizationInfo.setRoles(roleService.findStringRoles(userVO.getUserId()));
        logger.info("【{}】角色授权信息结束", username);

        authorizationInfo.setStringPermissions(roleResourcePermissionService.findStringPermissions(userVO.getUserId()));
        logger.info("【{}】权限授权信息结束", username);

        return authorizationInfo;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername().trim();
        logger.info("用户【{}】尝试登录", username);
        String password = "";
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
        }
        PcfUserVO userVO = userService.login(username, password);

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userVO.getUserCd(), userVO.getPassword().toCharArray(), null, getName());

        return authenticationInfo;
    }

}
