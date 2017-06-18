package cn.com.crazyit.foundation.pojo.temp;

import cn.com.crazyit.core.constant.Sex;
import cn.com.crazyit.foundation.pojo.Employee;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/4.
 */
@Getter
@Setter
@ToString
public class LoginEmployee implements Serializable {

    private Long id;
    private String name;
    private String headImageUri;
    private String roleTitle;
    private String sex;
    private Boolean bindingEmail;
    private Boolean bindingMobile;

    public static LoginEmployee build(Employee employee) {
        LoginEmployee loginEmployee = new LoginEmployee();
        loginEmployee.setId(employee.getId());
        loginEmployee.setName(employee.getName());
        loginEmployee.setHeadImageUri(employee.getHeadImageUri());
        loginEmployee.setRoleTitle(employee.getRole().getTitle());
        if (employee.getSex().equals(Sex.MALE)) {
            loginEmployee.setSex("男");
        } else if (employee.getSex().equals(Sex.FEMALE)) {
            loginEmployee.setSex("女");
        } else {
            loginEmployee.setSex("保密");
        }
        loginEmployee.setBindingEmail(null == employee.getEmail() ? Boolean.FALSE : Boolean.TRUE);
        loginEmployee.setBindingMobile(null == employee.getMobile() ? Boolean.FALSE : Boolean.TRUE);
        return loginEmployee;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("headImageUri", headImageUri);
        map.put("roleTitle", roleTitle);
        map.put("sex", sex);
        map.put("bindingEmail", bindingEmail);
        map.put("bindingMobile", bindingMobile);
        return map;
    }
}
