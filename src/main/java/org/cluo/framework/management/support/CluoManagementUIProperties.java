package org.cluo.framework.management.support;

import org.cluo.framework.management.model.common.enums.PublicLayout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author canfuu.cts
 * @class CluoManagementProperties
 * @date 2023/6/2 00:01
 */
@ConfigurationProperties(prefix = "cluo-management.ui")
public class CluoManagementUIProperties {


    private PublicLayout layout = PublicLayout.AppTopSide;

    public PublicLayout getLayout() {
        return layout;
    }

    public CluoManagementUIProperties setLayout(PublicLayout layout) {
        this.layout = layout;
        return this;
    }

    public CluoManagementUIProperties setLayout(String layout) {
        this.layout = PublicLayout.valueOf(layout);
        return this;
    }
}
