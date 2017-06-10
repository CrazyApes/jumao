package cn.com.crazyit.foundation.service;

import cn.com.crazyit.foundation.pojo.Menu;
import cn.com.crazyit.foundation.pojo.query.MenuQuery;
import cn.com.crazyit.foundation.pojo.temp.MenuBar;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/7.
 */
public interface MenuService extends AppService<Menu> {

    List<MenuBar> getMenuTree();

    Page<Menu> findAll(MenuQuery menuQuery, Integer page, Integer size);

    List<Menu> findByParentId(Long parentId);

    Boolean validateTitleRepeat(String title, Long id);
}
