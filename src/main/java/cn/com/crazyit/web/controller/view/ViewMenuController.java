package cn.com.crazyit.web.controller.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/7.
 */
@Controller
@RequestMapping(value = ViewMenuController.URL_MAPPING, produces = "text/html;charset=utf-8")
public class ViewMenuController {

    protected static final String URL_MAPPING = "/menu";

    private final Logger LOG = LoggerFactory.getLogger(ViewMenuController.class);

    @RequestMapping(value = "/page")
    public String menuPage() {
        return "/view/menu/menu_page";
    }
}
