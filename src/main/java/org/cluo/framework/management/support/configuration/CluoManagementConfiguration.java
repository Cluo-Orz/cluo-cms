package org.cluo.framework.management.support.configuration;


import org.cluo.framework.management.model.common.enums.PublicLayout;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Properties;

/**
 * @author canfuu.cts
 * @class CluoManagementConfiguration
 * @date 2023/6/3 03:24
 */
@ComponentScan(basePackages = "org.cluo.framework.management")
@ServletComponentScan(basePackages = "org.cluo.framework.management")
public class CluoManagementConfiguration {


}