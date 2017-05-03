package cn.com.crazyit.web.advice;

import cn.com.crazyit.core.exception.BusinessException;
import cn.com.crazyit.core.exception.DataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
@RestControllerAdvice
public class RestWebAdvice {

    @ExceptionHandler
    public ResponseEntity<String> exceptionHandler(Exception e) {
        String message;
        if (e instanceof BusinessException) {
            message = e.getMessage();
        } else if (e instanceof DataException) {
            message = "数据异常，请联系管理员";
        } else {
            message = "出现未知错误，请稍后重试或联系管理员";
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }
}
