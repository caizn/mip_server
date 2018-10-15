package com.lingtoo.wechat.message;

/**
 * Created: 2015/11/1.
 * Author: Qiannan Lu
 */
public class TemplateData {
    public TemplateData(String color, String value) {
        this.value = value;
        this.color = color;
    }

    public TemplateData() {}

    private String value;
    private String color;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
