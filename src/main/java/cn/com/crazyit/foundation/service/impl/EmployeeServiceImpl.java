package cn.com.crazyit.foundation.service.impl;

import cn.com.crazyit.core.constant.EmployeeStatus;
import cn.com.crazyit.core.constant.ResultCode;
import cn.com.crazyit.core.environment.PageableEnvironment;
import cn.com.crazyit.core.environment.TokenEnvironment;
import cn.com.crazyit.core.exception.DataException;
import cn.com.crazyit.core.result.FormErrorItem;
import cn.com.crazyit.core.result.RestResult;
import cn.com.crazyit.foundation.dao.EmployeeDAO;
import cn.com.crazyit.foundation.pojo.Employee;
import cn.com.crazyit.foundation.pojo.EmployeeAuth;
import cn.com.crazyit.foundation.pojo.temp.LoginEmployee;
import cn.com.crazyit.foundation.service.EmployeeAuthService;
import cn.com.crazyit.foundation.service.EmployeeService;
import cn.com.crazyit.util.CryptologyUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/4.
 */
@Service
@Transactional
public class EmployeeServiceImpl extends ApplicationServiceImpl<Employee> implements EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(PageableEnvironment pageableEnvironment, EmployeeDAO employeeDAO) {
        super(pageableEnvironment, employeeDAO);
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    private TokenEnvironment tokenEnvironment;

    @Autowired
    private EmployeeAuthService employeeAuthService;

    @Override
    public Employee save(Employee pojo) {
        return super.save(pojo);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public Employee modify(Employee pojo) {
        return super.modify(pojo);
    }

    @Override
    public Employee findOne(Long id) {
        return super.findOne(id);
    }

    @Override
    public List<Employee> findAll() {
        return super.findAll();
    }

    @Override
    public Page<Employee> findAll(Integer page, Integer size) {
        return super.findAll(page, size);
    }

    @Override
    public RestResult<?> login(String username, String password) {
        if (null == username) {
            throw new DataException("参数username为null");
        } else if (null == password) {
            throw new DataException("参数password为null");
        } else {
            EmployeeAuth auth = this.employeeAuthService.findByUsername(username);
            if (null == auth) {
                return new RestResult<>(
                        ResultCode.FAILURE,
                        null,
                        new FormErrorItem("username", "账户名不存在"));
            } else {
                if (!auth.getPassword().equals(CryptologyUtil.md5Encrypt(password))) {
                    return new RestResult<>(
                            ResultCode.FAILURE,
                            null,
                            new FormErrorItem("password", "您输入的密码不正确"));
                } else {
                    Employee employee = this.findOne(auth.getEmployeeId());
                    if (null == employee) {
                        throw new DataException("根据EmployeeAuth(id = " + auth.getId() + ")查找不到匹配的Employee");
                    } else {
                        if (EmployeeStatus.INACTIVE.equals(employee.getStatus())) {
                            return new RestResult<>(
                                    ResultCode.FAILURE,
                                    null,
                                    new FormErrorItem("username", "员工离职，账号已经被冻结"));
                        } else {
                            LoginEmployee loginEmployee = LoginEmployee.build(employee, auth);
                            String token = Jwts.builder()
                                    .setClaims(loginEmployee.toMap())
                                    .setExpiration(tokenEnvironment.buildExpire())
                                    .signWith(SignatureAlgorithm.HS256, tokenEnvironment.getKey())
                                    .compact();
                            return new RestResult<>(ResultCode.SUCCESS, null, token);
                        }
                    }
                }
            }
        }
    }
}
