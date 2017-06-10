package cn.com.crazyit.foundation.service;

import cn.com.crazyit.foundation.pojo.Customer;
import cn.com.crazyit.foundation.pojo.query.CustomerQuery;
import org.springframework.data.domain.Page;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/9.
 */
public interface CustomerService extends AppService<Customer> {

    Page<Customer> findAll(CustomerQuery customerQuery, Integer page, Integer size);

    Boolean validateTitleRepeat(String title, Long id);

    Boolean validatePhoneRepeat(String phone, Long id);

    Boolean validateFaxRepeat(String fax, Long id);

    Integer modifySetEnableFalseWhereIdIn(Long[] ids);
}
