package com.lingtoo.wechat.utils;

import com.lingtoo.wechat.T1TConstants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created: 2015/11/4.
 * Author: Qiannan Lu
 */
public class WechatUtil {
    
    protected static final String SCOPE_BASE = T1TConstants.SCOPE_BASE;

    protected static final String UTF8 = "utf-8";
	
    private static final String OAUTH2_URL = T1TConstants.WECHAT_OAUTH2_URL;

    private static final String SHARE_LINK_FORMAT = "?appid={0}&redirect_uri={1}&response_type=code&scope={2}&state={3}#wechat_redirect";

    public static String buildWechatShareLink(String appId, String redirectUri, String scope, String state) {
        return OAUTH2_URL + MessageFormat.format(SHARE_LINK_FORMAT, appId, redirectUri, scope, state);
    }
    
    public static String buildWechatUrl(String appId, String url) {
        try {
			return buildWechatShareLink(appId,
					URLEncoder.encode(url, UTF8),
					SCOPE_BASE, appId);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    public static void main(String[] args) {

        try {
            System.out.println(buildWechatShareLink(T1TConstants.APPID_DECORATION,
                    URLEncoder.encode("http://www.tcxiuyixiu.com/mobile/user/index", "utf-8"),
                    T1TConstants.SCOPE_BASE, T1TConstants.APPID_DECORATION));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
