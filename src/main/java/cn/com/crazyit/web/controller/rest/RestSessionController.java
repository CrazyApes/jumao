package cn.com.crazyit.web.controller.rest;

import cn.com.crazyit.core.result.RestResult;
import cn.com.crazyit.foundation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/1.
 */
@RestController
@RequestMapping(value = RestSessionController.URL_MAPPING, produces = "application/json;charset=utf-8")
public class RestSessionController {

    protected static final String URL_MAPPING = "/api/sessions";

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public RestResult<?> apiSessionsPost(
            @RequestParam String username,
            @RequestParam String password) {
        return this.employeeService.login(username, password);
    }
}
