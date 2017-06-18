package cn.com.crazyit.foundation.service;

import cn.com.crazyit.foundation.pojo.Role;
import cn.com.crazyit.foundation.pojo.query.RoleQuery;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/13.
 */
public interface RoleService extends AppService<Role> {

    Role findByTitle(String title);

    Boolean validateTitleRepeat(String title, Long id);

    Page<Role> findAll(RoleQuery roleQuery, Integer page, Integer size);
}
