package com.shark.pcf.controller;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shark.common.web.bind.annotation.CurrentUser;
import com.shark.common.web.validate.AjaxResponse;
import com.shark.pcf.service.PcfResourceService;
import com.shark.pcf.service.PcfUserService;
import com.shark.pcf.type.ResultType;
import com.shark.pcf.vo.MenuVO;
import com.shark.pcf.vo.PcfUserVO;

/**
 * Created by win7 on 2014/11/25.
 */
@Controller
public class PcfIndexController {
    private final Logger logger = LoggerFactory.getLogger(PcfIndexController.class);


    @Autowired
    private PcfResourceService resourceService;

    @Autowired
    private PcfUserService userService;

//    @Autowired
//    private BaSignInExternal baSignInExternal;

    @RequestMapping("/{index}")
    public String index(@CurrentUser PcfUserVO userVO, Model model) {
        // 查找菜单
        List<MenuVO> menus = resourceService.findMenus(userVO.getUserId());
        model.addAttribute("menus", menus);
        return "main";
    }

    @RequestMapping("/appIndex")
    @ResponseBody
    public AjaxResponse appIndex(@CurrentUser PcfUserVO userVO, HttpServletResponse response) {
        response.setHeader("isLogin", "1");
        String sql = "select * from BA_USER_DETAIL_T t where t.USER_DETAIL_ID = ?";
//        BaUserDetailVo detailVo = userService.findOneBySQL(sql, BaUserDetailVo.class, userVO.getUserId());
//        //查询今天是否已经签到
//        Boolean isSignIn = baSignInExternal.isSignInByUserId(userVO.getUserId());
//        detailVo.setIsSignIn(isSignIn);
//        if (detailVo != null) {
//            ReflectUtils.copyAllPropertiesByName(detailVo, userVO, false);
//            setEmptyValue(userVO);
//        } else {
//            Subject subject = SecurityUtils.getSubject();
//            subject.logout();
//            return AjaxResponse.fail("登录失败，用户【{}】无用户详细信息", new Object[]{userVO});
//        }
        logger.info("用户【{}】登录成功", userVO.getUserCd());
        return AjaxResponse.success("登录成功",ResultType.RESULT_OK, new Object[]{userVO});
    }

    private void setEmptyValue(PcfUserVO userVO) {
        userVO.setPassword(null);     
        userVO.setCreateUserId(null);
        userVO.setCreateTime(null);
        userVO.setRecordDate(null);
        userVO.setRecordUserCd(null);
        userVO.setModifyTime(null);
        userVO.setModifyUserId(null);
        userVO.setSortKey(null);
        userVO.setCreateDate(null);
        userVO.setCreateUserCd(null);
        userVO.setCreateUserCd(null);
    }

}
