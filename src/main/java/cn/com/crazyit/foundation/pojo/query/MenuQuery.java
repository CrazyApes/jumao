package cn.com.crazyit.foundation.pojo.query;

import cn.com.crazyit.core.constant.OrderType;
import cn.com.crazyit.foundation.pojo.Menu;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/7.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MenuQuery extends AppQuery<Menu> {

    public MenuQuery(String keywords) {
        super(keywords);
    }

    public MenuQuery(String keywords, OrderType orderType, String properties) {
        super(keywords, orderType, properties);
    }
}
