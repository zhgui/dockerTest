package com.shark.pcf.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 区间验证接口。
 * 
 * @author Shark
 * @version 1.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RangeValidation {
    double min() default Double.NEGATIVE_INFINITY;

    double max() default Double.POSITIVE_INFINITY;
}
