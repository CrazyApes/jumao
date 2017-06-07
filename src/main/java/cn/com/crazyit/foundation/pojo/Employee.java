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

    @Column(length = 5, nullable = false)
    private String name;

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "ROLE_ID")
    private Role role = new Role();

    @Column(length = 40)
    private String headImageUri;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex = Sex.SECRET;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status = EmployeeStatus.ACTIVE;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inactiveTime;

}
