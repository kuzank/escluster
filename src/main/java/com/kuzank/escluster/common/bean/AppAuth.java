package com.kuzank.escluster.common.bean;

import java.lang.annotation.*;

/**
 * @author kuzan
 * @since 2018/01/28
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface AppAuth {
    AuthEnum role();
}
