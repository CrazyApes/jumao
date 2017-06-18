package cn.com.crazyit.web.controller.view;

import cn.com.crazyit.foundation.pojo.Employee;
import cn.com.crazyit.foundation.pojo.Role;
import cn.com.crazyit.foundation.service.EmployeeService;
import cn.com.crazyit.foundation.service.RoleService;
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
 *         Created on 2017/6/13.
 */
@Controller
@RequestMapping(value = ViewEmployeeController.URL_MAPPING, produces = "text/html;charset=utf-8")
public class ViewEmployeeController extends ViewBaseController {

    protected final static String URL_MAPPING = "/employees";

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RoleService roleService;

    private final Logger LOG = LoggerFactory.getLogger(ViewEmployeeController.class);

    @GetMapping(value = "/page")
    public String employeesPageGet() {
        return "/view/employee/employee_page";
    }

    @GetMapping(value = "/edit/{id}")
    public String employeesEditIdGet(@PathVariable(value = "id") Long id, ModelMap map) {
        if (!id.equals(0L)) {
            Employee employee = this.employeeService.findOne(id);
            if (null == employee) {
                this.do404();
                LOG.warn("跳转员工编辑页面失败：Employee(id = {}) == null", id);
            } else if (employee.getReadOnly()) {
                this.do500();
                LOG.warn("跳转员工编辑页面失败：Employee(id = {}, readOnly = true)", id);
            } else {
                map.addAttribute("employee", employee);
            }
        }

        // 查找角色列表并放置入响应域中
        {
            List<Role> roleList = this.roleService.findAll();
            map.addAttribute("roleList", roleList);
        }

        return "/view/employee/employee_edit";
    }
}
