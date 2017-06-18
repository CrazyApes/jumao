<head>
    <link href="https://cdn.bootcss.com/bootstrap-select/1.12.2/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-switch/3.3.4/css/bootstrap3/bootstrap-switch.min.css" rel="stylesheet">

    <link href="/stylesheet/app.css" rel="stylesheet">

    <script src="https://cdn.bootcss.com/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-switch/3.3.4/js/bootstrap-switch.min.js"></script>

    <script src="/javascript/customer_edit.js"></script>
</head>
<body>
<section class="content-header"></section>
<section class="content">
    <div class="nav-tabs-custom">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#tab_1-1" data-toggle="tab">基本信息</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="tab_1-1">
                <form id="customerForm">

                    <input type="hidden" id="id" name="id"
                    <#if customer?? && customer.id??>
                           value="${customer.id?c}"
                    <#else>
                           value="0"
                    </#if>
                    >
                    <div class="row">
                        <div class="col-md-6 col-xs-12">
                            <div class="form-group">
                                <label for="title">
                                    客户识别名
                                    <i class="fa fa-question-circle i-tooltip hidden-xs" data-toggle="tooltip"
                                       data-content="只能输入中文、英文、数字，长度在2~40之间，不可重复，是识别客户的唯一标识"></i>
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <input name="title" type="text" class="form-control" id="title" placeholder="客户识别名"
                                           value="<#if customer??>${customer.title!'加载失败...'}</#if>">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-xs-12">
                            <div class="form-group">
                                <label for="region">
                                    所属地区
                                    <i class="fa fa-question-circle i-tooltip hidden-xs" data-toggle="tooltip"
                                       data-content="只能输入中文，长度在2~20之间"></i>
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <input name="region" type="text" class="form-control" id="region" placeholder="所属地区"
                                           value="<#if customer??>${customer.region!'加载失败...'}</#if>">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 col-xs-12">
                            <div class="form-group">
                                <label for="address">
                                    客户地址
                                    <i class="fa fa-question-circle i-tooltip hidden-xs" data-toggle="tooltip"
                                       data-content="只能输入中文、英文、数字，长度在4~100之间"></i>
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <input name="address" type="text" class="form-control" id="address" placeholder="客户地址"
                                           value="<#if customer??>${customer.address!'加载失败...'}</#if>">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-xs-12">
                            <div class="form-group">
                                <label for="deliveryType">
                                    常用物流
                                    <i class="fa fa-question-circle i-tooltip hidden-xs" data-toggle="tooltip"
                                       data-content="只能输入中文和英文，长度在0~20之间"></i>
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <input name="deliveryType" type="text" class="form-control" id="deliveryType" placeholder="常用物流"
                                           value="<#if customer??>${customer.deliveryType!''}</#if>">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 col-xs-12">
                            <div class="form-group">
                                <label for="phone">
                                    联系电话
                                    <i class="fa fa-question-circle i-tooltip hidden-xs" data-toggle="tooltip"
                                       data-content="可以是座机号码也可以是手机号码，只能输入数字或横杠"></i>
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <input name="phone" type="text" class="form-control" id="phone" placeholder="联系电话"
                                           value="<#if customer??>${customer.phone!'加载失败...'}</#if>">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-xs-12">
                            <div class="form-group">
                                <label for="address">
                                    客户传真
                                    <i class="fa fa-question-circle i-tooltip hidden-xs" data-toggle="tooltip"
                                       data-content="只能输入数字和横杠"></i>
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <input name="fax" type="text" class="form-control" id="fax" placeholder="客户传真"
                                           value="<#if customer??>${customer.fax!''}</#if>">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 col-xs-12">
                            <div class="form-group">
                                <label for="address">
                                    备注信息
                                    <i class="fa fa-question-circle i-tooltip hidden-xs" data-toggle="tooltip"
                                       data-content="可以输入任意信息，长度在0~150之间"></i>
                                </label>
                                <div class="input-group col-xs-12 col-md-12">
                                    <textarea name="remark" id="remark" class="form-control no-resize" rows="3"
                                            placeholder="请在此输入备注信息"><#if customer??>${customer.remark!''}</#if></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 col-xs-12">
                            <div class="form-group">
                                <label for="enable">
                                    是否生效
                                    <i class="fa fa-question-circle i-tooltip" data-toggle="tooltip"
                                       data-content="客户是否生效"></i>
                                </label>
                                <br>
                                <input name="enable" id="enable" class="switch" type="checkbox"
                                       <#if customer?? && customer.enable?? && customer.enable>checked</#if>>
                            </div>
                        </div>
                    </div>
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
                </form>
            </div>
        </div>
    </div>
</section>
</body>