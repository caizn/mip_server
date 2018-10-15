package com.lingtoo.wechat.message;

import java.util.Map;

/**
 * Created: 2015/11/28.
 * Author: Qiannan Lu
 */
public interface TemplateProcessor {
    String buildToUser();
    String buildTopColor();
    String buildUrl();
    Map<String, TemplateData> buildData();
}
