package cn.com.crazyit.foundation.pojo;

import cn.com.crazyit.core.constant.EmployeeStatus;
import cn.com.crazyit.core.constant.Sex;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "EMPLOYEE")
public class Employee extends AppPojo {

    // 用户名
    @Column(length = 20, nullable = false, updatable = false, unique = true)
    private String username;

    // 密码
    @Column(length = 32, nullable = false)
    private String password;

    // 绑定邮箱
    @Column(length = 40, unique = true)
    private String email;

    // 绑定手机号码
    @Column(length = 11, unique = true)
    private String mobile;

    // 员工姓名
    @Column(length = 5, nullable = false)
    private String name;

    // 员工角色，不使用延迟加载
    @ManyToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    // 员工头像
    @Column(length = 40)
    private String headImageUri;

    // 员工性别
    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex = Sex.SECRET;

    // 是否生效
    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status = EmployeeStatus.ACTIVE;

    // 员工在职时间
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activeTime;

    // 员工失效时间
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inactiveTime;

    // 是否只读
    @Column(nullable = false)
    private Boolean readOnly = Boolean.FALSE;


}
