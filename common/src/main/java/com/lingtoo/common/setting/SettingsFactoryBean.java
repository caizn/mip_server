package com.lingtoo.common.setting;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsFactoryBean extends AbstractFactoryBean<Settings> {

    private SettingsFileConfig settingsFileConfig = new SettingsFileConfig();

    @Override
    protected Settings createInstance() {
        ImmutableSettings.DefaultSettingsBuilder builder = new ImmutableSettings.DefaultSettingsBuilder();
        List<File> resourceList = new ArrayList<>();
        try {
            Resource[] resources = resolveConfig();
            for (Resource resource : resources) {
                File file = new File(resource.getURI());
                if (file.isDirectory()) {
                    resourceList.addAll(Arrays.asList(file.listFiles()));
                } else {
                    resourceList.add(file);
                }
            }

            for (File file : resourceList) {
                builder.loadFromUrl(file.toURI().toURL());
            }
        } catch (IOException e) {
            throw new FailedToResolveConfigException("Failed to resolve config path ["+ settingsFileConfig.getConfigPath()+"]", e);
        }


        return builder.build();
    }

    @Override
    public Class<?> getObjectType() {
        return Settings.class;
    }

    private Resource[] resolveConfig() throws IOException {
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        return patternResolver.getResources(settingsFileConfig.getConfigPath());
    }

    public void setConfigFilePath(String configFilePath) {
        settingsFileConfig.setConfigPath(configFilePath);
    }
}
