package cn.com.crazyit.foundation.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "EMPLOYEE_AUTH")
public class EmployeeAuth extends AppPojo {

    @Column(nullable = false, updatable = false, unique = true)
    private Long employeeId;

    @Column(length = 20, nullable = false, updatable = false, unique = true)
    private String username;

    @Column(length = 32, nullable = false)
    private String password;

    @Column(length = 40, unique = true)
    private String email;

    @Column(length = 11, unique = true)
    private String mobile;
}
