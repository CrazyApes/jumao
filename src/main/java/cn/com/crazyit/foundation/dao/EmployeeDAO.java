package cn.com.crazyit.foundation.dao;

import cn.com.crazyit.core.constant.EmployeeStatus;
import cn.com.crazyit.foundation.pojo.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
@Repository
public interface EmployeeDAO extends AppDAO<Employee> {

    Employee findByUsername(String username);

    @Query("update Employee set status = :status, inactiveTime = :now where status <> :status and id in :ids and readOnly = false")
    @Modifying
    Integer modifySetStatusWhereIdIn(@Param(value = "status") EmployeeStatus status,
                                     @Param(value = "now") Date now,
                                     @Param(value = "ids") Long[] ids);

    Integer countByUsernameAndIdNot(String username, Long id);

    Integer countByMobileAndIdNot(String mobile, Long id);

    Integer countByEmailAndIdNot(String email, Long id);

    @Query("select password from Employee where id = :id")
    String loadMd5PasswordById(@Param(value = "id") Long id);
}
