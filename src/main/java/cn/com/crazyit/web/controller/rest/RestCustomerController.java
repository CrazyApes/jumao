package cn.com.crazyit.web.controller.rest;

import cn.com.crazyit.core.constant.ResultCode;
import cn.com.crazyit.core.result.RestResult;
import cn.com.crazyit.foundation.pojo.Customer;
import cn.com.crazyit.foundation.pojo.form.CustomerForm;
import cn.com.crazyit.foundation.pojo.query.CustomerQuery;
import cn.com.crazyit.foundation.pojo.temp.GridData;
import cn.com.crazyit.foundation.pojo.temp.ValidatorResult;
import cn.com.crazyit.foundation.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/9.
 */
@RestController
@RequestMapping(value = RestCustomerController.URL_MAPPING, produces = "application/json;charset=utf-8")
public class RestCustomerController extends RestBaseController {

    protected static final String URL_MAPPING = "/api/customers";

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public GridData<Customer> apiCustomersGet(@RequestParam Integer offset, @RequestParam Integer size,
                                              @RequestParam(required = false) String keywords) {
        Integer page = this.getPage(offset, size);
        Page<Customer> customerPage = this.customerService.findAll(new CustomerQuery(keywords), page, size);
        return GridData.build(customerPage);
    }

    @PostMapping(value = "/validator/title")
    public ValidatorResult apiCustomersValidatorTitlePost(@RequestParam String title, @RequestParam Long id) {
        return new ValidatorResult(!this.customerService.validateTitleRepeat(title, id));
    }

    @PostMapping(value = "/validator/phone")
    public ValidatorResult apiCustomersValidatorPhonePost(@RequestParam String phone, @RequestParam Long id) {
        return new ValidatorResult(!this.customerService.validatePhoneRepeat(phone, id));
    }

    @PostMapping(value = "/validator/fax")
    public ValidatorResult apiCustomersValidatorFaxPost(@RequestParam String fax, @RequestParam Long id) {
        return new ValidatorResult(!this.customerService.validateFaxRepeat(fax, id));
    }

    @PostMapping
    public RestResult<?> apiCustomersPost(@Valid CustomerForm customerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.errorFormSubmit(bindingResult);
        } else {
            this.customerService.save(customerForm.transformToCustomer());
            return new RestResult<>(ResultCode.SUCCESS, null, null);
        }

    }

    @PutMapping(value = "/{id}")
    public RestResult<?> apiCustomersIdPut(@Valid CustomerForm customerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.errorFormSubmit(bindingResult);
        } else {
            this.customerService.modify(customerForm.transformToCustomer());
            return new RestResult<>(ResultCode.SUCCESS, null, null);
        }
    }

    @PostMapping(value = "/enable/false")
    public RestResult<Integer> apiCustomersEnableFalsePost(@RequestParam Long[] ids) {
        Integer count = this.customerService.modifySetEnableFalseWhereIdIn(ids);
        return new RestResult<>(ResultCode.SUCCESS, null, count);
    }
}
