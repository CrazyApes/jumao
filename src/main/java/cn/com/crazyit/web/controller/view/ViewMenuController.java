package cn.com.crazyit.web.controller.view;

import cn.com.crazyit.foundation.pojo.Menu;
import cn.com.crazyit.foundation.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/7.
 */
@Controller
@RequestMapping(value = ViewMenuController.URL_MAPPING, produces = "text/html;charset=utf-8")
public class ViewMenuController extends ViewBaseController {

    protected static final String URL_MAPPING = "/menus";

    private final Logger LOG = LoggerFactory.getLogger(ViewMenuController.class);

    @Autowired
    private MenuService menuService;

    @GetMapping(value = "/page")
    public String menuPage() {
        return "/view/menu/menu_page";
    }

    @GetMapping(value = "/edit/{id}")
    public String menuEditId(@PathVariable(value = "id") Long id, ModelMap map) {
        if (!id.equals(0L)) {
            Menu menu = menuService.findOne(id);
            if (null == menu) {
                this.do404();
                LOG.warn("跳转菜单修改页面失败：Menu(id = {}) == null", id);
            } else if (menu.getReadOnly()) {
                this.do500();
                LOG.warn("跳转菜单修改页面失败：Menu(id = {}, readOnly = true)", id);
            } else {
                map.addAttribute("menu", menu);
            }
        }

        // 查询所有的父级菜单列表，并放置入响应域中
        {
            List<Menu> topMenuList = this.menuService.findByParentId(0L);
            map.addAttribute("topMenuList", topMenuList);
        }

        return "/view/menu/menu_edit";
    }
}
