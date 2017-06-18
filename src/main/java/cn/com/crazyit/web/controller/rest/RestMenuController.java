package cn.com.crazyit.web.controller.rest;

import cn.com.crazyit.core.constant.OrderType;
import cn.com.crazyit.core.constant.ResultCode;
import cn.com.crazyit.core.result.RestResult;
import cn.com.crazyit.foundation.pojo.Menu;
import cn.com.crazyit.foundation.pojo.form.MenuForm;
import cn.com.crazyit.foundation.pojo.query.MenuQuery;
import cn.com.crazyit.foundation.pojo.temp.GridData;
import cn.com.crazyit.foundation.pojo.temp.ValidatorResult;
import cn.com.crazyit.foundation.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/7.
 */
@RestController
@RequestMapping(value = RestMenuController.URL_MAPPING, produces = "application/json;charset=utf-8")
public class RestMenuController extends RestBaseController {

    protected final static String URL_MAPPING = "/api/menus";

    private final Logger LOG = LoggerFactory.getLogger(RestMenuController.class);

    @Autowired
    private MenuService menuService;

    @GetMapping
    public GridData<Menu> apiMenusGet(@RequestParam Integer offset, @RequestParam Integer size,
                                      @RequestParam(required = false) String keywords) {

        Integer page = this.getPage(offset, size);

        Page<Menu> menuPage = this.menuService.findAll(new MenuQuery(keywords), page, size);

        return GridData.build(menuPage);
    }

    @PostMapping(value = "/validator/title")
    public ValidatorResult apiMenusValidatorsTitle(@RequestParam String title, @RequestParam Long id) {
        return new ValidatorResult(!this.menuService.validateTitleRepeat(title, id));
    }

    @PutMapping(value = "/{id}")
    public RestResult<?> apiMenusIdPut(@Valid MenuForm menuForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.errorFormSubmit(bindingResult);
        } else {
            this.menuService.modify(menuForm.transformToMenu());
            return new RestResult<>(ResultCode.SUCCESS, null, null);
        }
    }

    @PostMapping
    public RestResult<?> apiMenusPost(@Valid MenuForm menuForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.errorFormSubmit(bindingResult);
        } else {
            this.menuService.save(menuForm.transformToMenu());
            return new RestResult<>(ResultCode.SUCCESS, null, null);
        }
    }
}
