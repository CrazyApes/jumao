package cn.com.crazyit.dao;

import cn.com.crazyit.foundation.dao.ProductDAO;
import cn.com.crazyit.foundation.pojo.Product;
import cn.com.crazyit.service.BaseSpringBootTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author zhangxun
 * @Desciption:
 * @date 2017/8/19
 * @title
 */
public class ProductDAOTest extends BaseSpringBootTest {

    @Resource
    ProductDAO productDAO;

    @Test
    public void testAppDao(){
        Product product = new Product();
        product.setBaseDepth(123);
        product.setBaseHeight(1234);
        productDAO.save(product);
        System.out.print("111");
    }

}
