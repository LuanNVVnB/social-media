package com.example.cnd.common.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * File: com.example.cnd.common.aop
 * Description:  <div style="font-weight: bold; color: #0073e6;"> Alias </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">12/9/2023</span>
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Alias {
    /**
     * The alias name for the annotated field.
     *
     * @return The alias name.
     */
    String name();
}
