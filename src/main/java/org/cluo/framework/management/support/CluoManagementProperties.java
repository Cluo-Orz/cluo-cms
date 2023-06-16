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

    private String basePath;

    private String dataPath;

    private String host;


    public CluoManagementProperties() {
    }

    public CluoManagementProperties(String basePath, String dataPath, String host) {
        this.basePath = basePath;
        this.dataPath = dataPath;
        this.host = host;
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
