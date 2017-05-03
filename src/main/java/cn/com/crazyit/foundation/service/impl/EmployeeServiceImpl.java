package cn.com.crazyit.foundation.service.impl;

import cn.com.crazyit.core.constant.EmployeeStatus;
import cn.com.crazyit.core.environment.PageableEnvironment;
import cn.com.crazyit.core.exception.DataException;
import cn.com.crazyit.foundation.dao.ApplicationDAO;
import cn.com.crazyit.foundation.dao.EmployeeAuthDAO;
import cn.com.crazyit.foundation.dao.EmployeeDAO;
import cn.com.crazyit.foundation.pojo.Employee;
import cn.com.crazyit.foundation.pojo.EmployeeAuth;
import cn.com.crazyit.foundation.service.EmployeeService;
import cn.com.crazyit.util.CryptologyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
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
    private EmployeeAuthDAO employeeAuthDAO;

    @Override
    public ResponseEntity<?> login(String username, String password) throws DataException {
        EmployeeAuth auth = employeeAuthDAO.findByUsername(username);
        if (null == auth) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("您输入的用户名未注册");
        } else {
            if (!auth.getPassword().equals(CryptologyUtil.md5Encrypt(password))) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("您输入的密码不正确");
            } else {
                Employee employee = this.employeeDAO.findOne(auth.getEmployeeId());
                if (null == employee) {
                    throw new DataException("无法查询到与EmployeeAuth相匹配的Employee");
                } else if (employee.getStatus().equals(EmployeeStatus.INACTIVE)) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("员工离职，该账户已经失效，请联系管理员");
                } else {
                    return ResponseEntity.ok(employee);
                }
            }
        }
    }
}
