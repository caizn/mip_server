package com.lingtoo.common.setting;

import org.junit.Assert;
import org.junit.Test;

public class SettingsFileConfigTest {

    @Test
    public void testDefaultConfigPath() {
        SettingsFileConfig settingsFileConfig = new SettingsFileConfig();
        Assert.assertEquals("classpath*:config/", settingsFileConfig.getConfigPath());
        settingsFileConfig.setConfigPath("/data/lingtoo/config/");
        Assert.assertEquals("/data/lingtoo/config/", settingsFileConfig.getConfigPath());
    }
}
