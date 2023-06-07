package org.cluo.framework.management.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.cluo.framework.management.model.common.enums.PublicLayout;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author canfuu.cts
 * @class PublicInfo
 * @date 2023/6/3 00:13
 */
public class CmsPublicInfo {

    private String basePath;

    private String dataPath;

    private String host;

    private Boolean init = true;

    private PublicLayout layout;


    private List<CmsBarInfo> barInfo = new ArrayList<>();

    private Map<String, List<String>> keyPath = new HashMap<>();

    private String defaultTopBar;

    private String defaultSideBar;

    private String defaultSubSideBar;

    private String defaultSideBarTitle;

    private String defaultSubSideBarTitle;

    private String defaultTopBarTitle;


    @JsonIgnore
    public List<KeyLabel> getTopBarSideBar(String topBarKey) {
        return barInfo.stream().filter(tempInfo->tempInfo.getTopBar().getKey().equals(topBarKey)).findAny().orElse(new CmsBarInfo()).getSideBars();
    }

    @JsonIgnore
    public KeyLabel getTopBar(String topBarKey) {
        CmsBarInfo topBar = barInfo.stream().filter(tempInfo->tempInfo.getTopBar().getKey().equals(topBarKey)).findAny().orElse(new CmsBarInfo());
        return topBar.getTopBar();
    }

    public CmsPublicInfo toKeyPath() {
        barInfo.forEach((barInfo) -> {
            String key = barInfo.getTopBar().getKey();
            keyPath.put(barInfo.getTopBar().getKey(), List.of(barInfo.getTopBar().getKey()));
            List<KeyLabel> sideBars = barInfo.getSideBars();
            sideBars.forEach(keyLabel -> {
                keyPath.put(keyLabel.getKey(), List.of(keyLabel.getKey(), key));
                List<KeyLabel> children = keyLabel.getChildren();
                if (children != null) {
                    children.forEach(keyLabel1 -> {
                        keyPath.put(keyLabel1.getKey(), List.of(keyLabel1.getKey(), keyLabel.getKey(), key));
                    });
                }
            });

        });
        return this;
    }

    public Map<String, List<String>> getKeyPath() {
        return keyPath;
    }

    public CmsPublicInfo setKeyPath(Map<String, List<String>> keyPath) {
        this.keyPath = keyPath;
        return this;
    }

    public List<CmsBarInfo> getBarInfo() {
        return barInfo;
    }

    public CmsPublicInfo setBarInfo(List<CmsBarInfo> barInfo) {
        this.barInfo = barInfo;
        return this;
    }

    public String getDefaultSideBar() {
        return defaultSideBar;
    }

    public CmsPublicInfo setDefaultSideBar(String defaultSideBar) {
        this.defaultSideBar = defaultSideBar;
        return this;
    }

    public String getDefaultSubSideBar() {
        return defaultSubSideBar;
    }

    public CmsPublicInfo setDefaultSubSideBar(String defaultSubSideBar) {
        this.defaultSubSideBar = defaultSubSideBar;
        return this;
    }

    public PublicLayout getLayout() {
        return layout;
    }

    public CmsPublicInfo setLayout(PublicLayout layout) {
        this.layout = layout;
        return this;
    }


    public String getDefaultTopBar() {
        return defaultTopBar;
    }

    public CmsPublicInfo setDefaultTopBar(String defaultTopBar) {
        this.defaultTopBar = defaultTopBar;
        return this;
    }

    public Boolean getInit() {
        return init;
    }

    public CmsPublicInfo setInit(Boolean init) {
        this.init = init;
        return this;
    }

    public String getDefaultTopBarTitle() {
        return defaultTopBarTitle;
    }

    public CmsPublicInfo setDefaultTopBarTitle(String defaultTopBarTitle) {
        this.defaultTopBarTitle = defaultTopBarTitle;
        return this;
    }

    public String getDefaultSideBarTitle() {
        return defaultSideBarTitle;
    }

    public CmsPublicInfo setDefaultSideBarTitle(String defaultSideBarTitle) {
        this.defaultSideBarTitle = defaultSideBarTitle;
        return this;
    }

    public String getDefaultSubSideBarTitle() {
        return defaultSubSideBarTitle;
    }

    public CmsPublicInfo setDefaultSubSideBarTitle(String defaultSubSideBarTitle) {
        this.defaultSubSideBarTitle = defaultSubSideBarTitle;
        return this;
    }

    public String getHost() {
        return host;
    }

    public CmsPublicInfo setHost(String host) {
        this.host = host;
        return this;
    }

    public String getBasePath() {
        return basePath;
    }

    public CmsPublicInfo setBasePath(String basePath) {
        this.basePath = basePath;
        return this;
    }

    public String getDataPath() {
        return dataPath;
    }

    public CmsPublicInfo setDataPath(String dataPath) {
        this.dataPath = dataPath;
        return this;
    }
}
