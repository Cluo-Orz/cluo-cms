package org.cluo.framework.management.support;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author canfuu.cts
 * @class CluoManagementProperties
 * @date 2023/6/17 00:00
 */
@ConfigurationProperties(prefix = "cluo-management")
public class CluoManagementProperties {


    //  host: http://localhost:${server.port}
    //  base-path: ${cluo-management.host}${server.servlet.context-path:}${spring.mvc.servlet.path:}
    //  data-path: ${cluo-management.base-path}


    @Value("${cluo-management.host:http://localhost:${server.port}}")
    private String host;

    @Value("${cluo-management.base-path:${cluo-management.host:http://localhost:${server.port}}${server.servlet.context-path:}${spring.mvc.servlet.path:}}")
    private String basePath;

    @Value("${cluo-management.data-path:${cluo-management.host:http://localhost:${server.port}}${server.servlet.context-path:}${spring.mvc.servlet.path:}}")
    private String dataPath;

    public CluoManagementProperties() {
    }

    public String getBasePath() {
        return basePath;
    }

    public CluoManagementProperties setBasePath(String basePath) {
        this.basePath = basePath;
        return this;
    }

    public String getDataPath() {
        return dataPath;
    }

    public CluoManagementProperties setDataPath(String dataPath) {
        this.dataPath = dataPath;
        return this;
    }

    public String getHost() {
        return host;
    }

    public CluoManagementProperties setHost(String host) {
        this.host = host;
        return this;
    }
}
