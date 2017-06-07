package cn.com.crazyit.web.advice;

import cn.com.crazyit.core.context.SystemContext;
import cn.com.crazyit.foundation.pojo.Menu;
import cn.com.crazyit.foundation.pojo.temp.LoginEmployee;
import cn.com.crazyit.foundation.pojo.temp.MenuBar;
import cn.com.crazyit.foundation.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/1.
 */
@ControllerAdvice(basePackages = "cn.com.crazyit.web.controller.view")
public class ViewAdvice {

    @Autowired
    private MenuService menuService;

    @ModelAttribute(value = "ctx")
    public String ctx(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        return scheme + "://" + serverName + ":" + serverPort + contextPath;
    }

    @ModelAttribute(value = "loginEmployee")
    public LoginEmployee loginEmployee() {
        return SystemContext.getLoginEmployee();
    }

    @ModelAttribute(value = "menuTree")
    public List<MenuBar> menuTree() {
        return menuService.getMenuTree();
    }

    @ModelAttribute(value = "currentMenu")
    public Menu currentMenu(Long menuId) {
        if (null == menuId) {
            return null;
        } else {
            return this.menuService.findOne(menuId);
        }
    }

    @ModelAttribute(value = "parentMenu")
    public Menu parentMenu(Long parentMenuId) {
        if (null == parentMenuId) {
            return null;
        } else {
            return this.menuService.findOne(parentMenuId);
        }
    }
}
