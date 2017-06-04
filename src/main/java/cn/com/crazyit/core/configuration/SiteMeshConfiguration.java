package cn.com.crazyit.core.configuration;

import cn.com.crazyit.web.filter.SiteMeshFilter;
import io.jsonwebtoken.Jwts;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/31.
 */
@Configuration
public class SiteMeshConfiguration {

    @Bean
    @ConditionalOnWebApplication
    public FilterRegistrationBean siteMeshFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new SiteMeshFilter());
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        return registrationBean;
    }
}
