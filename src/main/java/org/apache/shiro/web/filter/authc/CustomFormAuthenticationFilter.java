
package org.apache.shiro.web.filter.authc;

import com.shark.pcf.service.PcfUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 基于几点修改：
 * 1、onLoginFailure 时 把异常添加到request attribute中 而不是异常类名
 * 2、登录成功时：成功页面重定向：
 * 2.1、如果前一个页面是登录页面，-->2.3
 * 2.2、如果有SavedRequest 则返回到SavedRequest
 * 2.3、否则根据当前登录的用户决定返回到管理员首页/前台首页
 * <p/>
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    public final static String IS_APP_LOGIN = "isAppLogin";


    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        request.setAttribute(getFailureKeyAttribute(), ae);
    }

    /**
     * 默认的成功地址
     */
    private String defaultSuccessUrl;
    /**
     * 管理员默认的成功地址
     */
    private String adminDefaultSuccessUrl;

    /**
     * 管理员默认的成功地址
     */
    private String appDefaultSuccessUrl;


    public void setDefaultSuccessUrl(String defaultSuccessUrl) {
        this.defaultSuccessUrl = defaultSuccessUrl;
    }

    public void setAdminDefaultSuccessUrl(String adminDefaultSuccessUrl) {
        this.adminDefaultSuccessUrl = adminDefaultSuccessUrl;
    }

    public void setAppDefaultSuccessUrl(String appDefaultSuccessUrl) {
        this.appDefaultSuccessUrl = appDefaultSuccessUrl;
    }

    public String getDefaultSuccessUrl() {
        return defaultSuccessUrl;
    }

    public String getAdminDefaultSuccessUrl() {
        return adminDefaultSuccessUrl;
    }

    public String getAppDefaultSuccessUrl() {
        return appDefaultSuccessUrl;
    }

    /**
     * 根据用户选择成功地址
     *
     * @return
     */
    @Override
    public String getSuccessUrl() {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        //User user = userService.findByUsername(username);
        //if (user != null && Boolean.TRUE.equals(user.getAdmin())) {
        //     return getAdminDefaultSuccessUrl();
        //}
        return getDefaultSuccessUrl();
    }

    /**
     * 判断是否是APP登录，是APP登录则返回登录成功信息
     */
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {
        String isAppLogin = request.getParameter(IS_APP_LOGIN);
        if (StringUtils.isNotBlank(isAppLogin) && "1".equals(isAppLogin)) {
            WebUtils.redirectToSavedRequest(request, response, getAppDefaultSuccessUrl());
        } else {
            WebUtils.redirectToSavedRequest(request, response, getSuccessUrl());
        }
        return false;
    }

    /**
     * 判断是否是APP登录，是APP登录失败则返回失败信息
     */

    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
                                     ServletRequest request, ServletResponse response) {

        setFailureAttribute(request, e);
        //login failed, let request continue back to the login page:
        return true;
    }

}
