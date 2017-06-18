$(function() {
    $('#loginForm').bootstrapValidator({
        framework: 'bootstrap',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        verbose: false,
        fields: {
            username: {
                message: '账户名校验失败',
                validators: {
                    notEmpty: {
                        message: '账户名不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 20,
                        message: '账户名的长度在6~20位之间'
                    },
                    regexp: {
                        regexp: /^[0-9a-zA-z_-]+$/,
                        message: '账户名只能输入数字、小写字母、大写字母、下划线和短横线',
                    }
                }
            },
            password: {
                message: '密码校验失败',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 20,
                        message: '密码的长度在6~20位之间'
                    },
                    regexp: {
                        regexp: /^[A-Za-z0-9]+$/,
                        message: '密码只能输入英文和数字'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {
        e.preventDefault();
        var $form = $(e.target);
        var origin = $form.serializeArray();
        var data = {};
        $.each(origin, function() {
            data[this.name] = this.value;
        });

        if (null === $global.timer) {

            $.ajax({
                url: $form.attr('action'),
                method: $form.attr('method'),
                data: data,
                error: function() {
                    $notify.danger('网络异常，请稍后重试或联系管理员');
                    $form.bootstrapValidator('disableSubmitButtons', false);
                },
                success: function(result) {
                    if (0 === result.code) {
                        $cookie.set($global.tokenKey, result.content);
                        window.setreq
                        window.location.href = '/index';
                    } else {
                        var fieldErroInfo = result.content;
                        $form.data('bootstrapValidator').updateStatus(fieldErroInfo.name, 'INVALID', 'customer');
                        $notify.warning(fieldErroInfo.message);
                    }
                }
            });
        }
    });
});