<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" role="form">
            <input type="hidden" name="${resource.resourceId}"/>

            <div class="space-4"></div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="parentId"> 父资源 </label>

                <div class="col-sm-9">
                    <input type="text" id="parentId" name="parentId" value="${resource.parentName}"
                           class="col-xs-10 col-sm-5 " disabled="disabled"/>
                </div>
            </div>
            <!-- #section:elements.form -->
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="resourceName"> 资源名称 </label>

                <div class="col-sm-9">
                    <input type="text" id="resourceName" name="resourceName"
                           value="${resource.resourceName}" disabled="disabled"
                           class="col-xs-10 col-sm-5"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="identify"> 资源标识符 </label>

                <div class="col-sm-9">
                    <input type="text" id="identify" name="identify" value="${resource.identify}"
                           class="col-xs-10 col-sm-5" disabled="disabled"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="url"> 资源URL </label>

                <div class="col-sm-9">
                    <input type="text" id="url" name="url" value="${resource.url}"
                           class="col-xs-10 col-sm-5" disabled="disabled"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="url"> 资源图标 </label>

                <div class="col-sm-9">
                    <input type="text" id="icon" name="icon" value="${resource.icon}"
                           class="col-xs-10 col-sm-5" disabled="disabled"/>
                </div>
            </div>


            <!-- /section:elements.form -->
            <div class="space-4"></div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="sortKey"> 资源排序 </label>

                <div class="col-sm-9">
                    <input type="text" id="sortKey" name="sortKey" value="${resource.sortKey}"
                           class="col-xs-10 col-sm-5" disabled="disabled"/>
                </div>
            </div>

            <div class="space-4"></div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="notes"> 备注 </label>

                <div class="col-sm-9">
                    <input type="text" id="notes" name="notes" value="${resource.notes}"
                           class="col-xs-10 col-sm-5" disabled="disabled"/>
                </div>
            </div>

            <div class="space-4"></div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="deleteFlag"> 状态 </label>

                <div class="col-sm-9" id="deleteFlag">
                    <span>
                        <input name="deleteFlag" type="radio" class="ace" ${resource.deleteFlag eq 0 ? "checked":"" }
                               disabled="disabled"/>
                        <span class="lbl"> 有效 </span>
                    </span>
                    <span>
                        <input name="deleteFlag" type="radio" class="ace" ${resource.deleteFlag eq 1 ? "checked":"" }
                               disabled="disabled"/>
                        <span class="lbl"> 无效 </span>
                    </span>
                </div>
            </div>
        </form>
    </div>
</div>
<br>
