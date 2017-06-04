package cn.com.crazyit.core.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/4.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class FormErrorItem implements Serializable {

    private String name;
    private String message;
}
