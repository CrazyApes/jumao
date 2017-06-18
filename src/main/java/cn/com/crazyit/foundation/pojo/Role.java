package cn.com.crazyit.foundation.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "ROLE")
public class Role extends AppPojo {

    @Column(length = 10, nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private Boolean readOnly = Boolean.FALSE;

    @ManyToMany
    @JoinTable(name = "role_menu",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id"))
    private Set<Menu> menus;

    @ManyToMany
    @JoinTable(name = "role_button",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "button_id"))
    private Set<Button> buttons;

}
