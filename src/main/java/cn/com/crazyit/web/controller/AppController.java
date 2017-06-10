package cn.com.crazyit.web.controller;


import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/8.
 */
public abstract class AppController {

    private HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }

    private HttpServletResponse getResponse() {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return attributes.getResponse();
    }

    protected void do400() {
        this.getResponse().setStatus(400);
    }

    protected void do404() {
        this.getResponse().setStatus(404);
    }

    protected void do500() {
        this.getResponse().setStatus(500);
    }
}
