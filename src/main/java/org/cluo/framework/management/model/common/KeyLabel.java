package org.cluo.framework.management.model.common;

import org.cluo.framework.management.model.common.enums.Icon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author canfuu.cts
 * @class KeyLabel
 * @date 2023/6/3 00:14
 */
public class KeyLabel {
    private String key;

    private String label;

    /**
     * from @<code>org.cluo.framework.management.model.enums.Icon</code>
     */
    private String icon;


    private List<KeyLabel> children;

    public String getIcon() {
        return icon;
    }

    public KeyLabel(){

    }

    public KeyLabel(String key, String label) {
        this.key = key;
        this.label = label;
    }

    public KeyLabel(String key, String label, Icon icon, KeyLabel... children) {
        this.key = key;
        this.label = label;
        this.icon = icon.name();
        this.children = new ArrayList<>(List.of(children));
    }

    public KeyLabel setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public KeyLabel setIcon(Icon icon) {
        this.icon = icon.name();
        return this;
    }

    public List<KeyLabel> getChildren() {
        return children;
    }

    public KeyLabel setChildren(List<KeyLabel> children) {
        this.children.addAll(children);
        return this;
    }

    public String getKey() {
        return key;
    }

    public KeyLabel setKey(String key) {
        this.key = key;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public KeyLabel setLabel(String label) {
        this.label = label;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyLabel keyLabel = (KeyLabel) o;
        return Objects.equals(key, keyLabel.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
