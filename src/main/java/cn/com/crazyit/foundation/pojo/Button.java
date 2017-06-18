package cn.com.crazyit.foundation.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/18.
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "button")
public class Button extends AppPojo {

    @ManyToOne(targetEntity = Menu.class)
    private Menu menu;

    @Column(length = 5, nullable = false)
    private String title;

    @Column(length = 50, nullable = false)
    private String actionUri;
}
