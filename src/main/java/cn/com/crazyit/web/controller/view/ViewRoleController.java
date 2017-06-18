package cn.com.crazyit.web.controller.view;

import cn.com.crazyit.foundation.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/13.
 */
@Controller
@RequestMapping(value = ViewRoleController.URL_MAPPING, produces = "text/html;charset=utf-8")
public class ViewRoleController extends ViewBaseController {

    protected final static String URL_MAPPING = "/roles";

    private final Logger LOG = LoggerFactory.getLogger(ViewRoleController.class);

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/page")
    public String rolesPageGet() {
        return "/view/role/role_page";
    }
}
