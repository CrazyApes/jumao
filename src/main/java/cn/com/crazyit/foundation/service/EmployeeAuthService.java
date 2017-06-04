package cn.com.crazyit.foundation.service;

import cn.com.crazyit.foundation.pojo.EmployeeAuth;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/2.
 */
public interface EmployeeAuthService extends ApplicationService<EmployeeAuth> {

    EmployeeAuth findByUsername(String username);
}
