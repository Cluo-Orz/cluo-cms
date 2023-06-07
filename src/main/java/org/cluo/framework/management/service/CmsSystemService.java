package org.cluo.framework.management.service;

import org.cluo.framework.management.model.common.*;
import org.cluo.framework.management.model.common.enums.ContentFieldType;
import org.cluo.framework.management.model.common.enums.ContentModelType;
import org.cluo.framework.management.model.common.enums.Icon;
import org.cluo.framework.management.model.common.params.ActionModel;
import org.cluo.framework.management.model.common.params.ActionFieldModel;
import org.cluo.framework.management.model.common.params.SimpleHttpModel;
import org.cluo.framework.management.support.CluoManagementUIProperties;
import org.cluo.framework.management.support.CmsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author canfuu.cts
 * @class SystemService
 * @date 2023/6/2 00:00
 */
@Service
public class CmsSystemService {

    @Value("${server.servlet.context-path}")
    private String contentPath;

    @Value("${cluo-management.host}")
    private String host;

    @Value("${cluo-management.base-path}")
    private String basePath;

    @Value("${cluo-management.data-path}")
    private String dataPath;

    @Autowired
    private CmsApi cmsApi;

    @Autowired
    private CluoManagementUIProperties cluoManagementUIProperties;


    public CmsPublicInfo publicInfo() throws Exception {
        CmsPublicInfo cmsPublicInfo = new CmsPublicInfo();
        cmsPublicInfo.setLayout(cluoManagementUIProperties.getLayout());
        cmsPublicInfo.setHost(host);
        cmsPublicInfo.setBasePath(basePath);
        cmsPublicInfo.setDataPath(dataPath);
        cmsPublicInfo.setBarInfo(new ArrayList<>(cmsApi.getKTopKeyVBarInfo().values()));
        cmsPublicInfo.toKeyPath();
        cmsPublicInfo.setDefaultTopBar(cmsApi.getDefaultTopBar());
        cmsPublicInfo.setDefaultTopBarTitle(cmsApi.getDefaultTopBarTitle());
        cmsPublicInfo.setDefaultSideBar(cmsApi.getBarInfo(cmsApi.getDefaultTopBar()).getDefaultSideBar());
        cmsPublicInfo.setDefaultSideBarTitle(cmsApi.getBarInfo(cmsApi.getDefaultTopBar()).getDefaultSideBarTitle());
        cmsPublicInfo.setDefaultSubSideBar(cmsApi.getBarInfo(cmsApi.getDefaultTopBar()).getDefaultSubSideBar());
        cmsPublicInfo.setDefaultSubSideBarTitle(cmsApi.getBarInfo(cmsApi.getDefaultTopBar()).getDefaultSubSideBarTitle());
        return cmsPublicInfo;
    }

    public CmsContentConfig contentConfigs(String topKey, String leftKey, String leftSubKey) {
        return cmsApi.getContentConfig(leftSubKey);
    }
}
