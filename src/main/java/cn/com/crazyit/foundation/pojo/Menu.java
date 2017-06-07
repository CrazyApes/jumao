package cn.com.crazyit.foundation.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/31.
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "MENU")
public class Menu extends AppPojo {

    // 菜单标题
    @Column(length = 10, nullable = false, unique = true)
    private String title;
    // 图标样式
    @Column(length = 20)
    private String iconStyle;
    // 父节点id
    @Column(nullable = false)
    private Long parentId = 0L;
    // 链接地址
    @Column(length = 30, nullable = false)
    private String linkUri = "#";
    // 排序号
    @Column(length = 2, nullable = false)
    private Integer sortId = 99;
    // 是否只读
    @Column(nullable = false)
    private Boolean readOnly = Boolean.FALSE;
}
