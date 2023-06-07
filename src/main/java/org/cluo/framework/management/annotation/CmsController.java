package org.cluo.framework.management.annotation;

import org.cluo.framework.management.model.common.enums.ContentModelType;
import org.cluo.framework.management.model.common.enums.Icon;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@ResponseBody
public @interface CmsController {
    @AliasFor(
        annotation = Controller.class
    )
    String value() default "";

    ContentModelType type() default ContentModelType.ManageListData;

    String top();

    String left();

    Icon icon() default Icon.BarsOutlined;

    boolean isDefault() default false;

    String leftSub();

}