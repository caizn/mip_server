package com.lingtoo.common.setting.loader;

import org.junit.Assert;
import org.junit.Test;

public class SettingsLoaderFactoryTest {

    @Test
    public void testCreateDefaultSettingsLoaderClass() {
        SettingsLoader settingsLoader = SettingsLoaderFactory.createSettingsLoader();
        Assert.assertNotNull(settingsLoader);
        Assert.assertTrue(settingsLoader instanceof YamlSettingsLoader);
    }
}
