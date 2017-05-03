package cn.com.crazyit.foundation.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "ROLE")
public class Role extends ApplicationPojo {
}
