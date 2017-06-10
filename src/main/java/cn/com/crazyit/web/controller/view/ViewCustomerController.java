package cn.com.crazyit.web.controller.view;

import cn.com.crazyit.foundation.pojo.Customer;
import cn.com.crazyit.foundation.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/9.
 */
@Controller
@RequestMapping(value = ViewCustomerController.URL_MAPPING, produces = "text/html;charset=utf-8")
public class ViewCustomerController extends ViewBaseController {

    protected final static String URL_MAPPING = "/customers";

    private final Logger LOG = LoggerFactory.getLogger(ViewCustomerController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/page")
    public String customersPage() {
        return "/view/customer/customer_page";
    }

    @GetMapping(value = "/edit/{id}")
    public String customersEditId(@PathVariable(value = "id") Long id, ModelMap map) {
        if (0L != id) {
            Customer customer = this.customerService.findOne(id);
            if (null == customer) {
                this.do404();
                LOG.warn("跳转客户修改页面失败：Customer(id = {}) == null", id);
            } else {
                map.addAttribute("customer", customer);
            }
        }

        return "/view/customer/customer_edit";
    }
}
