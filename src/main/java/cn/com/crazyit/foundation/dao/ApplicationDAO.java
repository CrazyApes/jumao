package cn.com.crazyit.foundation.dao;

import cn.com.crazyit.foundation.pojo.ApplicationPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
@NoRepositoryBean
public interface ApplicationDAO<Pojo extends ApplicationPojo>
        extends JpaRepository<Pojo, Long>, JpaSpecificationExecutor<Pojo> {
}
