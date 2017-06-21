package cn.com.crazyit.core.configuration;

import cn.com.crazyit.core.environment.ImageLocationEnvironment;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/3.
 */
@Configuration
@ConfigurationProperties(prefix = ImageLocationConfiguration.PREFIX)
public class ImageLocationConfiguration {

    protected final static String PREFIX = "application.images";

    @Setter private String bucket;
    @Setter private String[] cdnHosts;

    @Bean
    public ImageLocationEnvironment applicationEnvironment() {
        return new ImageLocationEnvironment(bucket, cdnHosts);
    }

}
