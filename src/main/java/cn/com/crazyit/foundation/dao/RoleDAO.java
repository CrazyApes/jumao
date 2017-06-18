package cn.com.crazyit.foundation.dao;

import cn.com.crazyit.foundation.pojo.Role;
import org.springframework.stereotype.Repository;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/13.
 */
@Repository
public interface RoleDAO extends AppDAO<Role> {

    Role findByTitle(String title);

    Integer countByTitleAndIdNot(String title, Long id);
}
