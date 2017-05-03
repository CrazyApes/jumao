package cn.com.crazyit.foundation.service;

import cn.com.crazyit.core.exception.DataException;
import cn.com.crazyit.foundation.pojo.Employee;
import org.springframework.http.ResponseEntity;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
public interface EmployeeService extends ApplicationService<Employee> {

    ResponseEntity<?> login(String username, String password) throws DataException;
}
