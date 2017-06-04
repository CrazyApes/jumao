var $global = {
    timer: null,
    tokenKey: 'jumao_token_key'
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

$(function() {
    $(document).ajaxStart(function() {
        $global.timer = $loading.show(1500);
    });

    $(document).ajaxComplete(function() {
        clearTimeout($global.timer);
        $loading.close();
        $global.timer = null;
    });
});
