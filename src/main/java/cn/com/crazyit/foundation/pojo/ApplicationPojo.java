package cn.com.crazyit.foundation.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class ApplicationPojo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
