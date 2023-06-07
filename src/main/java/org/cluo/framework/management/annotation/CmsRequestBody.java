package org.cluo.framework.management.annotation;

import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CmsRequestBody {
    boolean required() default true;
}
