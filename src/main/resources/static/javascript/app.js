var $global = {
    timer: null,
    tokenKey: 'jumao_token_key',
    validateMobile: function() {
        var userAgentInfo = navigator.userAgent;
        var Agents = ["Android", "iPhone",
            "SymbianOS", "Windows Phone"];
        var flag = false;
        for (var v = 0; v < Agents.length; v++) {
            if (userAgentInfo.indexOf(Agents[v]) > 0) {
                flag = true;
                break;
            }
        }
        return flag;
    }
};

var $grid = {
    refresh: function(container) {
        container.bootstrapTable('refresh');
    },
    add: function (url) {
        window.location.href = url;
    },
    modify: function (container, url) {
        var selected = this.getSelectedIds(container);
        var length = selected.length;
        if (length === 0) {
            $notify.warning('请在点击按钮前选中一条数据');
        } else if (length > 1) {
            $notify.warning('您每次只能选择一条数据进行修改');
        } else {
            var id = selected[0];
            url = url.replace('{id}', id);
            window.location.href = url;
        }
    },
    remove: function (container, url, method) {
        if (null === $global.timer) {
            var selected = this.getSelectedIds(container);
            if (null === selected || 0 === selected.length) {
                $notify.warning('请在点击按钮前选中至少一条数据');
                return;
            }
            $modal.danger('危险操作', '数据删除后无法恢复，是否确认？', function () {

                $global.timer = setTimeout($loading.show, 2000);

                var data = {
                    ids: selected
                };
                var type = null;
                if ('delete' === method || 'DELETE' === method || 'put' === method || 'PUT' === method) {
                    data._method = method;
                    type = 'POST';
                } else {
                    type = method;
                }
                $.ajax({
                    url: url,
                    type: type,
                    traditional: true,
                    data: data,
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
                            container.bootstrapTable('refresh');
                        } else {
                            $notify.danger(result.message);
                        }
                    }
                });
            });
        }
    },
    init: function (container, toolbar, url, method, search, queryParams, columns) {
        container.bootstrapTable({
            // 请求地址
            url: url,
            // 请求方式
            method: method,
            // 是否隔行变色
            striped: true,
            // 服务器返回数据类型
            dataType: 'json',
            // 是否缓存
            cache: true,
            // 工具栏
            toolbar: toolbar,
            // 是否显示分页按钮
            pagination: true,
            // 设置在服务端分页还是客户端分页
            sidePagination: 'server',
            // 是否开启查找按钮
            search: search,
            // 查找按钮位置
            searchAlign: 'right',
            // 设置初始化加载页数
            pageNumber: 1,
            // 设置每页数据量
            pageSize: 10,
            // 设置可选每页数据量
            pageList: [10, 15, 20],
            // 是否显示内容下拉框
            showColumns: true,
            // 是否显示刷新按钮
            showRefresh: true,
            // 主键字段
            idField: 'id',
            // 设置唯一标识
            uniqueId: 'id',
            // 上一页按钮显示值
            paginationPreText: '上一页',
            // 下一页按钮显示值
            paginationNextText: '下一页',
            // 是否开启点击选中
            clickToSelect: true,
            // 是否使用按下Enter键之后执行查询
            searchOnEnterKey: true,
            // 是否允许空值查询
            trimOnSearch: false,
            // 查询参数
            queryParams: queryParams,
            columns: columns
        });
    },
    getSelectedIds: function(container) {
        var ids = [];
        var selected = container.bootstrapTable('getSelections');
        for (var i = 0; i < selected.length; i++ ) {
            var data = selected[i];
            ids.push(data.id);
        }
        return ids;
    },
    getRowByUniqueId: function(container, uniqueId) {
        return container.bootstrapTable('getRowByUniqueId', uniqueId);
    }
};

var $loading = {
    show: function(time) {
        return setTimeout(function() {
            $('#loading').on('show.bs.modal', function(){
                var $this = $('#loading');
                var $modal_dialog = $this.find('.modal-dialog');
                var m_top = ( $(window).height() - $modal_dialog.height() )/2;
                $modal_dialog.css({'margin': m_top + 'px auto'});
            });
            $('#loading').modal({backdrop: 'static', keyboard: false});
            $('#loading').modal('show');
        }, time);
    },
    close: function(time) {
        setTimeout(function() {
            $('#loading').modal('hide');
        }, time);
    }
};

var $notify = {
    notify: function (options, type) {
        $.notify(options, {
            element: 'body',
            newest_on_top: true,
            type: type,
            placement: {
                from: "top",
                align: "right"
            },
            delay: 3000,
            timer: 1000,
            animate: {
                enter: 'animated fadeInDown',
                exit: 'animated fadeOutUp'
            },
            template: '<div class="alert bg-{0} alert-dismissible" style="width: 300px">' +
            '<h4><i data-notify="icon"></i>{1}</h4>' +
            '<span data-notify="message">{2}</span>' +
            '<a href="{3}" target="{4}" data-notify="url"></a>' +
            '</div>'
        });
    },
    success: function(message, url, target) {
        this.notify({
            title: '成功',
            icon: 'icon fa fa-check',
            message: message,
            url: url,
            target: target
        }, 'success');
    },
    info: function(message, url, target) {
        this.notify({
            title: '提示',
            icon: 'icon fa fa-info',
            message: message,
            url: url,
            target: target
        }, 'info');
    },
    warning: function(message, url, target) {
        this.notify({
            title: '警告',
            icon: 'icon fa fa-warning',
            message: message,
            url: url,
            target: target
        }, 'warning');
    },
    danger: function(message, url, target) {
        this.notify({
            title: '错误',
            icon: 'icon fa fa-ban',
            message: message,
            url: url,
            target: target
        }, 'danger');
    }
};

var $modal = {
    danger: function (title, content, method) {
        $('#dangerModalTitle').html(' ' + title);
        $('#dangerModalContent').html(' ' + content);
        $('#dangerModalCheckBtn').one('click', function() {
            $('#dangerModal').modal('hide');
            method();
        });
        $('#dangerModal').modal('show', {backdrop: 'static', keyboard: false});
    }
};

var $cookie = {
    set: function(key, value, days) {
        if (null === days || undefined === days) {
            days = 1
        }
        var date = new Date();
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        document.cookie = key + '='+ value + ';expires=' + date.toString();
    }
};

Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "H+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
