package cn.com.crazyit.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/31.
 */
@Controller
@RequestMapping(value = ViewIndexController.URL_MAPPING, produces = "text/html;charset=utf-8")
public class ViewIndexController {

    protected static final String URL_MAPPING = "/";

    @GetMapping(value = "index")
    public String index() {
        return "/view/index";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "/view/login";
    }
}
