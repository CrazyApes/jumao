package cn.com.crazyit.foundation.pojo.query;

import cn.com.crazyit.core.constant.OrderType;
import cn.com.crazyit.foundation.pojo.AppPojo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/7.
 */
@Getter
@Setter
@ToString
public abstract class AppQuery<Pojo extends AppPojo> {

    protected AppQuery(String keywords, OrderType orderType, String orderProperties) {
        if (null != keywords && keywords.length() > 0) {
            this.keywords = keywords;
        }
        if (null != orderType) {
            orderType = orderType;
        }
        if (null != orderProperties && orderProperties.length() > 0) {
            this.orderProperties = orderProperties;
        }
    }

    private String keywords;
    private OrderType orderType = OrderType.ASC;
    private String orderProperties = "id";

    public abstract Specification<Pojo> getConditional();
}
