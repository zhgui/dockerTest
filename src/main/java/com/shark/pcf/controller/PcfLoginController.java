package com.shark.pcf.controller;

import com.alibaba.fastjson.JSON;
import com.shark.common.web.validate.AjaxResponse;
import com.shark.pcf.type.ResultType;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by win7 on 2014/12/21.
 */
@Controller
public class PcfLoginController {

    private final Logger logger = LoggerFactory.getLogger(PcfLoginController.class);


    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 如果用户直接到登录页面 先退出一下
        // 原因：isAccessAllowed实现是subject.isAuthenticated()---->即如果用户验证通过 就允许访问
        // 这样会导致登录一直死循环
        Subject subject = SecurityUtils.getSubject();
        if (subject != null && subject.isAuthenticated()) {
            subject.logout();
        }
        response.setHeader("isLogin", "0");
        genAppMsg(request, response);
        model.addAttribute("shiroLoginFailure", request.getAttribute("shiroLoginFailure"));
        return "login";
    }

    private void genAppMsg(HttpServletRequest request, HttpServletResponse response) {
        Exception shiroLoginFailure = (Exception) request.getAttribute("shiroLoginFailure");
        if (shiroLoginFailure != null) {
            logger.error(shiroLoginFailure.getMessage(), shiroLoginFailure);
        }
        String isAppLogin = request.getParameter("isAppLogin");
        if (StringUtils.isNotBlank(isAppLogin) && "1".equals(isAppLogin)) {
            logger.info("isAppLogin={}", isAppLogin);
            sendAjaxResponse(response, shiroLoginFailure);
        }
    }

    private void sendAjaxResponse(HttpServletResponse response, Exception shiroLoginFailure) {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = null;
        try {
            String jsonString = JSON.toJSONString(AjaxResponse.fail("登录失败，手机号或密码错误",ResultType.RESULT_ERROR, new ArrayList<Object>()));
            out = response.getWriter();
            out.println(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


}
