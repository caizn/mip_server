package com.lingtoo.wechat.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created: 2015/11/1.
 * Author: Qiannan Lu
 */
public class WechatTemplate {
    public WechatTemplate(String templateId) {
        this.templateId = templateId;
    }

    @JsonProperty("template_id")
    private String templateId;

    @JsonProperty("touser")
    private String toUser;

    @JsonProperty("url")
    private String url;

    @JsonProperty("topcolor")
    private String topColor;

    @JsonProperty("data")
    private Map<String, TemplateData> data;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopColor() {
        return topColor;
    }

    public void setTopColor(String topColor) {
        this.topColor = topColor;
    }

    public Map<String, TemplateData> getData() {
        return data;
    }

    public void setData(Map<String, TemplateData> data) {
        this.data = data;
    }
}
