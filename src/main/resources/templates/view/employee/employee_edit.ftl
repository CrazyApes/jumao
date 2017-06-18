<head>
    <link href="https://cdn.bootcss.com/bootstrap-select/1.12.2/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-switch/3.3.4/css/bootstrap3/bootstrap-switch.min.css" rel="stylesheet">

    <link href="/stylesheet/app.css" rel="stylesheet">

    <script src="https://cdn.bootcss.com/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-switch/3.3.4/js/bootstrap-switch.min.js"></script>

    <script src="/javascript/employee_edit.js"></script>
</head>
<body>
<section class="content-header"></section>
<section class="content">
    <form id="employeeForm">

    <!-- 员工id信息 -->
    <input type="hidden" id="id" name="id"
        <#if employee?? && employee.id??>
        value="${employee.id?c}"
        <#else>
        value="0"
        </#if>
    >
        <div class="nav-tabs-custom">

            <ul class="nav nav-tabs">
                <li class="active"><a href="#tab_1-1" data-toggle="tab">基本信息</a></li>
                <li><a href="#tab_1-2" data-toggle="tab">安全信息</a></li>
            </ul>
            <div class="tab-content">
                <!-- 配置员工基本信息的面板 -->
                <div role="tabpanel" class="tab-pane active" id="tab_1-1">
                    <#include "base_panel.ftl">
                </div>

                <!-- 配置员工安全信息的面板 -->
                <div role="tabpanel" class="tab-pane" id="tab_1-2">
                    <#include "security_panel.ftl">
                </div>
            </div>

        </div>
        <div class="content-footer">
            <div class="row">
                <div class="col-xs-12 col-md-8"></div>
                <div class="col-xs-12 col-md-2">
                    <button type="submit" class="btn btn-primary footer-btn">
                        <i class="fa fa-check"></i> 保存
                    </button>
                </div>
                <div class="col-xs-12 col-md-2">
                    <button type="button" class="btn btn-danger footer-btn btn-cancel">
                        <i class="fa fa-close"></i> 取消
                    </button>
                </div>
            </div>
        </div>
    </form>
</section>
</body>