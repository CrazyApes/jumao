package cn.com.crazyit.foundation.service.impl;

import cn.com.crazyit.core.environment.PageableEnvironment;
import cn.com.crazyit.core.exception.DataException;
import cn.com.crazyit.foundation.dao.MenuDAO;
import cn.com.crazyit.foundation.pojo.Menu;
import cn.com.crazyit.foundation.pojo.query.MenuQuery;
import cn.com.crazyit.foundation.pojo.temp.MenuBar;
import cn.com.crazyit.foundation.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/7.
 */
@Service
@Transactional
public class MenuServiceImpl extends AppServiceImpl<Menu> implements MenuService {

    private MenuDAO menuDAO;

    @Autowired
    public MenuServiceImpl(PageableEnvironment pageableEnvironment, MenuDAO menuDAO) {
        super(pageableEnvironment, menuDAO);
        this.menuDAO = menuDAO;
    }

    @Override
    public List<MenuBar> getMenuTree() {
        List<Menu> menuList = this.findAll();

        Map<Long, List<MenuBar>> childrenMap = new HashMap<>();
        List<MenuBar> menuBarList = MenuBar.transform(menuList);

        List<MenuBar> result;

        if (null != menuBarList && menuBarList.size() > 0) {
            result = new ArrayList<>();
            menuBarList.forEach(menuBar -> {
                if (menuBar.getParentId().equals(0L)) {
                    result.add(menuBar);
                } else {
                    Long parentId = menuBar.getParentId();
                    List<MenuBar> childrenList = childrenMap.computeIfAbsent(parentId, k -> new ArrayList<>());
                    childrenList.add(menuBar);
                }
            });

            result.forEach(menuBar -> menuBar.setChildren(childrenMap.get(menuBar.getId())));
        } else {
            result = new ArrayList<>(0);
        }
        return result;
    }

    @Override
    public Page<Menu> findAll(MenuQuery menuQuery, Integer page, Integer size) {
        if (null == menuQuery) {
            throw new DataException("条件查询菜单失败：menuQuery == null");
        } else {
            return this.menuDAO.findAll(menuQuery.getConditional(), this.getPageRequest(page, size));
        }
    }
}
