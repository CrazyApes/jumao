package cn.com.crazyit.core.result;

import cn.com.crazyit.core.constant.ResultCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/1.
 */
@Getter
@Setter
@ToString
public class RestResult<Content> implements Serializable {

    public RestResult(ResultCode code, String message, Content content) {
        this.code = code.getValue();
        this.message = message;
        this.content = content;
    }

    private Integer code;
    private String message;
    private Content content;
}
