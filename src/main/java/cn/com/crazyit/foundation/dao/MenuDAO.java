package cn.com.crazyit.foundation.dao;

import cn.com.crazyit.foundation.pojo.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/7.
 */
@Repository
public interface MenuDAO extends AppDAO<Menu> {

    List<Menu> findByParentId(Long parentId);

    Integer countByTitleAndIdNot(String title, Long id);
}
