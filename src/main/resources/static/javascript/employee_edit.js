$(function() {
    if (!$global.validateMobile()) {
        $('.select').selectpicker();
    }

    $('.switch').bootstrapSwitch();

    $(function () {
        $('[data-toggle="tooltip"]').popover();
    });

    $('#employeeForm').bootstrapValidator({
        framework: 'bootstrap',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        verbose: false,
        excluded: null,
        fields: {
            username: {
                message: '员工账户名校验失败',
                validators: {
                    notEmpty: {
                        message: '员工账户名不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 20,
                        message: '员工账户名的长度必须在6~20位之间'
                    },
                    regexp: {
                        regexp: /^[A-Za-z0-9]+$/,
                        message: '员工账户名只能输入英文和数字'
                    },
                    different: {
                        field: 'password',
                        message: '账户名不能与密码相同'
                    },
                    threshold: 6,
                    remote: {
                        type: 'POST',
                        url: '/api/employees/validator/username',
                        message: '该账户名已经存在',
                        delay: 500,
                        data: function() {
                            return {
                                username: $('#username').val(),
                                id: $('#id').val()
                            }
                        }
                    }
                }
            },
            name: {
                message: '员工姓名校验失败',
                validators: {
                    notEmpty: {
                        message: '员工姓名不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 5,
                        message: '员工姓名的长度在2~5位之间'
                    },
                    regexp: {
                        regexp: /^[\u4e00-\u9fa5]+$/,
                        message: '员工姓名只能输入中文'
                    }
                }
            },
            mobile: {
                message: '员工电话校验失败',
                validators: {
                    regexp: {
                        regexp: /^(1[35784]\d{9})$/,
                        message: '请输入正确的联系电话'
                    },
                    threshold: 11,
                    remote: {
                        type: 'POST',
                        url: '/api/employees/validator/mobile',
                        message: '该电话号码已经存在',
                        delay: 500,
                        data: function() {
                            return {
                                mobile: $('#mobile').val(),
                                id: $('#id').val()
                            }
                        }
                    }
                }
            },
            email: {
                message: '员工邮箱地址校验失败',
                validators: {
                    emailAddress: {
                        message: '请输入正确的邮箱地址'
                    },
                    threshold: 6,
                    remote: {
                        type: 'POST',
                        url: '/api/employees/validator/email',
                        message: '该邮箱地址已经存在',
                        delay: 500,
                        data: function() {
                            return {
                                mobile: $('#mobile').val(),
                                id: $('#id').val()
                            }
                        }
                    }
                }
            },
            activeTime: {
                message: '入职时间校验失败',
                validators: {
                    notEmpty: {
                        message: '入职时间不能为空'
                    },
                    date: {
                        message: '请输入一个正确的日期'
                    }
                }
            },
            password: {
                message: '账户密码校验失败',
                validators: {
                    stringLength: {
                        min: 6,
                        max: 20,
                        message: '密码的长度必须在6~20位之间'
                    },
                    regexp: {
                        regexp: /^[A-Za-z0-9]+$/,
                        message: '密码只能输入英文和数字'
                    },
                    different: {
                        field: 'username',
                        message: '密码不能与账户名相同'
                    }
                }
            },
            rePassword: {
                message: '重复密码校验失败',
                validators: {
                    callback: {
                        message: '两次输入的密码不一致',
                        callback: function (value, validators) {
                            var password = $('#password').val();
                            if ("" !== password && password.length > 0) {
                                return value === password;
                            } else {
                                return true;
                            }
                        }
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
            var url = '/api/employees';

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
                        if (window.location.href === document.referrer) {
                            window.location.href = "/index";
                        } else {
                            window.location.href = document.referrer;
                        }
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