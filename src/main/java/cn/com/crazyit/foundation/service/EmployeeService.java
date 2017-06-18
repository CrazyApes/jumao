package cn.com.crazyit.foundation.service;

import cn.com.crazyit.core.constant.EmployeeStatus;
import cn.com.crazyit.core.result.RestResult;
import cn.com.crazyit.foundation.pojo.Employee;
import cn.com.crazyit.foundation.pojo.query.EmployeeQuery;
import org.springframework.data.domain.Page;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/4.
 */
public interface EmployeeService extends AppService<Employee> {

    RestResult<?> login(String username, String password);

    Page<Employee> findAll(EmployeeQuery employeeQuery, Integer page, Integer size);

    Integer modifySetStatusWhereIdIn(EmployeeStatus status, Long[] ids);

    Boolean validateUsernameRepeat(String username, Long id);

    Boolean validateMobileRepeat(String mobile, Long id);

    Boolean validateEmailRepeat(String email, Long id);

    String loadMd5PasswordById(Long id);
}
