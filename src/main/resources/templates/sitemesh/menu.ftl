<aside class="main-sidebar">
    <section class="sidebar">
        <div class="user-panel">
            <div class="pull-left image">
                <img src="<#if loginEmployee??>${loginEmployee.headImageUri!''}</#if>"
                    class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p><#if loginEmployee??>${loginEmployee.name!'加载中...'}<#else>加载中...</#if></p>
                <a href="#">
                    <i class="fa fa-smile-o"></i>
                    <#if loginEmployee??> ${loginEmployee.structureTitle!'加载中...'}<#else> 加载中...</#if>
                </a>
            </div>
        </div>
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
                    <button type="submit" name="search" id="search-btn" class="btn btn-flat">
                        <i class="fa fa-search"></i>
                    </button>
                </span>
            </div>
        </form>
        <ul class="sidebar-menu">
            <li class="header">操作菜单</li>
            <#if IndexMenuVOList?? && IndexMenuVOList?size gt 0>
                <#list IndexMenuVOList as item>
                    <li class="treeview <#if parentMenuId?? && parentMenuId?c == item.id?c>active</#if>">
                        <a href="${item.linkUr!'#'}?menuId=${item.id?c}">
                            <i class="${item.iconStyle!''}"></i>
                            <span>${item.title!'加载中...'}</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                        </a>
                        <#if item.children?? && item.children?size gt 0>
                            <ul class="treeview-menu">
                                <#list item.children as child>
                                    <li>
                                        <a href="${child.linkUri!'#'}?menuId=${child.id?c}&parentMenuId=${item.id?c}">
                                            <i class="${child.iconStyle!'fa fa-circle-o'}"></i>
                                            ${child.title!'加载中...'}
                                        </a>
                                    </li>
                                </#list>
                            </ul>
                        </#if>
                    </li>
                </#list>
            </#if>
        </ul>
    </section>
</aside>