package cn.com.crazyit.web.controller.rest;

import cn.com.crazyit.core.constant.ResultCode;
import cn.com.crazyit.core.result.RestResult;
import cn.com.crazyit.web.controller.AppController;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/7.
 */
public abstract class RestBaseController extends AppController {

    protected Integer getPage(Integer offset, Integer size) {
        if (null == offset || null == size) {
            return 0;
        }

        if (size.equals(0)) {
            return 0;
        }

        return (offset / size);
    }

    protected RestResult<?> errorFormSubmit(BindingResult result) {
        List<ObjectError> allErrors = result.getAllErrors();
        String message = this.errorMsgToHtml(allErrors);
        this.do400();
        return new RestResult<>(ResultCode.FAILURE, message, null);
    }

    private String errorMsgToHtml(List<ObjectError> errorList) {
        if (null != errorList && errorList.size() > 0) {
            StringBuilder builder = new StringBuilder();
            errorList.forEach(error -> builder.append(error.getDefaultMessage()).append("<br/>"));
            return builder.toString();
        } else {
            return null;
        }
    }
}
