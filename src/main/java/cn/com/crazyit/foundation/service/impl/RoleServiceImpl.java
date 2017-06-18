package cn.com.crazyit.foundation.service.impl;

import cn.com.crazyit.core.environment.PageableEnvironment;
import cn.com.crazyit.core.exception.DataException;
import cn.com.crazyit.foundation.dao.RoleDAO;
import cn.com.crazyit.foundation.pojo.Role;
import cn.com.crazyit.foundation.pojo.query.RoleQuery;
import cn.com.crazyit.foundation.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/13.
 */
@Service
@Transactional
public class RoleServiceImpl extends AppServiceImpl<Role> implements RoleService {

    private RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(PageableEnvironment pageableEnvironment, RoleDAO roleDAO) {
        super(pageableEnvironment, roleDAO);
        this.roleDAO = roleDAO;
    }

    @Override
    public Role findByTitle(String title) {
        if (null == title) {
            throw new DataException("根据标题查找角色失败：title == null");
        } else {
            return this.roleDAO.findByTitle(title);
        }
    }

    @Override
    public Boolean validateTitleRepeat(String title, Long id) {
        if (null == title) {
            throw new DataException("验证角色标题是否重复失败：title == null");
        } else if (null == id) {
            throw new DataException("验证角色标题是否重复失败：id == null");
        } else {
            return this.roleDAO.countByTitleAndIdNot(title, id) > 0;
        }
    }

    @Override
    public Page<Role> findAll(RoleQuery roleQuery, Integer page, Integer size) {
        if (null == roleQuery) {
            throw new DataException("条件查询角色失败：roleQuery == null");
        } else {
            return this.roleDAO.findAll(roleQuery.getConditional(), this.getPageRequest(page, size));
        }
    }
}
