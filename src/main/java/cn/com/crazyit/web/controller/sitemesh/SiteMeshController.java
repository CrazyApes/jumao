package cn.com.crazyit.web.controller.sitemesh;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/31.
 */
@Controller
@RequestMapping(value = SiteMeshController.MAPPING_URL, produces = "text/html;charset=utf-8")
public class SiteMeshController {

    protected static final String MAPPING_URL = "/sitemesh";

    @GetMapping(value = "/template")
    public String siteMeshTemplateGet() {
        return "/sitemesh/template";
    }
}
