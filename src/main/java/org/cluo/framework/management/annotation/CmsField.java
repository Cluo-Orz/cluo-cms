package org.cluo.framework.management.annotation;

import org.cluo.framework.management.model.common.enums.ContentFieldType;

import java.lang.annotation.*;

/**
 * @author canfuu.cts
 * @class CmsField
 * @date 2023/6/4 16:45
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CmsField {

    String displayName() default "";

    String name() default "";

    boolean required() default false;

    String placeholder() default "";

    ContentFieldType type() default ContentFieldType.Text;

    String defaultValue() default "";

    String regex() default "";

    String tips() default "数据格式错误";

    String dataUrl() default "";
}
