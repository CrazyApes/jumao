package cn.com.crazyit.foundation.service.impl;

import cn.com.crazyit.core.environment.PageableEnvironment;
import cn.com.crazyit.foundation.dao.ApplicationDAO;
import cn.com.crazyit.foundation.pojo.ApplicationPojo;
import cn.com.crazyit.foundation.service.ApplicationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<?> save(Pojo pojo) {
        return ResponseEntity.ok(dao.save(pojo));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        dao.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Override
    public ResponseEntity<?> modify(Pojo pojo) {
        return ResponseEntity.ok(dao.save(pojo));
    }

    @Override
    public ResponseEntity<?> findOne(Long id) {
        Pojo pojo = dao.findOne(id);
        if (null == pojo) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("您查找的数据不存在");
        } else {
            return ResponseEntity.ok(pojo);
        }
    }

    @Override
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(dao.findAll());
    }

    @Override
    public ResponseEntity<?> findAll(Integer page, Integer size) {
        return ResponseEntity.ok(dao.findAll(getPage(page, size)));
    }
}
