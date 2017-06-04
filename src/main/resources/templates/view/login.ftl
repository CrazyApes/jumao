<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>巨茂木业</title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="https://cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet">
<link href="https://cdn.bootcss.com/admin-lte/2.3.11/css/AdminLTE.min.css" rel="stylesheet">

<!--[if lt IE 9]>
<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

</head>
<body class="hold-transition login-page">
<#include "../common/loading.ftl">
<div class="login-box">
    <div class="login-logo">
        <a href="../../index2.html"><b>巨茂木业</b>OMS</a>
    </div>

    <div class="login-box-body">
        <p class="login-box-msg"></p>

        <form action="${ctx!''}/api/sessions" method="post" id="loginForm">
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input type="text" name="username" id="username" class="form-control" placeholder="账户名">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                    <input type="password" name="password" id="password" class="form-control" placeholder="密码">
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
                </div>
                <div class="col-xs-12" style="margin-top: 10px;">
                    <button type="reset" class="btn btn-danger btn-block btn-flat">重置</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
<script src="https://cdn.bootcss.com/mouse0270-bootstrap-notify/3.1.7/bootstrap-notify.min.js"></script>
<script src="${ctx!''}/javascript/app.js"></script>
<script src="${ctx!''}/javascript/login.js"></script>
</body>
</html>
