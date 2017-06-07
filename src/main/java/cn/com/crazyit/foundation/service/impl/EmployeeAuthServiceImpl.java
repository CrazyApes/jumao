package cn.com.crazyit.foundation.service.impl;

import cn.com.crazyit.core.environment.PageableEnvironment;
import cn.com.crazyit.core.exception.DataException;
import cn.com.crazyit.foundation.dao.EmployeeAuthDAO;
import cn.com.crazyit.foundation.pojo.EmployeeAuth;
import cn.com.crazyit.foundation.service.EmployeeAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/2.
 */
@Service
@Transactional
public class EmployeeAuthServiceImpl extends AppServiceImpl<EmployeeAuth>
        implements EmployeeAuthService {

    private EmployeeAuthDAO employeeAuthDAO;

    @Autowired
    public EmployeeAuthServiceImpl(PageableEnvironment pageableEnvironment, EmployeeAuthDAO employeeAuthDAO) {
        super(pageableEnvironment, employeeAuthDAO);
        this.employeeAuthDAO = employeeAuthDAO;
    }

    @Override
    public EmployeeAuth save(EmployeeAuth pojo) {
        return super.save(pojo);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public EmployeeAuth modify(EmployeeAuth pojo) {
        return super.modify(pojo);
    }

    @Override
    public EmployeeAuth findOne(Long id) {
        return super.findOne(id);
    }

    @Override
    public List<EmployeeAuth> findAll() {
        return super.findAll();
    }

    @Override
    public Page<EmployeeAuth> findAll(Integer page, Integer size) {
        return super.findAll(page, size);
    }

    @Override
    public EmployeeAuth findByUsername(String username) {
        if (null == username) {
            throw new DataException("参数username为null");
        } else {
            return this.employeeAuthDAO.findByUsername(username);
        }
    }
}
