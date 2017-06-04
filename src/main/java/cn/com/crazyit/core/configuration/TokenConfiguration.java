package cn.com.crazyit.core.configuration;

import cn.com.crazyit.core.environment.TokenEnvironment;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CrazyApeDX
 *         Created on 2017/6/4.
 */
@Configuration
@ConfigurationProperties(prefix = TokenConfiguration.PREFIX)
public class TokenConfiguration {

    protected static final String PREFIX = "application.jwt";

    @Setter private String key;

    @Bean
    public TokenEnvironment tokenEnvironment() {
        return new TokenEnvironment(key);
    }
}
