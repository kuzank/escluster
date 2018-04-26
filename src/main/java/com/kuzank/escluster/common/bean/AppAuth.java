package com.kuzank.escluster.common.bean;

import java.lang.annotation.*;

/**
 * <p>Description: </p>
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface AppAuth {
    AuthEnum role();
}
