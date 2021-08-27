package com.lazy.common.annotation;

import java.lang.annotation.*;

/**
 * @author Lazy
 * @date 2021/8/3 15:08
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface DefaultUploadClient {
}
