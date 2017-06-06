<head>
    <link href="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
</head>
<body>

<section class="content-header">
<h1>菜单名</h1>
<ol class="breadcrumb">
    <li><a href="/view"><i class="fa fa-home"></i> 首页</a></li>
    <li><a href="javascript:void(0);">父级菜单名</a></li>
    <li class="active">菜单名</li>
</ol>
</section>

<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box box-primary">
                <div id="toolbar" class="btn-group">
                    <button id="btn_add" type="button" class="btn btn-default">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 新增
                    </button>
                    <button id="btn_edit" type="button" class="btn btn-default">
                        <span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 修改
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
<div id="information" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="user-block">
                    <span class="username" style="margin-left: 0px;">
                        <a id="structureTitle" href="#"></a>
                        <a href="javascript:$page.information.close();" class="pull-right btn-box-tool">
                            <i class="fa fa-times"></i>
                        </a>

                    </span>
                    <ul id="structureAttributes" class="list-group list-group-unbordered" style="margin-top:10px;">
                        <li class="list-group-item">
                            <b>编码</b> <a class="pull-right" id="structureNumber"></a>
                        </li>
                        <li class="list-group-item">
                            <b>层级</b> <a class="pull-right" id="structureTier"></a>
                        </li>
                        <li class="list-group-item">
                            <b>类型</b> <a class="pull-right" id="structureType"></a>
                        </li>
                        <li class="list-group-item">
                            <b>标识</b> <a class="pull-right" id="structureSign"></a>
                        </li>
                        <li class="list-group-item">
                            <b>是否生效</b> <a class="pull-right" id="structureEnable"></a>
                        </li>
                        <li class="list-group-item">
                            <b>失效时间</b> <a class="pull-right" id="structureEnableFalseTime"></a>
                        </li>
                        <li class="list-group-item">
                            <b>末端节点</b> <a class="pull-right" id="structureLeaf"></a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="modal-footer">
                <a href="javascript:$page.information.close();" role="button" class="btn btn-primary">关闭</a>
            </div>
        </div>
    </div>
</div>
<script>
    $(function() {
        $grid.init($('#dataGrid'), $('#toolbar'), '/rest/structure/page/grid', 'get', true, function(params) {
            return {
                offset: params.offset ,
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
            field: 'number',
            title: '编码',
            events: {
                'click .scan': function(e, value, row) {
                    $page.information.show(row.id);
                }
            },
            formatter: function(value) {
                return '<a class="scan" href="#">' + value + '</a>';
            }
        }, {
            field: 'title',
            title: '组织架构名称'
        }, {
            field: 'tier',
            title: '层级',
            align: 'center'
        }, {
            field: 'type',
            title: '类型',
            align: 'center'
        }, {
            field: 'sign',
            title: '标识',
            align: 'center'
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
        }, {
            field: 'enableFalseTime',
            title: '失效时间',
            align: 'center'
        }, {
            field: 'leaf',
            title: '末端节点',
            formatter: function(value) {
                if (true === value) {
                    return '<span class="label label-danger">是</span>';
                } else {
                    return '<span class="label label-success">否</span>';
                }
            },
            align: 'center'
        }]);

        $('#btn_add').on('click', function () {
            $grid.add('/view/structure/edit/0?parentMenuId=${parentMenuId!'0'}');
        });

        $('#btn_edit').on('click', function() {
            $grid.modify($('#dataGrid'), '/view/structure/edit/{id}?parentMenuId=${parentMenuId!'0'}')
        });
    });

    var $page = {
        information: {
            show: function(id) {
                if (null === $global.timer) {
                    $global.timer = setTimeout($loading.show, 2000);
                    $.ajax({
                        url: '/rest/structure/' + id,
                        method: 'GET',
                        error: function () {
                            clearTimeout($global.timer);
                            $loading.close();
                            $global.timer = null;
                            $notify.danger('网络异常，请稍后重试或联系管理员');
                        },
                        success: function (result) {
                            clearTimeout($global.timer);
                            $loading.close();
                            $global.timer = null;
                            if (0 === result.code) {
                                var data = result.content;
                                $('#structureTitle').html(data.title);

                                if (null === data.number) {
                                    data.number = '-';
                                }
                                $('#structureNumber').html(data.number);

                                if (null === data.tier) {
                                    data.tier = '-';
                                }
                                $('#structureTier').html(data.tier);

                                if (null === data.type) {
                                    data.type = '-';
                                }
                                $('#structureType').html(data.type);

                                if (null === data.sign) {
                                    data.sign = '-';
                                }
                                $('#structureSign').html(data.sign);

                                if (true === data.enable) {
                                    data.enable = '生效';
                                } else if (false === data.enable) {
                                    data.enable = '失效';
                                } else {
                                    data.enable = '-';
                                }
                                $('#structureEnable').html(data.enable);

                                if (null === data.enableFalseTime) {
                                    data.enableFalseTime = '-';
                                }
                                $('#structureEnableFalseTime').html(data.enableFalseTime);

                                if (true === data.leaf) {
                                    data.leaf = '是';
                                } else if (false === data.leaf) {
                                    data.leaf = '否';
                                } else {
                                    data.leaf = '-';
                                }
                                $('#structureLeaf').html(data.leaf);

                                $('#information').modal();
                            } else {
                                $notify.danger(result.message);
                            }
                        }
                    })
                }
            },
            close: function() {
                $('#information').modal('hide');
            }
        }
    }
</script>
</body>