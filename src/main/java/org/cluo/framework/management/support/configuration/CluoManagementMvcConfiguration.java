package org.cluo.framework.management.support.configuration;

import org.cluo.framework.json.JSONUtil;
import org.cluo.framework.management.support.CluoManagementProperties;
import org.cluo.framework.management.support.CluoManagementUIProperties;
import org.cluo.framework.management.support.adapter.CmsRequestBodyAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author canfuu.cts
 * @class CluoManagementConfiguration
 * @date 2023/6/3 03:24
 */
@EnableWebMvc
public class CluoManagementMvcConfiguration implements WebMvcConfigurer {

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Value("${spring.mvc.servlet.path:}")
    private String servletPath;

    @Value("${server.port}")
    private String port;

    @Bean
    public CluoManagementProperties cluoManagementProperties() {
        //  host: http://localhost:${server.port}
        //  base-path: ${cluo-management.host}${server.servlet.context-path:}${spring.mvc.servlet.path:}
        //  data-path: ${cluo-management.base-path}
        String host = "http://localhost:"+port;
        String basePath = host + contextPath + servletPath;
        String dataPath = basePath;

        return new CluoManagementProperties(
                basePath,
                dataPath,
                host
        );
    }

    @Bean
    public CluoManagementUIProperties cluoManagementUIProperties() {
        return new CluoManagementUIProperties();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(JSONUtil.getObjectMapper());
        list.add(mappingJackson2HttpMessageConverter);
        resolvers.add(new CmsRequestBodyAdvice(list));
    }

}