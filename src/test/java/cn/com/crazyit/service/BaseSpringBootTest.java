package cn.com.crazyit.service;

import cn.com.crazyit.ApplicationStarter;
import cn.com.crazyit.foundation.pojo.temp.MenuBar;
import cn.com.crazyit.foundation.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author zhangxun
 * @Desciption:
 * @date 2017/8/19
 * @title
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=ApplicationStarter.class)// 指定spring-boot的启动类
public class BaseSpringBootTest {

    @Autowired
    MenuService menuService;

    @Test
    public void testOne(){
        List<MenuBar> menuTree = menuService.getMenuTree();
        System.out.print(menuTree);
    }
}
