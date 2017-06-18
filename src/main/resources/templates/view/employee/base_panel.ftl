<input type="hidden" name="headerImageUri" id="headerImageUri"
       value="<#if employee??>${employee.headerImageUri!'/images/header.png'}<#else>/images/header.png</#if>">
<div class="row">
    <div class="col-md-6 col-xs-12">
        <div class="user-panel">
            <div class="pull-left image">
                <img name="headImageUri" id="headImageUri"
                     src="<#if employee??>${employee.headerImageUri!'/images/header.png'}<#else>/images/header.png</#if>"
                     class="img-circle header_edit"
                     style="max-width: 70px;" alt="User Image">
            </div>
            <div class="pull-left info"
                 style="padding: 10px 5px 5px 15px; left: 90px;">
                <p style="color:black;" id="header_username"><#if employee??>${employee.username!'账户名...'}<#else>账户名...</#if></p>
                <a class="btn btn-primary btn-xs" href="#">
                    <i class="fa fa-cloud-upload"></i> 上传头像
                </a>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-6 col-xs-12">
        <div class="form-group">
            <label for="username">
                账户名
                <i class="fa fa-question-circle i-tooltip hidden-xs" data-toggle="tooltip"
                   data-content="只能输入英文、数字，长度在6~20之间，不可重复，是识别员工的唯一标识"></i>
            </label>
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                <input name="username" type="text" class="form-control" id="username" placeholder="账户名"
                       <#if employee??>value="${employee.username!'加载失败...'}" readonly="readonly"</#if>>
            </div>
        </div>
    </div>
    <div class="col-md-6 col-xs-12">
        <div class="form-group">
            <label for="roleId">
                员工角色
                <i class="fa fa-question-circle i-tooltip hidden-xs" data-toggle="tooltip"
                   data-content="角色与权限相关，请根据实际情况选择"></i>
            </label>
            <select id="roleId" name="roleId" class="form-control select" data-live-search="true">
                <#if roleList?? && roleList?size gt 0>
                    <#list roleList as item>
                        <option value="${(item.id!'0')?c}"
                                <#if employee?? && employee.role?? && employee.role.id?? && employee.role.id?c == (item.id!'0')?c>selected</#if>>
                            ${item.title!'加载失败...'}
                        </option>
                    </#list>
                </#if>
            </select>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-6 col-xs-12">
        <div class="form-group">
            <label for="name">
                员工姓名
                <i class="fa fa-question-circle i-tooltip hidden-xs" data-toggle="tooltip"
                   data-content="只能输入中文，长度在2~5之间"></i>
            </label>
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                <input name="name" type="text" class="form-control" id="name" placeholder="员工姓名"
                       value="<#if employee??>${employee.name!'加载失败...'}</#if>">
            </div>
        </div>
    </div>
    <div class="col-md-6 col-xs-12">
        <div class="form-group">
            <label for="sex">
                员工性别
                <i class="fa fa-question-circle i-tooltip hidden-xs" data-toggle="tooltip"
                   data-content="员工的性别"></i>
            </label>
            <select id="sex" name="sex" class="form-control select" data-live-search="true">
                <#assign sexs = [['SECRET', '保密'], ['MALE', '男'], ['FEMALE', '女']]>
                <#list sexs as item>
                    <option value="${item[0]}"
                            <#if employee?? && employee.sex?? && employee.sex == item[0]>selected</#if>>
                        ${item[1]}
                    </option>
                </#list>
            </select>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-6 col-xs-12">
        <div class="form-group">
            <label for="mobile">
                联系电话
                <i class="fa fa-question-circle i-tooltip hidden-xs" data-toggle="tooltip"
                   data-content="员工的手机号码，可以不填写"></i>
            </label>
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                <input name="mobile" type="text" class="form-control" id="mobile" placeholder="联系电话"
                       value="<#if employee??>${employee.mobile!'加载失败...'}</#if>">
            </div>
        </div>
    </div>
    <div class="col-md-6 col-xs-12">
        <div class="form-group">
            <label for="email">
                员工邮箱
                <i class="fa fa-question-circle i-tooltip hidden-xs" data-toggle="tooltip"
                   data-content="员工的邮箱地址，可以不填写"></i>
            </label>
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                <input name="email" type="text" class="form-control" id="email" placeholder="员工邮箱"
                       value="<#if employee??>${employee.email!''}</#if>">
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-6 col-xs-12">
        <div class="form-group">
            <label for="activeTime">
                入职时间
                <i class="fa fa-question-circle i-tooltip hidden-xs" data-toggle="tooltip"
                   data-content="员工的入职时间，必须填写"></i>
            </label>
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                <input name="activeTime" type="date" class="form-control" id="activeTime" placeholder="入职时间"
                       value="<#if employee??>${employee.activeTime?string('yyyy-MM-dd')}</#if>">
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-6 col-xs-12">
        <div class="form-group">
            <label for="status">
                是否生效
                <i class="fa fa-question-circle i-tooltip" data-toggle="tooltip"
                   data-content="员工账户是否生效"></i>
            </label>
            <br>
            <input name="status" id="status" class="switch" type="checkbox"
                   <#if employee?? && employee.status?? && employee.status == 'ACTIVE'>checked</#if>>
        </div>
    </div>
</div>