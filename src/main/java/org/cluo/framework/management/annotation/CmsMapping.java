package org.cluo.framework.management.annotation;

import org.cluo.framework.management.model.common.enums.CmsAction;
import org.cluo.framework.management.model.common.enums.ContentType;
import org.cluo.framework.management.model.common.params.ActionModel;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping(
    method = {RequestMethod.POST}
)
@ResponseBody
public @interface CmsMapping {
    @AliasFor(
        annotation = RequestMapping.class
    )
    String[] path() default {};

    CmsAction[] action();

    boolean fileUpload() default false;

    @AliasFor(
            annotation = RequestMapping.class
    )
    String[] consumes() default {};

    @AliasFor(
            annotation = RequestMapping.class
    )
    String[] produces() default {};
}
