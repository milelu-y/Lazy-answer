package com.lazy.common.annotation;

import java.lang.annotation.*;

/**
 * @author Lazy
 * @date 2021/8/4 11:23
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UpdateTime {
}
