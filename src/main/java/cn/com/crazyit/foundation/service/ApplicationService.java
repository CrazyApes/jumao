package cn.com.crazyit.foundation.service;

import cn.com.crazyit.foundation.pojo.ApplicationPojo;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
public interface ApplicationService<Pojo extends ApplicationPojo> {

    Pageable getPage(Integer page, Integer size);

    ResponseEntity<?> save(Pojo pojo);

    ResponseEntity<?> delete(Long id);

    ResponseEntity<?> modify(Pojo pojo);

    ResponseEntity<?> findOne(Long id);

    ResponseEntity<?> findAll();

    ResponseEntity<?> findAll(Integer page, Integer size);
}
