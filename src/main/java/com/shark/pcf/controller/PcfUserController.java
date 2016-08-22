 package com.shark.pcf.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shark.common.entity.search.Searchable;
import com.shark.common.query.PageConvertUtils;
import com.shark.common.query.ReportPage;
import com.shark.common.report.usermode.DocReport;
import com.shark.common.report.usermode.DocReportFactory;
import com.shark.common.utils.ExcelUtils;
import com.shark.common.utils.security.Md5Utils;
import com.shark.common.web.bind.annotation.CurrentUser;
import com.shark.common.web.controller.BaseController;
import com.shark.common.web.validate.AjaxResponse;
import com.shark.pcf.entity.PcfUser;
import com.shark.pcf.service.PcfDepartmentAthService;
import com.shark.pcf.service.PcfRoleAthService;
import com.shark.pcf.service.PcfUserService;
import com.shark.pcf.type.ResultType;
import com.shark.pcf.vo.PcfUserVO;

/**
 * 用户控制层
 * 
 * @since 1.0
 * @author shark
 * @version 1.0
 */
@Controller
@RequestMapping("/pcf/user")
public class PcfUserController extends BaseController<PcfUser, Long> {

    @Autowired
    private PcfUserService userService;

    @Autowired
    private PcfRoleAthService roleAthService;

    @Autowired
    private PcfDepartmentAthService departmentAthService;

    private final Logger logger = LoggerFactory.getLogger(PcfUserController.class);

    /**
     * 跳转到用户查询页面。
     * 
     * @return 用户查询页面
     */
    @RequestMapping("/toList")
    public String toList() {
        return viewName("userList");
    }

    /**
     * 切换显示用户信息页面
     * 
     * @return 用户信息页面
     */
    @RequestMapping("/toUserInfo")
    public String toUserInfo(Long userId, Model model) {
        PcfUser user = userService.findOne(userId);
        model.addAttribute("user", user);
        return viewName("userInfo");
    }

    /**
     * 切换显示用户角色所属信息页面。
     * 
     * @return 用户角色所属信息页面
     */
    @RequestMapping("/toRoleAthInfo")
    public String toRoleInfo(Long userId, Model model) {
        PcfUser user = new PcfUser();
        user.setUserId(userId);
        model.addAttribute("user", user);
        return viewName("roleAthInfo");
    }

    /**
     * 切换显示组织所属页面。
     * 
     * @return 组织所属页面
     */
    @RequestMapping("/toDepartmentAthInfo")
    public String toDepartmentAthInfo(Long userId, Model model) {
        PcfUser user = new PcfUser();
        user.setUserId(userId);
        model.addAttribute("user", user);
        return viewName("departmentAthInfo");
    }

    /**
     * 根据查询条件，取得查询结果。
     * 
     * @param searchable
     *            {@link Searchable}
     * @param response
     *            {@link HttpServletResponse}
     */
    @RequestMapping("/list")
    public void list(Searchable searchable, HttpServletResponse response) {
        ReportPage reportPage = userService.findByCond(searchable);
        PageConvertUtils.convertToJqGrid(reportPage, response);
    }

    /**
     * 根据用户ID，取得该用户所拥有的角色。
     * 
     * @param userId
     *            用户ID
     * @param searchable
     *            {@link Searchable}
     * @param response
     *            {@link HttpServletResponse}
     */
    @RequestMapping("/roleAthList")
    public void roleAthList(String userId, Searchable searchable, HttpServletResponse response) {
        searchable.addSearchParam("USER_ID_eq", userId);
        ReportPage reportPage = roleAthService.findByCond(searchable);
        PageConvertUtils.convertToJqGrid(reportPage, response);
    }

    /**
     * 根据用户ID，取得该用户所属于的组织。
     * 
     * @param userId
     *            用户ID
     * @param searchable
     *            {@link Searchable}
     * @param response
     *            {@link HttpServletResponse}
     */
    @RequestMapping("/departmentAthList")
    public void departmentAthList(String userId, Searchable searchable, HttpServletResponse response) {
        searchable.addSearchParam("USER_ID_eq", userId);
        ReportPage reportPage = departmentAthService.findByCond(searchable);
        PageConvertUtils.convertToJqGrid(reportPage, response);
    }

    /**
     * 根据查询条件，取得查询结果后，导出数据。
     * 
     * @param searchable
     *            {@link Searchable}
     * @param response
     *            {@link HttpServletResponse}
     * @throws Exception
     */
    @RequestMapping("/listExport")
    public void export(Searchable searchable, HttpServletResponse response) throws Exception {
        ReportPage reportPage = userService.findByCond(searchable);
        DocReport dr = new DocReportFactory().createReport();
        dr
            .title("用户列表")
            .header("用户编号", "用户名", "电子邮箱", "手机", "修改时间", "状态")
            .body(
                "user_cd",
                "user_name",
                "email_address1",
                "mobile_number",
                "record_date",
                "delete_flag")
            .dataMaps(reportPage.getResult());
        ExcelUtils.docReport2Web(dr, response);
    }

    /**
     * 页面跳转到用户增加。
     * 
     * @return 视图名称
     */
    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        PcfUser user = new PcfUser();
        user.setUserId(new Long(0));
        model.addAttribute("user", user);
        return viewName("userAdd");
    }

    /**
     * 更新指定用户信息。
     * 
     * @param userId
     *            用户ID
     * @param model
     *            {@link Model}
     * @return 新增用户页面
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Long userId, Model model) {
        PcfUser user = userService.findOne(userId);
        model.addAttribute("user", user);
        return viewName("userAdd");
    }

    /**
     * 彻底删除用户。
     * 
     * @param userIdList
     *            用户ID集合
     * @param model
     *            {@link Model}
     * @return {@link AjaxResponse}
     */
    @RequestMapping("/toDeleteUser")
    @ResponseBody
    public AjaxResponse toDeleteUser(Long[] userIdList, Model model) {
        try {
            userService.deleteUser(userIdList);
            return AjaxResponse.success("成功彻底删除用户");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResponse.fail(e.getMessage());
        }
    }

    /**
     * 删除用户的所属组织。
     * 
     * @param departmentAthIdList
     *            用户组织所属ID集合
     * @param userId
     *            用户ID
     * @param model
     *            {@link Model}
     * @return {@link AjaxResponse}
     */
    @RequestMapping("/toDeleteDepartmentAth")
    @ResponseBody
    public AjaxResponse toDeleteDepartmentAth(Long[] departmentAthIdList, Long userId, Model model) {
        try {
            departmentAthService.delete(departmentAthIdList);
            PcfUser user = userService.findOne(userId);
            model.addAttribute("user", user);
            return AjaxResponse.success("成功删除用户的所属组织",ResultType.RESULT_OK, user.getUserId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResponse.fail(e.getMessage());
        }
    }

    /**
     * 删除用户的所属角色。
     * 
     * @param roleAthIdList
     *            用户角色所属ID集合
     * @param userId
     *            用户ID
     * @param model
     *            {@link Model}
     * @return {@link AjaxResponse}
     */
    @RequestMapping("/toDeleteRoleAth")
    @ResponseBody
    public AjaxResponse toDeleteRoleAth(Long[] roleAthIdList, Long userId, Model model) {
        try {
            roleAthService.delete(roleAthIdList);
            PcfUser user = userService.findOne(userId);
            model.addAttribute("user", user);
            return AjaxResponse.success("成功删除用户的角色",ResultType.RESULT_OK, user.getUserId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResponse.fail(e.getMessage());
        }
    }

    /**
     * 给用户的增加角色。
     * 
     * @param roleIdList
     *            角色ID集合
     * @param userId
     *            用户ID
     * @param model
     *            {@link Model}
     * @return {@link AjaxResponse}
     */
    @RequestMapping("/toAddRoleToUser")
    @ResponseBody
    public AjaxResponse toAddRoleToUser(Long[] roleIdList, Long userId, Model model,
            @CurrentUser PcfUserVO pcfUserVO) {
        try {
            roleAthService.addRoleToUser(roleIdList, userId, pcfUserVO);
            PcfUser user = userService.findOne(userId);
            model.addAttribute("user", user);
            return AjaxResponse.success("成功增加用户的角色", ResultType.RESULT_OK,user.getUserId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResponse.fail(e.getMessage());
        }
    }

    /**
     * 给用户的增加组织。
     * 
     * @param departmentIdList
     *            组织ID集合
     * @param userId
     *            用户ID
     * @param model
     *            {@link Model}
     * @return {@link AjaxResponse}
     */
    @RequestMapping("/toAddDepartmentToUser")
    @ResponseBody
    public AjaxResponse toAddDepartmentToUser(Long[] departmentIdList, Long userId, Model model,
            @CurrentUser PcfUserVO pcfUserVO) {
        try {
            departmentAthService.addDepartmentToUser(departmentIdList, userId, pcfUserVO);
            PcfUser user = userService.findOne(userId);
            model.addAttribute("user", user);
            return AjaxResponse.success("成功增加用户的所属组织",ResultType.RESULT_OK, user.getUserId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResponse.fail(e.getMessage());
        }
    }

    /**
     * 给用户设置主所属组织。
     * 
     * @param departmentAthIdList
     *            组织所属ID集合
     * @param userId
     *            用户ID
     * @param model
     *            {@link Model}
     * @return {@link AjaxResponse}
     */
    @RequestMapping("/toSetDepartmentMainToUser")
    @ResponseBody
    public AjaxResponse toSetDepartmentMainToUser(Long[] departmentAthIdList, Long userId,
            Model model) {
        try {
            departmentAthService.setDepartmentMainToUser(departmentAthIdList, userId);
            PcfUser user = userService.findOne(userId);
            model.addAttribute("user", user);
            return AjaxResponse.success("成功设置用户的主所属组织", ResultType.RESULT_OK,user.getUserId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResponse.fail(e.getMessage());
        }
    }

    /**
     * 保存用户信息。
     * 
     * @param userVO
     *            {@link PcfUserVO}
     * @param model
     *            {@link Model}
     * @return 视图名称
     */
    @RequestMapping("/setUser")
    @ResponseBody
    public AjaxResponse setUser(PcfUserVO userVO, Model model, @CurrentUser PcfUserVO currentUser) {
        try {
            userVO = userService.setUser(userVO, currentUser);
            if (userVO.getUserId() == null) {
                return AjaxResponse.fail("用户编号重复，请重新输入用户编号");
            } else {
                model.addAttribute("user", userVO);
                return AjaxResponse.success("用户添加/修正成功",ResultType.RESULT_OK, userVO.getUserId());
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResponse.fail(e.getMessage());
        }
    }

    @RequestMapping("/updateUserPw")
    @ResponseBody
    public AjaxResponse updateUserPw(@CurrentUser PcfUserVO userVO, Model model,String oldPassword,String newPassword, HttpServletRequest request, HttpServletResponse response) {
        try {
            String hashUserPassword = userVO.getUserCd().concat(oldPassword).toString();
            String mdPw = Md5Utils.hash(Md5Utils.hash((Md5Utils.hash(hashUserPassword))));
            if(userVO.getPassword().equals(mdPw)==false){
                return AjaxResponse.fail("原密码不正确", ResultType.RESULT_ERROR,userVO.getUserId());
            }else{
                userVO.setPassword(newPassword);
                userService.update(userVO);
                return AjaxResponse.success("修改成功", ResultType.RESULT_OK,userVO.getUserId());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResponse.fail(e.getMessage());
        }
    }

}
