package cn.com.crazyit.web.controller.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Zack
 *         Created on 2017/6/10.
 */
@Controller
@RequestMapping(value = ViewProductController.URL_MAPPING, produces = "text/html;charset=utf-8")
public class ViewProductController {

    protected static final String URL_MAPPING = "/products";

    private final Logger LOG = LoggerFactory.getLogger(ViewProductController.class);

    @GetMapping(value = "/page")
    public String productPage() {
        return "/view/product/product_page";
    }
}
