package cn.com.crazyit.foundation.pojo.query;

import cn.com.crazyit.core.constant.OrderType;
import cn.com.crazyit.foundation.pojo.Menu;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/7.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class MenuQuery extends AppQuery<Menu> {

    public MenuQuery(String keywords, OrderType orderType, String properties) {
        super(keywords, orderType, properties);
    }

    @Override
    public Specification<Menu> getConditional() {
        return (root, query, builder) -> {
            if (null != this.getKeywords()) {
                Path<String> path = root.get("title");
                query.where(builder.like(path, "%" + this.getKeywords() + "%"));
            }
            if (null != this.getOrderType() && null != this.getOrderProperties()) {
                Path<Object>  path = root.get(this.getOrderProperties());
                switch (this.getOrderType()) {
                    case ASC:
                        query.orderBy(builder.asc(path));
                        break;
                    case DESC:
                        query.orderBy(builder.desc(path));
                        break;
                }
            }
            return query.getRestriction();
        };
    }
}
