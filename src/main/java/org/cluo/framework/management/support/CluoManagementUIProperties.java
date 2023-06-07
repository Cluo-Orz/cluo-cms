package org.cluo.framework.management.support;

import org.cluo.framework.management.model.common.enums.PublicLayout;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author canfuu.cts
 * @class CluoManagementProperties
 * @date 2023/6/2 00:01
 */
@Component
@ConfigurationProperties(prefix = "cluo-management.ui")
public class CluoManagementUIProperties {


    private PublicLayout layout = PublicLayout.AppFixedSide;

    public PublicLayout getLayout() {
        return layout;
    }

    public CluoManagementUIProperties setLayout(PublicLayout layout) {
        this.layout = layout;
        return this;
    }
}
