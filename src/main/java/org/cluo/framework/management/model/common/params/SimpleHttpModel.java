package org.cluo.framework.management.model.common.params;

/**
 * @author canfuu.cts
 * @class HttpModel
 * @date 2023/6/3 05:00
 */
public class SimpleHttpModel {
    protected String url;

    protected String method;

    public String getUrl() {
        return url;
    }

    public SimpleHttpModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public SimpleHttpModel setMethod(String method) {
        this.method = method;
        return this;
    }
}
