package cn.com.crazyit.foundation.service;

import cn.com.crazyit.core.result.RestResult;
import cn.com.crazyit.foundation.pojo.Employee;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/4.
 */
public interface EmployeeService extends AppService<Employee> {

    RestResult<?> login(String username, String password);
}
