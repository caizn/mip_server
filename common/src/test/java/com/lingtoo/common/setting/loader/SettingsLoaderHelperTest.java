package com.lingtoo.common.setting.loader;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsLoaderHelperTest {

    @Test
    public void testLoadConfigFromMap() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");

        Map<String, String> setting = SettingsLoader.Helper.loadNestedFromMap(map);
        Assert.assertNotNull(setting);
        Assert.assertEquals("value", setting.get("key"));

        Map<String, List> map2 = new HashMap<>();
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        list.add(map);
        map2.put("nestList", list);

        setting = SettingsLoader.Helper.loadNestedFromMap(map2);
        Assert.assertNotNull(setting);
        Assert.assertEquals("value", setting.get("nestList.0.key"));

        List<List<Map<String, String>>> arrayList = new ArrayList<>();
        arrayList.add(list);
        map2.put("nestList", arrayList);

        setting = SettingsLoader.Helper.loadNestedFromMap(map2);
        Assert.assertNotNull(setting);
        Assert.assertEquals("value", setting.get("nestList.0.0.key"));
    }
}
