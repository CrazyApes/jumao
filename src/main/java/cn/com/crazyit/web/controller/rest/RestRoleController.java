package cn.com.crazyit.web.controller.rest;

import cn.com.crazyit.foundation.pojo.Role;
import cn.com.crazyit.foundation.pojo.query.RoleQuery;
import cn.com.crazyit.foundation.pojo.temp.GridData;
import cn.com.crazyit.foundation.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/13.
 */
@RestController
@RequestMapping(value = RestRoleController.URL_MAPPING, produces = "application/json;charset=utf-8")
public class RestRoleController extends RestBaseController {

    protected static final String URL_MAPPING = "/api/roles";

    private final Logger LOG = LoggerFactory.getLogger(RestRoleController.class);

    @Autowired
    private RoleService roleService;

    @GetMapping
    public GridData<Role> apiRolesGet(@RequestParam Integer offset, @RequestParam Integer size,
                                      @RequestParam(required = false) String keywords) {
        Integer page = this.getPage(offset, size);

        Page<Role> rolePage = this.roleService.findAll(new RoleQuery(keywords), page, size);

        return GridData.build(rolePage);
    }
}
