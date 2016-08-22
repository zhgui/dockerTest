<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" role="form" id="userInfoForm">
            <input name="userId" value="${user.userId}" type="hidden">
            <input name="deleteFlagHidden" value="${user.deleteFlag}" type="hidden">
            <div class="space-4"></div>

            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="userCd"> 用户编号<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="text" id="userCd" name="userCd" value="${user.userCd}" placeholder="用户编号" class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="password"> 密码<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="password" id="password" name="password" value="${user.password}" placeholder="密码" class="col-xs-10 col-sm-5"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label no-padding-right" for="passwordConfirm"> 确认密码<span style="color: red">*</span></label>
                <div class="col-sm-9">
                    <input type="password" id="passwordConfirm" name="passwordConfirm" value="${user.password}" placeholder="密码确认" class="col-xs-10 col-sm-5"/>
                </div>
            </div>

            <!-- /section:elements.form -->
            <div class="space-4"></div>

            <div class="space-4"></div>
            
            <div class="space-4"></div>

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
        // 页面初期化
        // 用户编号有值的时候，判定为修正模式，用户编号不可编辑
        //console.debug("用户信息页面初期化");
        //console.debug($("input[name='userCd']").val());
        if($("input[name='userCd']").val() != ""){
            //console.debug("设定用户编号不可用");
            //console.debug($("input[name='userCd']"));
            $("input[name='userCd']").attr("readonly","readonly");
        }
        // 根据返回值设定状态单选框
        if($("input[name='deleteFlagHidden']").val()=="0"){
            $("#trueFlag").attr("checked",true);
        }else if($("input[name='deleteFlagHidden']").val()=="1"){
            $("#falseFlag").attr("checked",true);
        }
        
        //console.debug("sex");
        //console.debug($("input[name='sexHidden']").val());
        // 根据返回值设定状态单选框,设定性别单选框
        /* if($("input[name='sexHidden']").val()=="0"){
            $("#man").attr("checked",true);
        }else if($("input[name='sexHidden']").val()=="1"){
            $("#female").attr("checked",true);
        } */
        
        // 页面项目验证
        $('#userInfoForm').validate(jValidateOption({
            rules: {
                userCd: {
                    required: true,
                    minlength:2,
                    maxlength:20
                },
                password: {
                    required: true,
                    equalTo: "#passwordConfirm",
                    maxlength:32
                },
                passwordConfirm: {
                    required: true,
                    equalTo: "#password"
                }
            },

            messages: {
                userCd: {
                    //required: "请输入用户编号"
                },
                password: {
                    //required: "请输入密码"
                    //equalTo:"密码和确认密码必须一致"
                },
                passwordConfirm: {
                    required: "请再次输入密码",
                    equalTo:"密码和确认密码必须一致"
                }
            }
        }));
        
        // 保存操作
        $(".saveBtn").on("click", function () {
            //console.debug("用户信息页面的保存操作开始");
            // 校验通过的情况下，执行登录/更新操作
            if (!$('#userInfoForm').valid()) {
                return false;
            }
            doPost("${ctx}/pcf/user/setUser", $("#userInfoForm").serialize(), function (data) {
                if (data.success) {
                    addGritter("success", data.message);
                    //console.debug(data);
                    $("input[name='userId']").val(data.responseData)
                    document.location.href = "${ctx}/pcf/user/toUpdate?userId=" + data.responseData;
                } else {
                    addGritter("error", data.message);
                }
            }, "json")
        });
    });
</script>
</body>
</html>