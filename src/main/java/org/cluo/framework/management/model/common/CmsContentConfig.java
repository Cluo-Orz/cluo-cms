package org.cluo.framework.management.model.common;

import org.cluo.framework.management.model.common.enums.ContentModelType;
import org.cluo.framework.management.model.common.params.ActionModel;
import org.cluo.framework.management.model.common.params.SimpleHttpModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author canfuu.cts
 * @class CmsContentConfig
 * @date 2023/6/3 23:57
 */
public class CmsContentConfig {

    private ContentModelType type;

    private List<ActionModel> actions = new ArrayList<>();


    public ContentModelType getType() {
        return type;
    }

    public CmsContentConfig setType(ContentModelType type) {
        this.type = type;
        return this;
    }

    public List<ActionModel> getActions() {
        return actions;
    }

    public CmsContentConfig setActions(List<ActionModel> actions) {
        this.actions.addAll(actions);
        return this;
    }
}
