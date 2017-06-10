package cn.com.crazyit.foundation.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/25.
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "CUSTOMER")
public class Customer extends AppPojo {

    // 识别名
    @Column(nullable = false, unique = true, length = 40)
    private String title;

    // 地址
    @Column(nullable = false, length = 100)
    private String address;

    // 电话号码(座机或者手机)
    @Column(nullable = false, unique = true, length = 15)
    private String phone;

    // 传真
    @Column(unique = true, length = 20)
    private String fax;

    // 是否生效
    @Column(nullable = false)
    private Boolean enable = Boolean.TRUE;
}
