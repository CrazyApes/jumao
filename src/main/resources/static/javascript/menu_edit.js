$(function() {
    if (!$global.validateMobile()) {
        $('.select').selectpicker();
    }

    $(function () {
        $('[data-toggle="tooltip"]').popover();
    });

    $('#menuForm').bootstrapValidator({
        framework: 'bootstrap',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        verbose: false,
        fields: {
            title: {
                message: '菜单标题校验失败',
                validators: {
                    notEmpty: {
                        message: '菜单标题不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 10,
                        message: '菜单标题的长度必须在2~10位之间'
                    },
                    regexp: {
                        regexp: /^[\u4e00-\u9fa5]+$/,
                        message: '菜单标题只能输入中文'
                    },
                    threshold: 2,
                    remote: {
                        type: 'POST',
                        url: '/api/menus/validator/title',
                        message: '该名称已经存在',
                        delay: 500,
                        data: function() {
                            return {
                                title: $('#title').val(),
                                id: $('#id').val()
                            }
                        }
                    }
                }
            },
            linkUri: {
                message: '链接地址校验失败',
                validators: {
                    notEmpty: {
                        message: '链接地址不能为空'
                    },
                    regexp: {
                        regexp: /^(\/?[a-zA-Z0-9]\/?)+$|^#$/,
                        message: '请输入一个正确的不带参数的站内链接地址或者"#"'
                    },
                    stringLength: {
                        min: 1,
                        max: 50,
                        message: '链接地址的长度在1~50之间'
                    }
                }
            },
            iconStyle: {
                message: '图标样式校验失败',
                validators: {
                    stringLength: {
                        max: 20,
                        message: '图标样式的长度在0~20之间'
                    }
                }
            },
            sortId: {
                message: '排序号校验失败',
                validators: {
                    notEmpty: {
                        message: '排序号不能为空'
                    },
                    regexp: {
                        regexp: /^[1-9]([0-9]?)$/,
                        message: '排序号的为1~99之间的整数'
                    }
                }
            }
        }
    }).on('success.form.bv', function(e) {
        e.preventDefault();
        var $form = $(e.target);
        var origin = $form.serializeArray();
        var data = {};

        console.log(origin);
        console.log(data);

        $.each(origin, function() {
            data[this.name] = this.value;
        });

        if (null === $global.timer) {
            var url = '/api/menus';

            if (null !== data.id && 0 !== Number(data.id)) {
                data._method = 'PUT';
                url += ('/' + data.id);
            }


            $global.timer = $loading.show(1500);

            $.ajax({
                url: url,
                method: 'POST',
                data: data,
                error: function() {
                    clearTimeout($global.timer);
                    $loading.close();
                    $global.timer = null;
                    $notify.danger('网络异常，请稍后重试或联系管理员');
                    $form.bootstrapValidator('disableSubmitButtons', false);
                },
                success: function(result) {
                    clearTimeout($global.timer);
                    $loading.close();
                    $global.timer = null;
                    if (0 === result.code) {
                        window.location.href = document.referrer;
                    } else {
                        $notify.danger(result.message);
                        $form.bootstrapValidator('disableSubmitButtons', false);
                    }
                }
            })

        }
    });

    $('.btn-cancel').on('click', function() {
        window.location.href = document.referrer;
    });
});