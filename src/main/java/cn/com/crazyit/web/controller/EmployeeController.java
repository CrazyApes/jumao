package cn.com.crazyit.web.controller;

import cn.com.crazyit.foundation.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
@RestController
@RequestMapping(value = EmployeeController.MAPPING_PREFIX, produces = "application/json;charset=utf-8")
public class EmployeeController {

    protected final static String MAPPING_PREFIX = "/employees";

    private final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> employeesIdGet(@PathVariable(value = "id") Long id) {
        return employeeService.findOne(id);
    }
}
