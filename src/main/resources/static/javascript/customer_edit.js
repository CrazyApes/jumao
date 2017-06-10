$(function() {
    if (!$global.validateMobile()) {
        $('.select').selectpicker();
    }

    $('.switch').bootstrapSwitch();

    $(function () {
        $('[data-toggle="tooltip"]').popover();
    });

    $('#customerForm').bootstrapValidator({
        framework: 'bootstrap',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        verbose: false,
        fields: {
            title: {
                message: '客户标识名校验失败',
                validators: {
                    notEmpty: {
                        message: '客户标识名不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 40,
                        message: '客户标识名的长度必须在2~40位之间'
                    },
                    regexp: {
                        regexp: /^[\u4e00-\u9fa5A-Za-z0-9]+$/,
                        message: '客户标识名只能输入中文、英文和数字'
                    },
                    threshold: 2,
                    remote: {
                        type: 'POST',
                        url: '/api/customers/validator/title',
                        message: '该标识名已经存在',
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
            address: {
                message: '客户地址校验失败',
                validators: {
                    notEmpty: {
                        message: '客户地址不能为空'
                    },
                    regexp: {
                        regexp: /^[\u4e00-\u9fa5A-Za-z0-9]+$/,
                        message: '客户地址只能输入中文、英文和数字'
                    },
                    stringLength: {
                        min: 4,
                        max: 100,
                        message: '客户地址的长度在4~100位之间'
                    }
                }
            },
            phone: {
                message: '联系电话校验失败',
                validators: {
                    stringLength: {
                        min: 8,
                        max: 15,
                        message: '联系电话的长度在8~15位之间'
                    },
                    notEmpty: {
                        message: '联系电话不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9]+(-[0-9]+)?$/,
                        message: '联系电话只能输入数字或横杠，且属于正确的电话号码格式'
                    },
                    threshold: 8,
                    remote: {
                        type: 'POST',
                        url: '/api/customers/validator/phone',
                        message: '该联系电话已经存在',
                        delay: 500,
                        data: function() {
                            return {
                                phone: $('#phone').val(),
                                id: $('#id').val()
                            }
                        }
                    }
                }
            },
            fax: {
                message: '客户传真校验失败',
                stringLength: {
                    max: 20,
                    message: '客户传真最大不能超过20位'
                },
                threshold: 1,
                remote: {
                    type: 'POST',
                    url: '/api/customers/validator/fax',
                    message: '该传真已经存在',
                    delay: 500,
                    data: function() {
                        return {
                            fax: $('#fax').val(),
                            id: $('#id').val()
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
            var url = '/api/customers';

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