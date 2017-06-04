package cn.com.crazyit.foundation.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
public class Menu extends ApplicationPojo {

    // 菜单标题
    private String title;
    // 图标样式
    private String iconStyle;
    // 父节点id
    private Long parentId;
    // 链接地址
    private String linkUri;
    // 排序号
    private Integer sortId;
}
