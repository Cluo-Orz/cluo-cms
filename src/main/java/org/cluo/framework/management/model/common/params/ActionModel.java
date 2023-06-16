package org.cluo.framework.management.model.common.params;

import org.cluo.framework.management.model.common.ActionProps;
import org.cluo.framework.management.model.common.enums.ContentType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author canfuu.cts
 * @class HttpModel
 * @date 2023/6/3 23:55
 */
public class ActionModel implements Serializable {

    private String url;
    private String method;
    private String action;
    private ContentType contentType;
    private String keyField;

    private Integer defaultSize;
    private Integer defaultPage;

    private List<ActionFieldModel> params= new ArrayList<>();
    private List<ActionFieldModel> fields = new ArrayList<>();

    public String getKeyField() {
        return keyField;
    }

    public ActionModel setKeyField(String keyField) {
        this.keyField = keyField;
        return this;
    }

    private ActionProps props = new ActionProps();

    public ActionProps getProps() {
        return props;
    }

    public ActionModel setProps(ActionProps props) {
        this.props = props;
        return this;
    }


    public List<ActionFieldModel> getFields() {
        return fields;
    }

    public ActionModel setFields(List<ActionFieldModel> fields) {
        this.fields = fields;
        return this;
    }

    public static ActionModel build(String url) {
        return new ActionModel().setUrl(url);
    }


    public String getAction() {
        return action;
    }

    public ActionModel setAction(String action) {
        this.action = action;
        return this;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public ActionModel setContentType(ContentType contentType) {
        this.contentType = contentType;
        return this;
    }

    public List<ActionFieldModel> getParams() {
        return params;
    }

    public ActionModel setParams(List<ActionFieldModel> params) {
        this.params = params;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ActionModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public ActionModel setMethod(String method) {
        this.method = method;
        return this;
    }

    public Integer getDefaultSize() {
        return defaultSize;
    }

    public ActionModel setDefaultSize(Integer defaultSize) {
        this.defaultSize = defaultSize;
        return this;
    }

    public Integer getDefaultPage() {
        return defaultPage;
    }

    public ActionModel setDefaultPage(Integer defaultPage) {
        this.defaultPage = defaultPage;
        return this;
    }
}
