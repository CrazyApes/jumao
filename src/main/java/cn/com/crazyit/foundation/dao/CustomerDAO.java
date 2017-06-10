package cn.com.crazyit.foundation.dao;

import cn.com.crazyit.foundation.pojo.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/9.
 */
@Repository
public interface CustomerDAO extends AppDAO<Customer> {

    Customer findByTitle(String title);

    Customer findByPhone(String phone);

    Customer findByFax(String fax);

    Integer countByTitleAndIdNot(String title, Long id);

    Integer countByPhoneAndIdNot(String phone, Long id);

    Integer countByFaxAndIdNot(String fax, Long id);

    @Query("update Customer set enable = false where id in :ids and enable = true")
    @Modifying
    Integer modifySetEnableFalseWhereIdIn(@Param(value = "ids") Long[] ids);
}
