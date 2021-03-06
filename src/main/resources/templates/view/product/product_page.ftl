<head>
    <link href="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
</head>
<body>

<section class="content-header">
    <h1><#if currentMenu??>${currentMenu.title!'加载中...'}<#else>加载中...</#if></h1>
    <ol class="breadcrumb">
        <li><a href="/view"><i class="fa fa-home"></i>&nbsp;首页</a></li>
    <#if parentMenu??>
        <li><a href="javascript:void(0);">&nbsp;${parentMenu.title!'加载中...'}</a></li>
    </#if>
    <#if currentMenu??>
        <li class="active">&nbsp;${currentMenu.title!'加载中...'}</li>
    </#if>
    </ol>
</section>

<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box box-primary">
                <div id="toolbar" class="btn-group">
                    <button id="btn_add" type="button" class="btn btn-default">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;新增
                    </button>
                    <button id="btn_edit" type="button" class="btn btn-default">
                        <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>&nbsp;修改
                    </button>
                    <button id="btn_delete" type="button" class="btn btn-default">
                        <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>&nbsp;失效
                    </button>
                </div>
                <div class="box-body table-reponsive">
                    <table id="dataGrid" class="table table-bordered table-hover">

                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    $(function() {
        $grid.init($('#dataGrid'), $('#toolbar'), '/api/customers', 'get', true, function(params) {
            return {
                offset: params.offset,
                size: params.limit,
                keywords: params.search
            }
        }, [{
            checkbox: true,
            title: '选择'
        }, {
            field: 'id',
            title: 'ID',
            align: 'center'
        }, {
            field: 'title',
            title: '客户标识名'
        }, {
            field: 'region',
            title: '所属地区',
            width: '100px'
        }, {
            field: 'address',
            title: '客户地址',
            width: '350px'
        }, {
            field: 'phone',
            title: '联系方式'
        }, {
            field: 'deliveryType',
            title: '常用物流'
        }, {
            field: 'fax',
            title: '传真号码'
        }, {
            field: 'remark',
            title: '备注',
            width: '400px'
        }, {
            field: 'enable',
            title: '是否生效',
            formatter: function(value) {
                if (true === value) {
                    return '<span class="label label-success">生效</span>';
                } else {
                    return '<span class="label label-danger">失效</span>';
                }
            },
            align: 'center'
        }]);

        $('#btn_add').on('click', function () {
            $grid.add('/customers/edit/0?parentMenuId=${parentMenuId!'0'}');
        });

        $('#btn_edit').on('click', function() {
            var container = $('#dataGrid');
            var selected = $grid.getSelectedIds(container);
            var length = selected.length;
            if (length === 0) {
                $notify.warning('请在点击按钮前选中一条数据');
            } else if (length > 1) {
                $notify.warning('您每次只能选择一条数据进行修改');
            } else {
                var id = selected[0];
                var row = $grid.getRowByUniqueId(container, id);
                if (true === row.readOnly) {
                    $notify.warning('只读数据不允许修改');
                } else {
                    window.location.href = '/customers/edit/' + id + '?parentMenuId=${parentMenuId!'0'}';
                }
            }
        });

        $('#btn_delete').on('click', function() {
            if (null === $global.timer) {
                var container = $('#dataGrid');
                var selected = $grid.getSelectedIds(container);
                var length = selected.length;
                if (length === 0) {
                    $notify.warning('请在点击按钮前选中一条数据');
                } else {
                    $global.timer = $loading.show(1500);
                    $.ajax({
                        url: '/api/customers/enable/false',
                        method: 'POST',
                        traditional: true,
                        data: {
                            ids: selected
                        },
                        error: function() {
                            clearTimeout($global.timer);
                            $loading.close();
                            $global.timer = null;
                            $notify.danger('网络异常，请稍后重试或联系管理员');
                        },
                        success: function(result) {
                            clearTimeout($global.timer);
                            $loading.close();
                            $global.timer = null;
                            if (0 === result.code) {
                                $grid.refresh($('#dataGrid'));
                                $notify.info("共计" + result.content + "条数据被失效");
                            } else {
                                $notify.danger(result.message);
                            }
                        }
                    })
                }
            }
        });
    });
</script>
</body>