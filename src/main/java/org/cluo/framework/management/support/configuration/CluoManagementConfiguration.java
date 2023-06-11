package org.cluo.framework.management.support.configuration;

import org.cluo.framework.json.JSONUtil;
import org.cluo.framework.management.support.adapter.CmsRequestBodyAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author canfuu.cts
 * @class CluoManagementConfiguration
 * @date 2023/6/3 03:24
 */
@Configuration
public class CluoManagementConfiguration implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(JSONUtil.getObjectMapper());
        list.add(mappingJackson2HttpMessageConverter);
        resolvers.add(new CmsRequestBodyAdvice(list));
    }
}