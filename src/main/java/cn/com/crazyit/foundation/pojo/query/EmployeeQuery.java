package cn.com.crazyit.foundation.pojo.query;

import cn.com.crazyit.foundation.pojo.Employee;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;


/**
 * @author CrazyApeDX
 *         Created on 2017/6/13.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class EmployeeQuery extends AppQuery<Employee> {

    public EmployeeQuery(String keywords) {
        super(keywords);
    }

    @Override
    public Specification<Employee> getConditional() {
        return (root, query, builder) -> {
            if (null != this.getKeywords() && this.getKeywords().length() > 0) {
                String keywords = "%" + this.getKeywords() + "%";
                Path<String> usernamePath = root.get("username");
                Path<String> namePath = root.get("name");
                Path<String> emailPath = root.get("email");
                Path<String> mobilePath = root.get("mobile");
                Path<String> roleTitlePath = root.join("role").get("title");

                query.where(builder.or(
                        builder.like(usernamePath, keywords),
                        builder.like(namePath, keywords),
                        builder.like(emailPath, keywords),
                        builder.like(mobilePath, keywords),
                        builder.like(roleTitlePath, keywords)));
            }

            query = this.getOrderQuery(root, query, builder);

            return query.getRestriction();
        };
    }
}
