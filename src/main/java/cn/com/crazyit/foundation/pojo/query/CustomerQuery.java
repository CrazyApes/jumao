package cn.com.crazyit.foundation.pojo.query;

import cn.com.crazyit.core.constant.OrderType;
import cn.com.crazyit.foundation.pojo.Customer;


/**
 * @author CrazyApeDX
 *         Created on 2017/6/9.
 */
public class CustomerQuery extends AppQuery<Customer> {

    public CustomerQuery() {
        super();
    }

    public CustomerQuery(String keywords) {
        super(keywords);
    }

    public CustomerQuery(String keywords, OrderType orderType, String orderProperties) {
        super(keywords, orderType, orderProperties);
    }
}
