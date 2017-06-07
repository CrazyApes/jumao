package cn.com.crazyit.foundation.pojo.temp;

import cn.com.crazyit.foundation.pojo.Menu;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/7.
 */
@Getter
@Setter
@ToString
public class MenuBar implements Serializable {

    private Long id;
    private String title;
    private String iconStyle;
    private String linkUri;
    private Long parentId;
    private Integer sortId;
    private List<MenuBar> children = new ArrayList<>();

    public final static MenuBar transform(Menu menu) {
        MenuBar menuBar = new MenuBar();
        menuBar.setId(menu.getId());
        menuBar.setTitle(menu.getTitle());
        menuBar.setIconStyle(menu.getIconStyle());
        menuBar.setLinkUri(menu.getLinkUri());
        menuBar.setParentId(menu.getParentId());
        menuBar.setSortId(menu.getSortId());
        return menuBar;
    }

    public final static List<MenuBar> transform(List<Menu> menuList) {
        List<MenuBar> menuBarList;
        if (null != menuList && menuList.size() > 0) {
            menuBarList = new ArrayList<>(menuList.size());
            menuList.forEach(menu -> menuBarList.add(MenuBar.transform(menu)));
        } else {
            menuBarList = new ArrayList<>(0);
        }
        return menuBarList;
    }
}
