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

import com.shark.common.entity.search.Searchable;
import com.shark.common.plugin.web.controller.entity.ZTree;
import com.shark.common.query.PageConvertUtils;
import com.shark.common.query.ReportPage;
import com.shark.common.report.usermode.DocReport;
import com.shark.common.report.usermode.DocReportFactory;
import com.shark.common.utils.ExcelUtils;
import com.shark.common.web.bind.annotation.CurrentUser;
import com.shark.common.web.controller.BaseController;
import com.shark.common.web.validate.AjaxResponse;
import com.shark.pcf.entity.PcfDepartment;
import com.shark.pcf.entity.PcfDepartmentIncAth;
import com.shark.pcf.service.impl.PcfDepartmentAthServiceImpl;
import com.shark.pcf.service.impl.PcfDepartmentIncAthServiceImpl;
import com.shark.pcf.service.impl.PcfDepartmentServiceImpl;
import com.shark.pcf.service.impl.PcfRoleAthServiceImpl;
import com.shark.pcf.type.ResultType;
import com.shark.pcf.vo.PcfDepartmentVO;
import com.shark.pcf.vo.PcfUserVO;

/**
 * 组织控制层
 * 
 * @author shark
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/pcf/department")
public class PcfDepartmentController extends BaseController<PcfDepartment, Long> {

    @Autowired
    private PcfDepartmentServiceImpl departmentService;

    @Autowired
    private PcfRoleAthServiceImpl roleAthService;

    @Autowired
    private PcfDepartmentAthServiceImpl departmentAthService;

    @Autowired
    private PcfDepartmentIncAthServiceImpl departmentIncAthService;

    private final Logger logger = LoggerFactory.getLogger(PcfUserController.class);

    /**
     * 跳转到组织查询页面 。
     * 
     * @return 组织查询页面
     */
    @RequestMapping("/toList")
    public String toList() {
        return viewName("departmentList");
    }

    /**
     * 跳转到无所属组织操作页面 。
     * 
     * @return 无所属组织页面
     */
    @RequestMapping("/toDepartmentIncAth")
    public String toDepartmentIncAth(Long departmentId, Model model) {
        // 组织
        PcfDepartment department = new PcfDepartment();
        department.setDepartmentId(departmentId);
        model.addAttribute("department", department);

        return viewName("departmentIncAth");
    }

    /**
     * 跳转到组织树页面。
     * 
     * @return 组织树页面
     */
    @RequestMapping("/toTreeList")
    public String toTreeList(Model model) {
        getDepartmentTreeData(model);
        return viewName("departmentTreeList");
    }

    /**
     * 切换显示组织信息页面
     * 
     * @return 组织信息页面
     */
    @RequestMapping("/toDepartmentInfo")
    public String toDepartmentInfo(Long departmentId, Long parentDepartmentId, Model model) {
        PcfDepartment department = departmentService.findOne(departmentId);
        model.addAttribute("department", department);

        // 组织内包
        PcfDepartmentIncAth departmentIncAth = new PcfDepartmentIncAth();
        departmentIncAth.setParentDepartmentId(parentDepartmentId);
        model.addAttribute("departmentIncAth", departmentIncAth);

        return viewName("departmentInfo");
    }

    /**
     * 根据查询条件，取得组织列表。
     * 
     * @param parentDepartmentId
     *            夫组织ID
     * @param response
     *            {@link HttpServletResponse}
     */
    @RequestMapping("/departmentIncAthList")
    public void departmentIncAthList(int parentDepartmentId, Searchable searchable,
            HttpServletResponse response) {
        searchable.addSearchParam("C.PARENT_DEPARTMENT_ID_eq", parentDepartmentId);
        ReportPage reportPage = departmentIncAthService.findDepartmentListByDepartment(searchable);
        PageConvertUtils.convertToJqGrid(reportPage, response);
    }

    /**
     * 查找无所属的组织集合。
     * 
     * @param response
     *            {@link HttpServletResponse}
     */
    @RequestMapping("/departmentListWithOutIncAth")
    public void departmentListWithOutIncAth(Searchable searchable, HttpServletResponse response) {
        ReportPage reportPage = departmentService.findDepartmentListWithOutIncAth(searchable);
        PageConvertUtils.convertToJqGrid(reportPage, response);
    }

    /**
     * 根据父组织ID，取得当前组织的所有子组织列表。
     * 
     * @param searchable
     *            {@link Searchable}
     * @param response
     *            {@link HttpServletResponse}
     */
    @RequestMapping("/list")
    public void list(Searchable searchable, HttpServletResponse response) {
        ReportPage reportPage = departmentService.findByCond(searchable);
        PageConvertUtils.convertToJqGrid(reportPage, response);
    }

    /**
     * 根据查询条件，取得所属组织的用户列表。
     * 
     * @param searchable
     *            {@link Searchable}
     * @param response
     *            {@link HttpServletResponse}
     */
    @RequestMapping("/userAthList")
    public void userAthList(Searchable searchable, HttpServletResponse response) {
        ReportPage reportPage = departmentAthService.findUserAthListByCond(searchable);
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
        ReportPage reportPage = departmentService.findByCond(searchable);
        DocReport dr = new DocReportFactory().createReport();
        dr
            .title("组织列表")
            .header("组织编号", "组织名", "电子邮箱", "手机", "修改时间", "状态")
            .body(
                "department_cd",
                "department_name",
                "email_address1",
                "mobile_number",
                "record_date",
                "delete_flag")
            .dataMaps(reportPage.getResult());
        ExcelUtils.docReport2Web(dr, response);
    }

    /**
     * 页面跳转到新增组织页面。
     * 
     * @return 视图名称
     */
    @RequestMapping("/toAdd")
    public String toAdd(Long departmentId, Model model) {
        // 组织
        PcfDepartment department = new PcfDepartment();
        department.setDepartmentId(new Long(0));
        model.addAttribute("department", department);

        // 组织内包
        PcfDepartmentIncAth departmentIncAth = new PcfDepartmentIncAth();
        // 新增组织的情况下
        if (departmentId == null) {
            departmentIncAth.setParentDepartmentId(new Long(0));
        } else {
            departmentIncAth.setParentDepartmentId(departmentId);
        }

        model.addAttribute("departmentIncAth", departmentIncAth);

        return viewName("departmentAdd");
    }

    /**
     * 页面跳转到更新组织页面。
     * 
     * @param departmentId
     *            组织ID
     * @param model
     *            {@link Model}
     * @return 组织新增页面
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Long departmentId, Long parentDepartmentId, Model model) {
        PcfDepartment department = departmentService.findOne(departmentId);
        model.addAttribute("department", department);

        // 组织内包
        PcfDepartmentIncAth departmentIncAth = new PcfDepartmentIncAth();
        departmentIncAth.setParentDepartmentId(parentDepartmentId);

        model.addAttribute("departmentIncAth", departmentIncAth);

        return viewName("departmentAdd");
    }

    /**
     * 保存组织信息。
     * 
     * @param departmentVO
     *            {@link PcfDepartmentVO}
     * @param model
     *            {@link Model}
     * @return {@link AjaxResponse}
     */
    @RequestMapping("/setDepartment")
    @ResponseBody
    public AjaxResponse setDepartment(PcfDepartmentVO departmentVO, Model model,
            @CurrentUser PcfUserVO pcfUserVO) {
        try {
            departmentVO = departmentService.setDepartment(departmentVO, pcfUserVO.getUserCd());
            if (departmentVO.getDepartmentId() == null) {
                return AjaxResponse.fail("组织编号重复，请重新输入组织编号");
            } else {
                model.addAttribute("department", departmentVO);
                return AjaxResponse.success("组织添加/修正成功",ResultType.RESULT_OK, departmentVO);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResponse.fail(e.getMessage());
        }
    }

    /**
     * 给组织增加用户。
     * 
     * @param userIdList
     *            用户ID集合
     * @param departmentId
     *            组织ID
     * @param model
     *            {@link Model }
     * @return {@link AjaxResponse}
     */
    @RequestMapping("/toAddUserToDepartment")
    @ResponseBody
    public AjaxResponse toAddUserToDepartment(Long[] userIdList, Long departmentId, Model model,
            @CurrentUser PcfUserVO pcfUserVO) {
        try {
            departmentAthService.addUserToDepartment(userIdList, departmentId, pcfUserVO);
            return AjaxResponse.success("成功增加该组织的用户");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResponse.fail(e.getMessage());
        }
    }

    /**
     * 给组织增加子组织。
     * 
     * @param departmentIdList
     *            组织ID集合
     * @param parentDepartmentId
     *            父组织ID
     * @return {@link AjaxResponse}
     */
    @RequestMapping("/toAddDepartmentToIncAth")
    @ResponseBody
    public AjaxResponse toAddDepartmentToIncAth(Long[] departmentIdList, Long parentDepartmentId,
            @CurrentUser PcfUserVO pcfUserVO) {
        try {
            departmentService.addDepartmentToIncAth(
                departmentIdList,
                parentDepartmentId,
                pcfUserVO.getUserCd());
            return AjaxResponse.success("成功增加该组织的子组织");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResponse.fail(e.getMessage());
        }
    }

    /**
     * 移除所属组织。
     * 
     * @param departmentIdList
     *            组织ID集合
     * @param model
     *            {@link Model}
     * @return {@link AjaxResponse}
     */
    @RequestMapping("/toDeleteDepartmentIncAth")
    @ResponseBody
    public AjaxResponse toDeleteDepartmentIncAth(Long[] departmentIdList, Model model) {
        try {
            // departmentAthService.deleteDepartmentAth(departmentAthIdList);
            return AjaxResponse.success("成功移除该组织的子组织");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResponse.fail(e.getMessage());
        }
    }

    /**
     * 删除组织的所属用户。
     * 
     * @param departmentAthIdList
     *            用户组织所属ID集合
     * @param model
     *            {@link Model}
     * @return {@link AjaxResponse}
     */
    @RequestMapping("/toDeleteDepartmentAth")
    @ResponseBody
    public AjaxResponse toDeleteDepartmentAth(Long[] departmentAthIdList, Model model) {
        try {
            departmentAthService.deleteDepartmentAth(departmentAthIdList);
            return AjaxResponse.success("成功删除所属该组织的用户");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResponse.fail(e.getMessage());
        }
    }

    /**
     * 彻底删除组织及其下属组织。
     * 
     * @param departmentId
     *            组织ID
     * @param model
     *            {@link Model}
     * @return 组织树页面
     */
    @RequestMapping("/toDeleteDepartment")
    public String toDeleteDepartment(Long departmentId, Model model) {
        departmentService.deleteDepartment(departmentId);
        getDepartmentTreeData(model);
        return viewName("departmentTreeList");
    }

    /**
     * 取得组织树的数据。
     * 
     * @param model
     *            {@link Model}
     */
    private void getDepartmentTreeData(Model model) {
        // 查出系统中所有资源，组成ZTREE树状结构
        List<ZTree<Long>> zTrees = departmentAthService.findTreeData();
        model.addAttribute("zTrees", zTrees);
    }

    /**
     * 移除组织及其下属组织。
     * 
     * @param departmentId
     *            组织ID
     * @param model
     *            {@link Model}
     * @return 组织树页面
     */
    @RequestMapping("/toRemoveDepartment")
    public String toRemoveDepartment(Long departmentId, Model model) {
        departmentService.removeDepartment(departmentId);
        getDepartmentTreeData(model);
        return viewName("departmentTreeList");
    }

}
