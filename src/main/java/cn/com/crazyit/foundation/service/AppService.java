package cn.com.crazyit.foundation.service;

import cn.com.crazyit.foundation.pojo.AppPojo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
public interface AppService<Pojo extends AppPojo> {

    Pageable getPageRequest(Integer page, Integer size);

    Pojo save(Pojo pojo);

    void delete(Long id);

    Pojo modify(Pojo pojo);

    Pojo findOne(Long id);

    List<Pojo> findAll();

    Page<Pojo> findAll(Integer page, Integer size);
}
