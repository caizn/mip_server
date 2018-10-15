package com.lingtoo.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created: 2015/11/4.
 * Author: Qiannan Lu
 */
public class ApplicationContextProvider implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.setApplicationContext(applicationContext);
    }
}
