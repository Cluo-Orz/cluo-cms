package org.cluo.framework.management.model.common.params;

import org.cluo.framework.management.model.common.enums.ContentFieldType;

/**
 * @author canfuu.cts
 * @class ActionField
 * @date 2023/6/4 16:05
 */
public class ActionFieldModel {

    private String displayName;

    private String name;

    private Boolean required = false;
    private String placeholder;
    private ContentFieldType type = ContentFieldType.Text;
    private String regex;
    private String tips = "数据格式错误";

    private String defaultValue;

    private Boolean richText = false;

    private String dataUrl;

    private Integer fileCount;

    private String fileSuffix;

    private String fileName;

    private Boolean bool = false;

    public static ActionFieldModel build(String name) {
        return new ActionFieldModel().setName(name).setDisplayName(name);
    }

    public static ActionFieldModel build(String name, ContentFieldType type) {
        return new ActionFieldModel().setName(name).setDisplayName(name).setType(type);
    }

    public static ActionFieldModel build(String name, ContentFieldType type, String regex) {
        return new ActionFieldModel().setName(name).setDisplayName(name).setType(type).setRegex(regex);
    }

    public static ActionFieldModel build(String name, String displayName) {
        return new ActionFieldModel().setName(name).setDisplayName(name);
    }

    public static ActionFieldModel build(String name, String displayName, ContentFieldType type) {
        return new ActionFieldModel().setName(name).setType(type).setDisplayName(name);
    }

    public static ActionFieldModel build(String name, String displayName, ContentFieldType type, String regex) {
        return new ActionFieldModel().setName(name).setType(type).setRegex(regex).setDisplayName(name);
    }

    public static ActionFieldModel build(String name, String displayName, ContentFieldType type, String regex, String tips) {
        return new ActionFieldModel().setName(name).setType(type).setRegex(regex).setDisplayName(name).setTips(tips);
    }


    public String getPlaceholder() {
        return placeholder;
    }

    public ActionFieldModel setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public String getName() {
        return name;
    }

    public ActionFieldModel setName(String name) {
        this.name = name;
        if (this.placeholder == null) {
            this.placeholder = "请输入" + name;
        }
        return this;
    }

    public ContentFieldType getType() {
        return type;
    }

    public ActionFieldModel setType(ContentFieldType type) {
        this.type = type;
        return this;
    }

    public String getRegex() {
        return regex;
    }

    public ActionFieldModel setRegex(String regex) {
        this.regex = regex;
        return this;
    }

    public String getTips() {
        return tips;
    }

    public ActionFieldModel setTips(String tips) {
        this.tips = tips;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ActionFieldModel setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }


    public Boolean getRequired() {
        return required;
    }

    public ActionFieldModel setRequired(Boolean required) {
        this.required = required;
        return this;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public ActionFieldModel setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
        return this;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public ActionFieldModel setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public Integer getFileCount() {
        return fileCount;
    }

    public ActionFieldModel setFileCount(Integer fileCount) {
        this.fileCount = fileCount;
        return this;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public ActionFieldModel setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public ActionFieldModel setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public Boolean getRichText() {
        return richText;
    }

    public ActionFieldModel setRichText(Boolean richText) {
        this.richText = richText;
        return this;
    }

    public Boolean getBool() {
        return bool;
    }

    public ActionFieldModel setBool(Boolean bool) {
        bool = bool;
        return this;
    }
}
