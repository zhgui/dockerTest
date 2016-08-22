<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" role="form" id="resourceUpdateForm" name="resourceUpdateForm">
            <input type="hidden" name="resourceId" value="${resource.resourceId}"/>
            <input type="hidden" name="parentId" value="${resource.parentId}"/>

            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="parentName"> 父资源 </label>

                <div class="col-sm-9">
                    <input type="text" id="parentName" name="parentName" placeholder="父资源"
                           value="${resource.parentName}"
                           class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="resourceName"> 资源名称 </label>

                <div class="col-sm-9">
                    <input type="text" id="resourceName" name="resourceName" placeholder="资源名称"
                           value="${resource.resourceName}"
                           class="col-xs-10 col-sm-5"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="identify"> 资源标识符 </label>

                <div class="col-sm-9">
                    <input type="text" id="identify" name="identify" placeholder="资源标识符" value="${resource.identify}"
                           class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="url"> 资源URL </label>

                <div class="col-sm-9">
                    <input type="text" id="url" name="url" value="${resource.url}" placeholder="资源URL"
                           class="col-xs-10 col-sm-5"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="url"> 资源图标 </label>

                <div class="col-sm-9">
                    <input type="text" id="icon" name="icon" value="${resource.icon }" placeholder="资源图标，暂未实现"  
                           class="col-xs-10 col-sm-5"/>
                </div>
            </div>


            <!-- /section:elements.form -->
            <div class="space-4"></div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="sortKey"> 资源排序 </label>

                <div class="col-sm-9">
                    <input type="text" id="sortKey" name="sortKey" placeholder="资源排序" value="${resource.sortKey}"
                           class="col-xs-10 col-sm-5"/>
                </div>
            </div>

            <div class="space-4"></div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="notes"> 备注 </label>

                <div class="col-sm-9">
                    <input type="text" id="notes" name="notes" placeholder="备注" value="${resource.notes}"
                           class="col-xs-10 col-sm-5"/>
                </div>
            </div>

            <div class="space-4"></div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="deleteFlag"> 状态 </label>

                <div class="col-sm-9" id="deleteFlag">
                    <span>
                        <input name="deleteFlag" type="radio" value="0"
                               class="ace"  ${resource.deleteFlag == "0"?'checked':""}/>
                        <span class="lbl"> 有效 </span>
                    </span>
                    <span>
                        <input name="deleteFlag" type="radio" value="1"
                               class="ace"  ${resource.deleteFlag == "1"?'checked':""}/>
                        <span class="lbl"> 无效 </span>
                    </span>
                </div>
            </div>
            <div class="well">
                <div class="row ">
                    <div class="col-md-offset-3 col-md-8">
                        <button class="btn btn-info saveBtn" type="button">
                            <i class="ace-icon fa fa-check bigger-110"></i>
                            更新
                        </button>
                        &nbsp; &nbsp; &nbsp;
                        <button class="btn" type="reset">
                            <i class="ace-icon fa fa-undo bigger-110"></i>
                            重置
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
