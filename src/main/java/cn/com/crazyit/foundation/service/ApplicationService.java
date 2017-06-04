package cn.com.crazyit.foundation.service;

import cn.com.crazyit.foundation.pojo.ApplicationPojo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
public interface ApplicationService<Pojo extends ApplicationPojo> {

    Pageable getPage(Integer page, Integer size);

    Pojo save(Pojo pojo);

    void delete(Long id);

    Pojo modify(Pojo pojo);

    Pojo findOne(Long id);

    List<Pojo> findAll();

    Page<Pojo> findAll(Integer page, Integer size);
}
