package cn.com.crazyit.foundation.pojo.form;

import cn.com.crazyit.foundation.pojo.Menu;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/8.
 */
@Getter
@Setter
@ToString
public class MenuForm implements Serializable {

    private Long id;

    @NotNull(message = "菜单标题不能为空")
    @Length(min = 2, max = 10, message = "菜单标题的长度必须在2~10位之间")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]+$", message = "菜单标题只能输入中文")
    private String title;

    @NotNull(message = "链接地址不能为空")
    @Length(min = 1, max = 50, message = "链接地址的长度在1~50之间")
    @Pattern(regexp = "^(\\/?[a-zA-Z0-9]\\/?)+$|^#$", message = "请输入一个正确的不带参数的站内链接地址或者\"#\"")
    private String linkUri;

    @Length(min = 0, max = 20, message = "图标样式的长度在0~20之间")
    private String iconStyle;

    @NotNull(message = "必须选择父级菜单")
    private Long parentId;

    @NotNull(message = "排序号不能为空")
    @Min(value = 1, message = "排序号的为1~99之间的整数")
    @Max(value = 99, message = "排序号的为1~99之间的整数")
    private Integer sortId;

    public Menu transformToMenu() {
        Menu menu = new Menu();
        if (0L != this.id) {
            menu.setId(this.id);
        }
        menu.setTitle(this.title);
        menu.setLinkUri(this.linkUri);
        if (null != this.iconStyle && iconStyle.trim().length() > 0) {
            menu.setIconStyle(this.iconStyle);
        }
        menu.setParentId(this.parentId);
        menu.setSortId(this.sortId);
        return menu;
    }
}
