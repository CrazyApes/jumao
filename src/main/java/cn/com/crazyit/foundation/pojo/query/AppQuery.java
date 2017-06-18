package cn.com.crazyit.foundation.pojo.query;

import cn.com.crazyit.core.constant.OrderType;
import cn.com.crazyit.foundation.pojo.AppPojo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/7.
 */
@Getter
@Setter
@ToString
public abstract class AppQuery<Pojo extends AppPojo> {

    protected AppQuery() {
        super();
    }

    protected AppQuery(String keywords) {
        super();
        this.keywords = keywords;
    }

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

    public Specification<Pojo> getConditional() {
        return (root, query, builder) -> {
            if (null != this.getKeywords()) {
                Path<String> path = root.get("title");
                query.where(builder.like(path, "%" + this.getKeywords() + "%"));
            }
            query = this.getOrderQuery(root, query, builder);
            return query.getRestriction();
        };
    }

    protected CriteriaQuery getOrderQuery(Root root, CriteriaQuery query, CriteriaBuilder builder) {
        if (null != this.getOrderType() && null != this.getOrderProperties() && this.getOrderProperties().length() > 0) {
            Path path = root.get(this.getOrderProperties());
            switch (this.getOrderType()) {
                case ASC:
                    query.orderBy(builder.asc(path));
                    break;
                case DESC:
                    query.orderBy(builder.desc(path));
                    break;
            }
        }
        return query;
    }
}
