package cn.com.crazyit.foundation.service.impl;

import cn.com.crazyit.core.environment.PageableEnvironment;
import cn.com.crazyit.core.exception.DataException;
import cn.com.crazyit.foundation.dao.CustomerDAO;
import cn.com.crazyit.foundation.pojo.Customer;
import cn.com.crazyit.foundation.pojo.query.CustomerQuery;
import cn.com.crazyit.foundation.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/9.
 */
@Service
@Transactional
public class CustomerServiceImpl extends AppServiceImpl<Customer> implements CustomerService {

    private CustomerDAO customerDAO;

    @Autowired
    public CustomerServiceImpl(PageableEnvironment pageableEnvironment, CustomerDAO customerDAO) {
        super(pageableEnvironment, customerDAO);
        this.customerDAO = customerDAO;
    }

    @Override
    public Page<Customer> findAll(CustomerQuery customerQuery, Integer page, Integer size) {
        if (null == customerQuery) {
            throw new DataException("条件查询客户列表失败：customerQuery == null");
        } else {
            return this.customerDAO.findAll(customerQuery.getConditional(), this.getPageRequest(page, size));
        }
    }

    @Override
    public Boolean validateTitleRepeat(String title, Long id) {
        if (null == title) {
            throw new DataException("验证客户标识名重复失败：title == null");
        } else if (null == id) {
            throw new DataException("验证客户标识名重复失败：id == null");
        } else {
            return this.customerDAO.countByTitleAndIdNot(title, id) > 0;
        }
    }

    @Override
    public Boolean validatePhoneRepeat(String phone, Long id) {
        if (null == phone) {
            throw new DataException("验证客户联系电话重复失败：phone == null");
        } else if (null == id) {
            throw new DataException("验证客户联系电话重复失败：id == null");
        } else {
            return this.customerDAO.countByPhoneAndIdNot(phone, id) > 0;
        }
    }

    @Override
    public Boolean validateFaxRepeat(String fax, Long id) {
        if (null == fax) {
            throw new DataException("验证客户传真重复失败：fax == null");
        } else if (null == id) {
            throw new DataException("验证客户传真名重复失败：id == null");
        } else {
            return this.customerDAO.countByFaxAndIdNot(fax, id) > 0;
        }
    }

    @Override
    public Integer modifySetEnableFalseWhereIdIn(Long[] ids) {
        if (null == ids) {
            throw new DataException("批量失效客户失败：ids == null");
        } else {
            return this.customerDAO.modifySetEnableFalseWhereIdIn(ids);
        }
    }
}
