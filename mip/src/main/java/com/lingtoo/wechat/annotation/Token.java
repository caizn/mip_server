package com.lingtoo.wechat.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created: 2015/11/11.
 * Author: Qiannan Lu
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {

    boolean save() default false;

    boolean remove() default false;
}