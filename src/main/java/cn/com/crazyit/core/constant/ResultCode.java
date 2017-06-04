package cn.com.crazyit.core.constant;

import lombok.Getter;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/4.
 */
@Getter
public enum ResultCode {

    SUCCESS(0),
    FAILURE(-1);

    private Integer value;

    ResultCode(Integer value) {
        this.value = value;
    }
}
