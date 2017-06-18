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
import cn.com.crazyit.foundation.pojo.query.EmployeeQuery;
import cn.com.crazyit.foundation.pojo.temp.LoginEmployee;
import cn.com.crazyit.foundation.service.EmployeeService;
import cn.com.crazyit.util.CryptologyUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/4.
 */
@Service
@Transactional
public class EmployeeServiceImpl extends AppServiceImpl<Employee> implements EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(PageableEnvironment pageableEnvironment, EmployeeDAO employeeDAO) {
        super(pageableEnvironment, employeeDAO);
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    private TokenEnvironment tokenEnvironment;

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
            Employee employee = this.employeeDAO.findByUsername(username);
            if (null == employee) {
                return new RestResult<>(
                        ResultCode.FAILURE,
                        null,
                        new FormErrorItem("username", "账户名不存在"));
            } else {
                if (!employee.getPassword().equals(CryptologyUtil.md5Encrypt(password))) {
                    return new RestResult<>(
                            ResultCode.FAILURE,
                            null,
                            new FormErrorItem("password", "您输入的密码不正确"));
                } else {
                    if (EmployeeStatus.INACTIVE.equals(employee.getStatus())) {
                        return new RestResult<>(
                                ResultCode.FAILURE,
                                null,
                                new FormErrorItem("username", "员工离职，账号已经被冻结"));
                    } else {
                        LoginEmployee loginEmployee = LoginEmployee.build(employee);
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

    @Override
    public Page<Employee> findAll(EmployeeQuery employeeQuery, Integer page, Integer size) {
        if (null == employeeQuery) {
            throw new DataException("条件查询员工信息失败：employeeQuery == null");
        } else {
            return this.employeeDAO.findAll(employeeQuery.getConditional(), this.getPageRequest(page, size));
        }
    }

    @Override
    public Integer modifySetStatusWhereIdIn(EmployeeStatus status, Long[] ids) {
        if (null == status) {
            throw new DataException("批量更改员工状态失败：status == null");
        } else if (null == ids) {
            throw new DataException("批量更改员工状态失败：ids == null");
        } else {
            return this.employeeDAO.modifySetStatusWhereIdIn(status, new Date(), ids);
        }
    }

    @Override
    public Boolean validateUsernameRepeat(String username, Long id) {
        if (null == username) {
            throw new DataException("验证员工账户名重复失败：fax == null");
        } else if (null == id) {
            throw new DataException("验证员工账户名重复失败：id == null");
        } else {
            return this.employeeDAO.countByUsernameAndIdNot(username, id) > 0;
        }
    }

    @Override
    public Boolean validateMobileRepeat(String mobile, Long id) {
        if (null == mobile) {
            throw new DataException("验证员工手机号码重复失败：mobile == null");
        } else if (null == id) {
            throw new DataException("验证员工手机号码重复失败：id == null");
        } else {
            return this.employeeDAO.countByMobileAndIdNot(mobile, id) > 0;
        }
    }

    @Override
    public Boolean validateEmailRepeat(String email, Long id) {
        if (null == email) {
            throw new DataException("验证员工邮箱地址重复失败：email == null");
        } else if (null == id) {
            throw new DataException("验证员工邮箱地址重复失败：id == null");
        } else {
            return this.employeeDAO.countByEmailAndIdNot(email, id) > 0;
        }
    }

    @Override
    public String loadMd5PasswordById(Long id) {
        if (null == id) {
            throw new DataException("根据ID查询加密后的密码失败：id == null");
        } else {
            return this.employeeDAO.loadMd5PasswordById(id);
        }
    }
}
