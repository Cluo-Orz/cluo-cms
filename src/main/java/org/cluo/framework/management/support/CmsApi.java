package org.cluo.framework.management.support;

import org.cluo.framework.management.model.common.CmsBarInfo;
import org.cluo.framework.management.model.common.CmsContentConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author canfuu.cts
 * @class CmsApi
 * @date 2023/6/4 12:57
 */
@Component
public class CmsApi {




    private String defaultTopBar;

    private String defaultTopBarTitle;

    private Map<String, CmsContentConfig> kSubSideKeyVConfig = new ConcurrentHashMap<>();

    private Map<String, CmsBarInfo> kTopKeyVBarInfo = new ConcurrentHashMap<>();

    public CmsContentConfig getContentConfig(String key) {
        return kSubSideKeyVConfig.get(key);
    }

    public CmsApi setContentConfig(String key, CmsContentConfig config) {
        kSubSideKeyVConfig.put(key, config);
        return this;
    }

    public CmsBarInfo getBarInfo(String key) {
        return kTopKeyVBarInfo.get(key);
    }

    public CmsApi setBarInfo(String key, CmsBarInfo barInfo) {
        kTopKeyVBarInfo.put(key, barInfo);
        return this;
    }

    public CmsContentConfig computeIfAbsent(String key, CmsContentConfig config) {
        return kSubSideKeyVConfig.computeIfAbsent(key, (k) -> config);
    }

    public CmsBarInfo computeIfAbsent(String key, CmsBarInfo barInfo) {
        return kTopKeyVBarInfo.computeIfAbsent(key, (k) -> barInfo);
    }

    public Map<String, CmsContentConfig> getKSubSideKeyVConfig() {
        return kSubSideKeyVConfig;
    }

    public Map<String, CmsBarInfo> getKTopKeyVBarInfo() {
        return kTopKeyVBarInfo;
    }

    public String getDefaultTopBar() {
        return defaultTopBar;
    }

    public CmsApi setDefaultTopBar(String defaultTopBar) {
        this.defaultTopBar = defaultTopBar;
        return this;
    }

    public String getDefaultTopBarTitle() {
        return defaultTopBarTitle;
    }

    public CmsApi setDefaultTopBarTitle(String defaultTopBarTitle) {
        this.defaultTopBarTitle = defaultTopBarTitle;
        return this;
    }
    public Map<String, CmsContentConfig> getkSubSideKeyVConfig() {
        return kSubSideKeyVConfig;
    }

    public CmsApi setkSubSideKeyVConfig(Map<String, CmsContentConfig> kSubSideKeyVConfig) {
        this.kSubSideKeyVConfig = kSubSideKeyVConfig;
        return this;
    }

    public Map<String, CmsBarInfo> getkTopKeyVBarInfo() {
        return kTopKeyVBarInfo;
    }

    public CmsApi setkTopKeyVBarInfo(Map<String, CmsBarInfo> kTopKeyVBarInfo) {
        this.kTopKeyVBarInfo = kTopKeyVBarInfo;
        return this;
    }
}
