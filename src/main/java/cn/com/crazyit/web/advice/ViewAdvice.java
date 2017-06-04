package cn.com.crazyit.web.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/1.
 */
@ControllerAdvice(basePackages = "cn.com.crazyit.web.controller.view")
public class ViewAdvice {

    @ModelAttribute(value = "ctx")
    public String ctx(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        return scheme + "://" + serverName + ":" + serverPort + contextPath;
    }
}
