package com.lingtoo.wechat.message;

import com.lingtoo.wechat.components.WechatClient;

import java.util.Map;

/**
 * Created: 2015/11/28.
 * Author: Qiannan Lu
 */
public abstract class TemplateBuilder<T extends WechatTemplate> implements TemplateProcessor {
    private String toUser;
    private String url;

    public TemplateBuilder() {}

    /**
     *
     * @param toUser    openid of the target wechat user
     * @param url       detail page url
     */
    public TemplateBuilder(String toUser, String url) {
        this.toUser = toUser;
        this.url = url;
    }

    public WechatTemplate build(T template) {
        template.setToUser(buildToUser());
        template.setData(buildData());
        template.setTopColor(buildTopColor());
        template.setUrl(buildUrl());
        return template;
    }

    @Override
    public String buildUrl() {
        return url;
    }

    @Override
    public String buildToUser() {
        return toUser;
    }

    // 默认标题颜色  黑色 ：#000000
    @Override
    public String buildTopColor() {
        return "#000000";
    }
}
