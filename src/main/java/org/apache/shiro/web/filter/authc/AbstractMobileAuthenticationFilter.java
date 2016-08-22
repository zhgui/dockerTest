package org.apache.shiro.web.filter.authc;

import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by win7 on 2015/4/27.
 */
public abstract class AbstractMobileAuthenticationFilter extends AuthenticationFilter {

	//APP端请求URL中附带的token字段名
    public static final String TOKEN = "token";
    //APP端请求URL中附带的账号名
    public static final String USER_CD = "userCd";
    //APP端请求的URL中附带的密码名
    public static final String USER_PW = "password";
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(AbstractMobileAuthenticationFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request,
                                     ServletResponse response) throws Exception {

        logger.info("移动用户进入校验！" + getLoginUrl());

        HttpServletRequest req = (HttpServletRequest) request;

        String token = req.getParameter(TOKEN);
        logger.info("dada_token = " + token);
        if (isAccess(token)) {
            return onAccessSuccess(req, (HttpServletResponse) response);
        }

        return onAccessFail(req, (HttpServletResponse) response);
    }

    /**
     * 判断token的合法性
     *
     * @param token
     * @return
     */
    public abstract boolean isAccess(String token);

    /**
     * 认证成功进行的操作处理
     *
     * @param request
     * @param response
     * @return true 继续后续处理，false 不需要后续处理
     */
    public abstract boolean onAccessSuccess(HttpServletRequest request,
                                            HttpServletResponse response);
    /**
     * 认证失败时处理结果
     *
     * @param request
     * @param response
     * @return true 继续后续处理，false 不需要后续处理
     */
    public abstract boolean onAccessFail(HttpServletRequest request,
                                         HttpServletResponse response);
    
    /**
     * 认证密码失败时处理结果
     *
     * @param request
     * @param response
     * @return true 继续后续处理，false 不需要后续处理
     */
    public abstract boolean onAccessFailPassword(HttpServletRequest request,
                                         HttpServletResponse response);
    
    /**
     * 当前账号已被禁用
     * @param request
     * @param response
     * @return
     */
    public abstract boolean onAccessFailUserStatus(HttpServletRequest request,
            HttpServletResponse response);
    
    /**
     * 认证用户名登录用户名失败时处理结果
     *
     * @param request
     * @param response
     * @return true 继续后续处理，false 不需要后续处理
     */
    public abstract boolean onAccessFailUserCd(HttpServletRequest request,
                                         HttpServletResponse response);

}