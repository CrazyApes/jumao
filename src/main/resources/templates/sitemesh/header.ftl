<header class="main-header">

    <a href="/" class="logo">
        <span class="logo-mini"><b>易</b></span>
        <span class="logo-lg"><b>乐易装</b></span>
    </a>
    <nav class="navbar navbar-static-top">
        <a href="javascript:void(0);" class="sidebar-toggle" data-toggle="offcanvas" role="button"></a>

        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="<#if loginEmployee??>${loginEmployee.headImageUri!''}</#if>"
                            class="user-image" alt="User Image">
                        <span class="hidden-xs">
                            <#if loginEmployee??>${loginEmployee.name!'加载中...'}<#else>加载中...</#if>
                        </span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="user-header">
                            <img src="<#if loginEmployee??>${loginEmployee.headImageUri!''}</#if>"
                                class="img-circle" alt="User Image">
                            <p>
                                <#if loginEmployee??>${loginEmployee.name!'加载中...'}<#else>加载中...</#if>
                                <small>
                                    <#if loginEmployee??>${loginEmployee.structureTitle!'加载中...'}<#else>加载中...</#if>
                                </small>
                            </p>
                        </li>
                        <li class="user-body">
                            <div class="row">
                                <div class="col-xs-4 text-center">
                                    <a href="#">Followers</a>
                                </div>
                                <div class="col-xs-4 text-center">
                                    <a href="#">Sales</a>
                                </div>
                                <div class="col-xs-4 text-center">
                                    <a href="#">Friends</a>
                                </div>
                            </div>
                        </li>
                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="#" class="btn btn-default btn-flat">Profile</a>
                            </div>
                            <div class="pull-right">
                                <a href="#" class="btn btn-default btn-flat">Sign out</a>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>