<head>
    <link href="https://cdn.bootcss.com/bootstrap-select/1.12.2/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-switch/3.3.4/css/bootstrap3/bootstrap-switch.min.css" rel="stylesheet">

    <link href="/stylesheet/app.css" rel="stylesheet">

    <script src="https://cdn.bootcss.com/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>

    <script src="/javascript/menu_edit.js"></script>
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
                <form id="menuForm">

                    <input type="hidden" id="id" name="id"
                    <#if menu?? && menu.id??>
                           value="${menu.id?c}"
                    <#else>
                           value="0"
                    </#if>
                    >
                    <div class="row">
                        <div class="col-md-6 col-xs-12">
                            <div class="form-group">
                                <label for="type">
                                    父级菜单
                                    <i class="fa fa-question-circle i-tooltip hidden-xs" data-toggle="tooltip"
                                       data-content="二级菜单选择指定父级菜单；一级菜单，请选择'无父级菜单'"></i>
                                </label>
                                <select id="parentId" name="parentId" class="form-control select" data-live-search="true">
                                    <option value="0">无父级菜单</option>
                                <#if topMenuList?? && topMenuList?size gt 0>
                                    <#list topMenuList as item>
                                        <#if !(menu?? && menu.id?? && menu.id?c == (item.id!'0')?c)>
                                            <option value="${(item.id!'0')?c}" data-icon="${item.iconStyle!'fa fa-circle-o'}"
                                                    <#if menu?? && menu.parentId?? && menu.parentId?c == (item.id!'0')?c>selected</#if>>
                                            ${item.title!'加载失败...'}
                                            </option>
                                        </#if>
                                    </#list>
                                </#if>
                                </select>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <div class="form-group">
                                <label for="title">
                                    菜单标题
                                    <i class="fa fa-question-circle i-tooltip hidden-xs" data-toggle="tooltip"
                                       data-content="只能输入中文，长度在2~10之间"></i>
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <input name="title" type="text" class="form-control" id="title" placeholder="菜单标题"
                                           value="<#if menu??>${menu.title!'加载失败...'}</#if>">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-6">
                            <div class="form-group">
                                <label for="linkUri">
                                    链接地址
                                    <i class="fa fa-question-circle i-tooltip hidden-xs" data-toggle="tooltip"
                                       data-content="输入菜单跳转的链接，如果没有请输入'#'；
                                       长度在1~50之间，只能输入站内链接资源，
                                       且不允许带参数，例如：/example"></i>
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <input name="linkUri" type="text" class="form-control" id="linkUri" placeholder="链接地址"
                                           value="<#if menu??>${menu.linkUri!'加载失败...'}</#if>">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <div class="form-group">
                                <label for="iconStyle">
                                    图标样式
                                    <i class="fa fa-question-circle i-tooltip hidden-xs" data-toggle="tooltip"
                                       data-content="图标样式可参考:http://fontawesome.io/icons/；长度在0~20个之间"></i>
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <input name="iconStyle" type="text" class="form-control" id="iconStyle" placeholder="图标样式"
                                           value="<#if menu??>${menu.iconStyle!''}</#if>">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-6">
                            <div class="form-group">
                                <label for="sortId">
                                    排序号
                                    <i class="fa fa-question-circle i-tooltip hidden-xs" data-toggle="tooltip"
                                       data-content="菜单的排序标识，数字越小，排序越靠前，范围在1~99之间"></i>
                                </label>
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-pencil"></i></span>
                                    <input name="sortId" type="number" class="form-control" id="sortId" placeholder="排序号"
                                           value="<#if menu??>${(menu.sortId!'99')?c}<#else>99</#if>">
                                </div>
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