package cn.com.crazyit.core.context;

import cn.com.crazyit.foundation.pojo.temp.LoginEmployee;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/7.
 */
public class SystemContext {

    private final static ThreadLocal<LoginEmployee> employeeContext = ThreadLocal.withInitial(() -> {
        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setId(0L);
        loginEmployee.setName("开发者");
        loginEmployee.setRoleTitle("高级开发工程师");
        loginEmployee.setSex("保密");
        loginEmployee.setHeadImageUri("/images/header.jpg");
        loginEmployee.setBindingMobile(Boolean.TRUE);
        loginEmployee.setBindingEmail(Boolean.TRUE);
        return loginEmployee;
    });

    public final static void setLoginEmployee(LoginEmployee loginEmployee) {
        employeeContext.set(loginEmployee);
    }

    public final static LoginEmployee getLoginEmployee() {
        return employeeContext.get();
    }
}
