package cn.com.crazyit.core.configuration;

import cn.com.crazyit.core.environment.PageableEnvironment;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
@Configuration
@ConfigurationProperties(prefix = PageableConfiguration.PREFIX)
public class PageableConfiguration {

    protected final static String PREFIX = "application.pageable";

    @Setter
    private Integer defaultPage;
    @Setter
    private Integer defaultSize;
    @Setter
    private Integer minSize;
    @Setter
    private Integer maxSize;

    @Bean
    public PageableEnvironment pageableEnvironment() {
        return new PageableEnvironment(defaultPage, defaultSize, minSize, maxSize);
    }
}
