package com.lingtoo.common.setting;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.net.URL;

public class ImmutableSettingsTest {

    @Test
    public void testLoadConfigAndGetOperation(){
        ImmutableSettings.DefaultSettingsBuilder builder = new ImmutableSettings.DefaultSettingsBuilder();
        builder.loadFromUrl(loadConfigURL("classpath*:config/test.yml"));
        Settings settings = builder.build();
        Assert.assertNotNull(settings);
        Assert.assertEquals("testValue", settings.get("module01.testStringKey"));
        Settings subSettings = settings.getByPrefix("module01.");
        Assert.assertNotNull(subSettings);
        Assert.assertEquals("testValue", subSettings.get("testStringKey"));
        Assert.assertFalse(subSettings.getAsBoolean("testBooleanKey"));
        Assert.assertTrue(10 == subSettings.getAsInt("testIntKey"));
        Assert.assertTrue(subSettings.getAsFloat("testFloatKey") == 3.45f);
        Assert.assertTrue(subSettings.getAsDouble("testDoubleKey") == 4.566);
        Assert.assertTrue(subSettings.getAsLong("testLongKey") == 3234242L);
    }

    @Test
    public void testGetDefaultValue() {
        ImmutableSettings.DefaultSettingsBuilder builder = new ImmutableSettings.DefaultSettingsBuilder();
        builder.loadFromUrl(loadConfigURL("classpath*:config/test.yml"));
        Settings settings = builder.build();
        Assert.assertNotNull(settings);
        Assert.assertEquals("testStringDefaultValue", settings.get("nonDefaultedModule.testStringKey", "testStringDefaultValue"));
        Assert.assertTrue(settings.getAsInt("nonDefaultedModule.testIntKey", 1) == 1);
        Assert.assertTrue(settings.getAsLong("nonDefaultedModule.testLongKey", 1L) == 1L);
        Assert.assertTrue(settings.getAsDouble("nonDefaultedModule.testDoubleKey", 4.5222) == 4.5222);
        Assert.assertTrue(settings.getAsFloat("nonDefaultedModule.testFloatKey", 3.45f) == 3.45f);
    }

    @Test(expected = SettingsException.class)
    public void testExceptionWhenGet() {
        ImmutableSettings.DefaultSettingsBuilder builder = new ImmutableSettings.DefaultSettingsBuilder();
        builder.loadFromUrl(loadConfigURL("classpath*:config/test.yml"));
        Settings settings = builder.build();
        Assert.assertNotNull(settings);
        settings.getAsInt("module01.testStringKey");
    }

    private URL loadConfigURL(String file) {
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        try {
            Resource[] resources = patternResolver.getResources(file);
            return resources[0].getURL();
        } catch (IOException e) {
            //just ignored.
        }
        return null;
    }


}
