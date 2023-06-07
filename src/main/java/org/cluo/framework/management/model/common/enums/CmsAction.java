package org.cluo.framework.management.model.common.enums;

/**
 * @author canfuu.cts
 * @class CmsAction
 * @date 2023/6/4 06:56
 */
public enum CmsAction {
    ListSelectData(ContentModelType.ManageListData),
    ListSelectOptions(ContentModelType.ManageListData),
    ListInsertData(ContentModelType.ManageListData),
    ListUpdateData(ContentModelType.ManageListData),
    ListClickButton(ContentModelType.ManageListData),
    ;

    private final ContentModelType belongType;

    CmsAction(ContentModelType belongType) {
        this.belongType = belongType;
    }

    public ContentModelType getBelongType() {
        return belongType;
    }
}
