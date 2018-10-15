package com.lingtoo.common.utils;

import org.springframework.context.ApplicationContext;

/**
 * Created: 2015/11/4.
 * Author: Qiannan Lu
 */
public class ApplicationContextHolder {

    private static ApplicationContext applicationContext;

    private ApplicationContextHolder() {

    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextHolder.applicationContext = applicationContext;
    }
}