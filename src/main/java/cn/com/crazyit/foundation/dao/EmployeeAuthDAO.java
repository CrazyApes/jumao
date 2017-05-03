package cn.com.crazyit.foundation.dao;

import cn.com.crazyit.foundation.pojo.EmployeeAuth;
import org.springframework.stereotype.Repository;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
@Repository
public interface EmployeeAuthDAO extends ApplicationDAO<EmployeeAuth> {

    EmployeeAuth findByEmployeeId(Long employeeId);

    EmployeeAuth findByUsername(String username);
}
