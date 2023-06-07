package org.cluo.framework.management.model.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author canfuu.cts
 * @class CmsBarInfo
 * @date 2023/6/3 19:49
 */
public class CmsBarInfo {
    private KeyLabel topBar;

    private List<KeyLabel> sideBars = new ArrayList<>();

    private String defaultSideBar;

    private String defaultSubSideBar;

    private String defaultSideBarTitle;

    private String defaultSubSideBarTitle;

    public String getDefaultSideBar() {
        return defaultSideBar;
    }

    public CmsBarInfo setDefaultSideBar(String defaultSideBar) {
        this.defaultSideBar = defaultSideBar;
        return this;
    }

    public String getDefaultSubSideBar() {
        return defaultSubSideBar;
    }

    public CmsBarInfo setDefaultSubSideBar(String defaultSubSideBar) {
        this.defaultSubSideBar = defaultSubSideBar;
        return this;
    }

    public String getDefaultSideBarTitle() {
        return defaultSideBarTitle;
    }

    public CmsBarInfo setDefaultSideBarTitle(String defaultSideBarTitle) {
        this.defaultSideBarTitle = defaultSideBarTitle;
        return this;
    }

    public String getDefaultSubSideBarTitle() {
        return defaultSubSideBarTitle;
    }

    public CmsBarInfo setDefaultSubSideBarTitle(String defaultSubSideBarTitle) {
        this.defaultSubSideBarTitle = defaultSubSideBarTitle;
        return this;
    }

    public CmsBarInfo() {
    }

    public CmsBarInfo(KeyLabel topBar, List<KeyLabel> sideBars) {
        this.topBar = topBar;
        this.sideBars.addAll(sideBars);
    }

    public KeyLabel getTopBar() {
        return topBar;
    }

    public CmsBarInfo setTopBar(KeyLabel topBar) {
        this.topBar = topBar;
        return this;
    }

    public List<KeyLabel> getSideBars() {
        return sideBars;
    }

    public CmsBarInfo setSideBars(List<KeyLabel> sideBars) {
        this.sideBars = sideBars;
        return this;
    }
}
