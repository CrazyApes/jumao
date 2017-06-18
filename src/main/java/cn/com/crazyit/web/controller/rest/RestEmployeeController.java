package cn.com.crazyit.web.controller.rest;

import cn.com.crazyit.core.constant.EmployeeStatus;
import cn.com.crazyit.core.constant.ResultCode;
import cn.com.crazyit.core.result.RestResult;
import cn.com.crazyit.foundation.pojo.Employee;
import cn.com.crazyit.foundation.pojo.form.EmployeeForm;
import cn.com.crazyit.foundation.pojo.query.EmployeeQuery;
import cn.com.crazyit.foundation.pojo.temp.GridData;
import cn.com.crazyit.foundation.pojo.temp.ValidatorResult;
import cn.com.crazyit.foundation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/13.
 */
@RestController
@RequestMapping(value = RestEmployeeController.URL_MAPPING, produces = "application/json;charset=utf-8")
public class RestEmployeeController extends RestBaseController {

    protected final static String URL_MAPPING = "/api/employees";

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public GridData<Employee> apiCustomersGet(@RequestParam Integer offset, @RequestParam Integer size,
                                              @RequestParam(required = false) String keywords) {
        Integer page = this.getPage(offset, size);
        Page<Employee> EmployeePage = this.employeeService.findAll(new EmployeeQuery(keywords), page, size);
        return GridData.build(EmployeePage);
    }

    @PostMapping(value = "/status/inactive")
    public RestResult<Integer> apiEmployeesStatusInactivePost(Long[] ids) {
        Integer count = this.employeeService.modifySetStatusWhereIdIn(EmployeeStatus.INACTIVE, ids);
        return new RestResult<>(ResultCode.SUCCESS, null, count);
    }

    @PostMapping(value = "/validator/username")
    public ValidatorResult apiCustomersValidatorTitlePost(@RequestParam String username, @RequestParam Long id) {
        return new ValidatorResult(!this.employeeService.validateUsernameRepeat(username, id));
    }

    @PostMapping(value = "/validator/mobile")
    public ValidatorResult apiCustomersValidatorPhonePost(@RequestParam String mobile, @RequestParam Long id) {
        return new ValidatorResult(!this.employeeService.validateMobileRepeat(mobile, id));
    }

    @PostMapping(value = "/validator/email")
    public ValidatorResult apiCustomersValidatorFaxPost(@RequestParam String email, @RequestParam Long id) {
        return new ValidatorResult(!this.employeeService.validateEmailRepeat(email, id));
    }

    @PostMapping
    public RestResult<?> apiCustomersPost(@Valid EmployeeForm employeeForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.errorFormSubmit(bindingResult);
        } else {
            this.employeeService.save(employeeForm.transformToEmployee());
            return new RestResult<>(ResultCode.SUCCESS, null, null);
        }
    }

    @PutMapping(value = "/{id}")
    public RestResult<?> apiCustomersIdPut(@Valid EmployeeForm employeeForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.errorFormSubmit(bindingResult);
        } else {
            Employee employee = employeeForm.transformToEmployee();
            // Spring Data JPA的一个小BUG，不能智能的识别哪些字段不修改，所以只能手动把不修改的值设置进入POJO
            if (null == employee.getPassword()) {
                employee.setPassword(this.employeeService.loadMd5PasswordById(employee.getId()));
            }
            this.employeeService.modify(employee);
            return new RestResult<>(ResultCode.SUCCESS, null, null);
        }
    }
}
