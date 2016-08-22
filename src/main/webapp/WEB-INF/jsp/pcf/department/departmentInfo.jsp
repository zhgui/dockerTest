<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" role="form" id="departmentInfoForm">
            <input name="departmentId" value="${department.departmentId}" type="hidden">
            <input name="parentDepartmentId" value="${departmentIncAth.parentDepartmentId}" type="hidden">
            <input name="deleteFlagHidden" value="${department.deleteFlag}" type="hidden">
            <div class="space-4"></div>

            <!-- #section:elements.form -->
            <!-- 组织编号 -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="departmentCd"> 组织编号<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="departmentCd" name="departmentCd" value="${department.departmentCd}" placeholder="组织编号" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <!-- 组织名 -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="departmentName"> 组织名 </label>
                <div class="col-sm-9">
                    <input type="text" id="departmentName" name="departmentName"  value="${department.departmentName}" placeholder="组织名" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <!-- 组织简称 -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="departmentShortName"> 组织简称 </label>
                <div class="col-sm-9">
                    <input type="text" id="departmentShortName" name="departmentShortName" value="${department.departmentShortName}" placeholder="组织简称" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <!-- 组织检索名 -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="departmentSearchName"> 组织检索名 </label>
                <div class="col-sm-9">
                    <input type="text" id="departmentSearchName" name="departmentSearchName" value="${department.departmentSearchName}" placeholder="组织检索名" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <!-- 国家编码 -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for=countryCd>国家编码</label>
                <div class="col-sm-9">
                    <input type="text" id="countryCd" name="countryCd" value="${department.countryCd}" placeholder="国家编码" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <!-- 邮政编号 -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for=zipCode>邮政编号</label>
                <div class="col-sm-9">
                    <input type="text" id="zipCode" name="zipCode" value="${department.zipCode}" placeholder="邮政编号" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <!-- 地址1 -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for=address1>地址1</label>
                <div class="col-sm-9">
                    <input type="text" id="address1" name="address1" value="${department.address1}" placeholder="地址1" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <!-- 地址2 -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for=address2>地址2</label>
                <div class="col-sm-9">
                    <input type="text" id="address2" name="address2" value="${department.address2}" placeholder="地址2" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <!-- 地址3 -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for=address3>地址3</label>
                <div class="col-sm-9">
                    <input type="text" id="address3" name="address3" value="${department.address3}" placeholder="地址3" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <!-- 电话号码--分机号 -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for=telephoneNumber>电话号码</label>
                <div class="col-sm-9">
                    <input type="text" id="telephoneNumber" name="telephoneNumber" value="${department.telephoneNumber}" placeholder="电话号码" class="col-xs-10 col-sm-5"/>
                    <label class="col-sm-1 control-label no-padding-right" for=extensionNumber>分机号</label>
                    <input type="text" id="extensionNumber" name="extensionNumber" value="${department.extensionNumber}" placeholder="分机号" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <!-- 传真号--分机号 -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for=faxNumber>传真号</label>
                <div class="col-sm-9">
                    <input type="text" id="faxNumber" name="faxNumber" value="${department.faxNumber}" placeholder="传真号" class="col-xs-10 col-sm-5"/>
                    <label class="col-sm-1 control-label no-padding-right" for=extensionNumber>传真分机号</label>
                    <input type="text" id="extensionFaxNumber" name="extensionFaxNumber" value="${department.extensionFaxNumber}" placeholder="传真分机号" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <!-- 邮件地址1/2 -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="emailAddress1"> 邮件地址1 </label>
                <div class="col-sm-9">
                    <input type="text" id="emailAddress1" name="emailAddress1" value="${department.emailAddress1}" placeholder="邮件地址1" class="col-xs-10 col-sm-5"/>
                    <label class="col-sm-1 control-label no-padding-right" for="emailAddress2"> 邮件地址2 </label>
                    <input type="text" id="emailAddress2" name="emailAddress2" value="${department.emailAddress2}" placeholder="邮件地址2" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <!-- 备注 -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="notes">备注</label>
                <div class="col-sm-6">
                    <textarea class="form-control limited" id="notes" name="notes" placeholder="备注" maxlength="500">${department.notes}</textarea>
                </div>
            </div>
            <!-- 排序键 -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="sortKey">排序键<span style="color: red">*</span></label>
                <div class="col-sm-1">
                    <input type="text" id="sortKey" name="sortKey" value="${department.sortKey}" placeholder="排序键" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <!-- 组织状态-->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="deleteFlag"> 状态 </label>
                <div class="col-sm-9">
                    <span>
                        <input id="trueFlag" name="deleteFlag" type="radio" class="ace" value="0" checked/>
                        <span class="lbl"> 有效 </span>
                    </span>
                    <span>
                        <input id="falseFlag" name="deleteFlag" type="radio" class="ace" value="1"/>
                        <span class="lbl"> 无效 </span>
                    </span>
                </div>
            </div>
            <!--查询条件按钮-->
            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info saveBtn"
                        type="button">
                        <i class="ace-icon fa fa-check bigger-110"></i>
                        提交
                        
                    </button>
                    &nbsp; &nbsp; &nbsp;
                    <button class="btn" type="reset">
                        <i class="ace-icon fa fa-undo bigger-110"></i>
                        重置
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        //console.debug("组织信息页面的父亲节点ID:"+$("input[name='parentDepartmentId']").val());
    
        // 页面初期化
        // 画面模式（新增·修正）判断
        // 组织编号有值的时候，判定为修正模式
        if($("input[name='departmentId']").val() != ""){
            // 组织编号设定为只读，不能编辑
            $("input[name='departmentCd']").attr("readonly","readonly");
        }else{
            $("input[name='sortKey']").val("0");
        }
        
        // 根据返回值设定状态单选框
        if($("input[name='deleteFlagHidden']").val()=="0"){
            $("#trueFlag").attr("checked",true);
        }else if($("input[name='deleteFlagHidden']").val()=="1"){
            $("#falseFlag").attr("checked",true);
        }
        
        // 页面项目验证
        $('#departmentInfoForm').validate(jValidateOption({
            rules: {
                departmentCd: {
                    required: true
                },
                departmentName: {
                    maxlength: 50
                },
                departmentShortName: {
                    maxlength: 30
                },
                departmentSearchName: {
                    maxlength: 30
                },
                countryCd: {
                    maxlength: 10
                },
                zipCode: {
                    maxlength: 30
                },
                address1: {
                    maxlength: 50
                },
                address2: {
                    maxlength: 50
                },
                address3: {
                    maxlength: 50
                },
                telephoneNumber: {
                    maxlength: 20,
                    number: true
                },
                extensionNumber: {
                    maxlength: 10,
                    number: true
                },
                faxNumber: {
                    maxlength: 30,
                    number: true
                },
                extensionFaxNumber: {
                    maxlength: 20,
                    number: true
                },
                mobileNumber: {
                    maxlength: 10,
                    number: true
                },
                emailAddress1: {
                    email: true,
                    maxlength: 30
                },
                emailAddress2: {
                    email: true,
                    maxlength: 30
                },
                notes: {
                    maxlength: 250
                },
                sortKey: {
                    required: true,
                    number: true,
                    maxlength: 5
                }
            },

            messages: {
                departmentCd: {
                    //required: "请输入组织编号"
                },
                sortKey: {
                    //required: "请输入排序键"
                }
            }
        }));
        
        // 保存操作
        $(".saveBtn").on("click", function () {
            //console.debug("组织信息页面的保存操作开始");
            // 校验通过的情况下，执行登录/更新操作
            if (!$('#departmentInfoForm').valid()) {
                return false;
            }
            doPost("${ctx}/pcf/department/setDepartment", $("#departmentInfoForm").serialize(), function (data) {
                if (data.success) {
                    addGritter("success", data.message);
                    //document.location.href = "${ctx}/pcf/permission/toUpdate?permissionId=" + data.data.permissionId;
                } else {
                    addGritter("error", data.message);
                }
            }, "json")
            //console.debug("组织信息页面的保存操作顺利结束");
        });
    });
</script>
</body>
</html>