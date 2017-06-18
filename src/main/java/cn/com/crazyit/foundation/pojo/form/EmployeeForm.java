package cn.com.crazyit.foundation.pojo.form;

import cn.com.crazyit.core.constant.EmployeeStatus;
import cn.com.crazyit.core.constant.Sex;
import cn.com.crazyit.foundation.pojo.Employee;
import cn.com.crazyit.foundation.pojo.Role;
import cn.com.crazyit.util.CryptologyUtil;
import cn.com.crazyit.util.DateFormatUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/18.
 */
@Getter
@Setter
@ToString
public class EmployeeForm implements Serializable {

    private Long id;

    private String headImageUri;

    @NotNull(message = "员工账户名不能为空")
    @Length(min = 6, max = 20, message = "员工账户名的长度必须在6~20位之间")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "员工账户名只能输入英文和数字")
    private String username;

    private Long roleId;

    @NotNull(message = "员工姓名不能为空")
    @Length(min = 2, max = 5, message = "员工姓名的长度在2~5之间")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]+$", message = "员工姓名只能输入中文")
    private String name;

    private String sex;

    @Pattern(regexp = "^(1[35784]\\d{9})$|^$", message = "请输入正确的电话号码")
    private String mobile;

    @Email(message = "请输入正确的邮箱地址")
    private String email;

    @NotNull(message = "员工入职时间不能为空")
    private String activeTime;

    private Boolean status;

    @Pattern(regexp = "^[A-Za-z0-9]+$|^$", message = "密码只能输入英文和数字")
    private String password;

    public Employee transformToEmployee() {
        Employee employee = new Employee();

        // 修改用户时才会添加id属性
        if (0 != this.id) {
            employee.setId(this.id);
        }
        employee.setUsername(this.username);

        // 设置角色id
        {
            Role role = new Role();
            role.setId(this.roleId);
            employee.setRole(role);
        }

        employee.setName(this.name);
        employee.setSex(Enum.valueOf(Sex.class, this.sex));
        if (null == headImageUri || headImageUri.length() == 0) {
            employee.setHeadImageUri("/images/header.png");
        } else {
            employee.setHeadImageUri(headImageUri);
        }
        if (null != mobile || mobile.length() > 0) {
            employee.setMobile(this.mobile);
        }
        if (null != email || email.length() > 0) {
            employee.setEmail(this.email);
        }

        employee.setActiveTime(DateFormatUtil.format(this.activeTime, "yyyy-MM-dd"));

        if (this.status) {
            employee.setStatus(EmployeeStatus.ACTIVE);
            employee.setInactiveTime(null);
        } else {
            employee.setStatus(EmployeeStatus.INACTIVE);
            employee.setInactiveTime(new Date());
        }

        if (null != password && password.length() > 0) {
            employee.setPassword(CryptologyUtil.md5Encrypt(password));
        } else if (0 == this.id && (null == password || password.length() == 0)) {
            employee.setPassword(CryptologyUtil.md5Encrypt("123456"));
        }

        return employee;
    }
}
