package org.cluo.framework.management.model.common;

import java.io.Serializable;

/**
 * @author canfuu.cts
 * @class ActionProps
 * @date 2023/6/5 23:20
 */
public class ActionProps implements Serializable {
    private Boolean hasPagination = false;

    public Boolean getHasPagination() {
        return hasPagination;
    }

    public ActionProps setHasPagination(Boolean hasPagination) {
        this.hasPagination = hasPagination;
        return this;
    }
}
