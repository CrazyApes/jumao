package cn.com.crazyit.foundation.pojo.temp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/8.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ValidatorResult implements Serializable {

    private Boolean valid;
}
