package cn.com.crazyit.foundation.service.impl;

import cn.com.crazyit.core.environment.PageableEnvironment;
import cn.com.crazyit.core.exception.DataException;
import cn.com.crazyit.foundation.dao.ApplicationDAO;
import cn.com.crazyit.foundation.pojo.ApplicationPojo;
import cn.com.crazyit.foundation.service.ApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
public abstract class ApplicationServiceImpl<Pojo extends ApplicationPojo> implements ApplicationService<Pojo> {

    private PageableEnvironment pageableEnvironment;
    private ApplicationDAO<Pojo> dao;

    public ApplicationServiceImpl(PageableEnvironment pageableEnvironment, ApplicationDAO<Pojo> dao) {
        this.pageableEnvironment = pageableEnvironment;
        this.dao = dao;
    }

    @Override
    public Pageable getPage(Integer page, Integer size) {
        if (null == page || page < 0) {
            page = pageableEnvironment.getDefaultPage();
        }
        if (null == size || size < 0) {
            size = pageableEnvironment.getDefaultSize();
        } else if (size < pageableEnvironment.getMinSize()) {
            size = pageableEnvironment.getMinSize();
        } else if (size > pageableEnvironment.getMaxSize()) {
            size = pageableEnvironment.getMaxSize();
        }
        return new PageRequest(page, size);
    }

    @Override
    public Pojo save(Pojo pojo) {
        if (null == pojo) {
            throw new DataException("参数pojo为null");
        } else if (null != pojo.getId()) {
            throw new DataException("参数pojo的id不为null");
        } else {
            return dao.save(pojo);
        }
    }

    @Override
    public void delete(Long id) {
        if (null == id) {
            throw new DataException("参数id为null");
        } else {
            dao.delete(id);
        }
    }

    @Override
    public Pojo modify(Pojo pojo) {
        if (null == pojo) {
            throw new DataException("参数pojo为null");
        } else if (null == pojo.getId()) {
            throw new DataException("参数pojo的id为null");
        } else {
            return dao.save(pojo);
        }
    }

    @Override
    public Pojo findOne(Long id) {
        if (null == id) {
            throw new DataException("参数id为null");
        } else {
            return dao.findOne(id);
        }
    }

    @Override
    public List<Pojo> findAll() {
        return dao.findAll();
    }

    @Override
    public Page<Pojo> findAll(Integer page, Integer size) {
        return dao.findAll(getPage(page, size));
    }
}
