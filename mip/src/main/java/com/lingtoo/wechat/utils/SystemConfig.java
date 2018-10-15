package com.lingtoo.wechat.utils;

import com.lingtoo.common.setting.Settings;
import com.lingtoo.common.utils.ApplicationContextHolder;
import org.apache.commons.lang3.StringUtils;

/**
 * Created: 2015/12/7.
 * Author: Qiannan Lu
 */
public class SystemConfig {
    public static String getImageServer() {
        Settings settings = ApplicationContextHolder.getApplicationContext().getBean(Settings.class);
        String imageServer = settings.get("system.image.server");
        return StringUtils.isEmpty(imageServer) ? "" : imageServer;
    }
    
    public static String getAbsolutePath() {
        Settings settings = ApplicationContextHolder.getApplicationContext().getBean(Settings.class);
        String absolutePath = settings.get("system.image.logo.absolutePath");
        return StringUtils.isEmpty(absolutePath) ? "" : absolutePath;
    }
    
    public static String getContextPath(){
        Settings settings = ApplicationContextHolder.getApplicationContext().getBean(Settings.class);
        String contextPath = settings.get("system.contextPath.path");
        return StringUtils.isEmpty(contextPath) ? "" : contextPath;
    }
}
