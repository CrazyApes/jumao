package cn.com.crazyit.web.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * @author CrazyApeDX
 *         Created on 2017/5/31.
 */
public class SiteMeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", "/sitemesh/template")
                .addExcludedPath("/sitemesh/template")
                .addExcludedPath("/login")
                .addExcludedPath("/logout");
    }
}
