package cn.com.crazyit.core.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
@Configuration
@ConfigurationProperties(prefix = DruidDataSourceConfiguration.PREFIX)
public class DruidDataSourceConfiguration {

    protected final static String PREFIX = "spring.datasource";

    @Setter private String driverClassName;
    @Setter private String url;
    @Setter private String username;
    @Setter private String password;
    @Setter private Integer initialSize;
    @Setter private Integer minIdle;
    @Setter private Integer maxActive;
    @Setter private Long maxWait;
    @Setter private Long timeBetweenEvictionRunsMillis;
    @Setter private Long minEvictableIdleTimeMillis;
    @Setter private String validationQuery;
    @Setter private Boolean testWhileIdle;
    @Setter private Boolean testOnBorrow;
    @Setter private Boolean testOnReturn;
    @Setter private String filters;
    @Setter private String connectionProperties;
    @Setter private String servletUsername;
    @Setter private String servletPassword;
    @Setter private String servletResetEnable;
    @Setter private String servletUrl;
    @Setter private String filterPattern;
    @Setter private String filterExclusions;

    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    @ConditionalOnMissingBean({DataSource.class, DruidDataSource.class})
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        try {
            dataSource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataSource.setConnectionProperties(connectionProperties);
        return dataSource;
    }

    @Bean
    @ConditionalOnWebApplication
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet());
        registrationBean.addUrlMappings(servletUrl);
        registrationBean.addInitParameter("loginUsername", servletUsername);
        registrationBean.addInitParameter("loginPassword", servletPassword);
        registrationBean.addInitParameter("resetEnable", servletResetEnable);
        return registrationBean;
    }

    @Bean
    @ConditionalOnWebApplication
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new WebStatFilter());
        registrationBean.addUrlPatterns(filterPattern);
        registrationBean.addInitParameter("exclusions", filterExclusions);
        return registrationBean;
    }
}
