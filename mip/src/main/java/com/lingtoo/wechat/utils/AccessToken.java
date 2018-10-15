package com.lingtoo.wechat.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.lingtoo.wechat.T1TConstants;

public class AccessToken {

//    protected Logger logger = LoggerFactory.getLogger(getClass());
    public static final int API_TOKEN_LENGTH = 24;

    public static final int TOKEN_EXPIRE = 1; //token过期
    public static final int TOKEN_CORRECT = 0; //token正确
    public static final int TOKEN_ERROR = 2; //token错误

    public static final int TOKEN_DURATION = 7 * 24 * 60 * 60 * 1000; //token过期间隔，单位毫秒
//    public static final int TOKEN_DURATION = 5 * 60 * 1000; //token过期间隔，用于测试


    public static String getVersionFromToken(String token) {
        try {
            token = new String(Base64.decode(token), "utf-8");
            if (token.indexOf("&version=") != -1) {
                String version = token.substring(token.indexOf("&version=") + "&version=".length());
                return version;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean validToken(String token) {
        try {
            token = new String(Base64.decode(token), "utf-8");
            String time = token.substring("time=".length(), token.indexOf("&num="));
            String num = token.substring(token.indexOf("&num=") + "&num=".length(), token.indexOf("&accessToken="));
            String accessToken;
            if (token.indexOf("&version=") != -1) {
                accessToken = token.substring(token.indexOf("&accessToken=") + "&accessToken=".length(), token.indexOf("&version="));
            } else {
                accessToken = token.substring(token.indexOf("&accessToken=") + "&accessToken=".length());
            }
            String encrypted = Des.encryptDES(time + "_" + num, T1TConstants.RESTFUL_SERVICE_KEY);
            if (accessToken.equals(encrypted)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static int validTokenExpire(String token,Integer token_duration) {
        try {
            token = new String(Base64.decode(token), "utf-8");
            String time = token.substring("time=".length(), token.indexOf("&num="));
            long tokenTime = Long.parseLong(time);
            long currentTime = new Date().getTime();
            if(currentTime - tokenTime > token_duration){
                return TOKEN_EXPIRE;
            }
            String num = token.substring(token.indexOf("&num=") + "&num=".length(), token.indexOf("&accessToken="));
            String accessToken;
            if (token.indexOf("&version=") != -1) {
                accessToken = token.substring(token.indexOf("&accessToken=") + "&accessToken=".length(), token.indexOf("&version="));
            } else {
                accessToken = token.substring(token.indexOf("&accessToken=") + "&accessToken=".length());
            }
            String encrypted = Des.encryptDES(time + "_" + num, T1TConstants.RESTFUL_SERVICE_KEY);
            if (accessToken.equals(encrypted)) {
                return TOKEN_CORRECT;
            }
            return TOKEN_ERROR;
        } catch (Exception e) {
            return TOKEN_ERROR;
        }
    }
    
    public static int validTokenExpire(String token) {
        try {
            token = new String(Base64.decode(token), "utf-8");
            String time = token.substring("time=".length(), token.indexOf("&num="));
            long tokenTime = Long.parseLong(time);
            long currentTime = new Date().getTime();
            if(currentTime - tokenTime > TOKEN_DURATION){
                return TOKEN_EXPIRE;
            }
            String num = token.substring(token.indexOf("&num=") + "&num=".length(), token.indexOf("&accessToken="));
            String accessToken;
            if (token.indexOf("&version=") != -1) {
                accessToken = token.substring(token.indexOf("&accessToken=") + "&accessToken=".length(), token.indexOf("&version="));
            } else {
                accessToken = token.substring(token.indexOf("&accessToken=") + "&accessToken=".length());
            }
            String encrypted = Des.encryptDES(time + "_" + num, T1TConstants.RESTFUL_SERVICE_KEY);
            if (accessToken.equals(encrypted)) {
                return TOKEN_CORRECT;
            }
            return TOKEN_ERROR;
        } catch (Exception e) {
            return TOKEN_ERROR;
        }
    }

    //dGltZT0xMzkyNzc2NjY1MzU5Jm51bT1UMEg2MyZhY2Nlc3NUb2tlbj1sdGdXTC9QalB3WUZwWG1ka2FXVDAvN3p3RG5jS1VUcyZ2ZXJzaW9uPTAuMC4x
    public static String getToken() {
        try {
            String time = String.valueOf(new Date().getTime());
            String num = StringUtil.getRandCode(5);
            String encrypted = Des.encryptDES(time + "_" + num, T1TConstants.RESTFUL_SERVICE_KEY);
            String token = "time=" + time + "&num=" + num + "&accessToken=" + encrypted + "&version=0.0.1";
            return Base64.encodeBytes(token.getBytes("utf-8"));
        } catch (Exception e) {
//            logger.error(e, e);
            return null;
        }
    }

    public static String getToken(String key) {
        try {
            String time = String.valueOf(new Date().getTime());
            String num = StringUtil.getRandCode(5);
            String encrypted = Des.encryptDES(time + "_" + num, key);
            String token = "time=" + time + "&num=" + num + "&accessToken=" + encrypted + "&version=0.0.1";
            return Base64.encodeBytes(token.getBytes("utf-8"));
        } catch (Exception e) {
//            logger.error(e, e);
            return null;
        }
    }

    /*public static String getDataToken() {
        return getDataToken("127.0.0.1");
    }*/
    /*public static String getDataToken(String ip) {
        try {
            ip = getIpToken(ip);
            String encrypted = Des.encryptDES("t1=" + ip, T1TConstants.API_SERVICE_KEY);
            String token = encrypted;
            return Base64.encodeBytes(token.getBytes("utf-8"));
        } catch (Exception e) {
//            logger.error(e, e);
            return null;
        }
    }*/

    public static boolean validDataToken(String token, Map<String, String> map) {
        try {

            String decString = new String(Base64.decode(token), "utf-8");

            String params = Des.decryptDES(decString, T1TConstants.API_SERVICE_KEY);

            Map<String, String> m = new HashMap<String, String>();
            String[] ps = params.split("&");
            for (String p : ps) {

                String[] namevalue = p.split("=");
                if (namevalue.length > 1) {
                    m.put(namevalue[0], namevalue[1]);
                }
            }
            for (String key : map.keySet()) {
                String v = m.get(key);
                if (!m.containsKey(key) || !map.get(key).equals(v)) {
                    return false;
                }
            }
            return true;
        } catch (Exception ex) {
//            logger.error(ex, ex);
            return false;
        }
    }

   /* public static String getIpToken(String ip) throws Exception {
        String tk = FileSignatureUtil.MD5(ip.getBytes("utf-8"));
        tk = RegexUtil.replace(tk, "(.)(.)(.)(.)(.)(.)(.)(.)", new Replacement() {
            @Override
            public String format(Matcher m) {
                return m.group(1) + m.group(7) + m.group(2) + m.group(5) + m.group(3);
            }
        });
        return tk;
    }*/

    public static void main(String[] args) throws UnsupportedEncodingException, IOException, Exception {
        /*T1TConstants.RESTFUL_SERVICE_KEY = "27AB5038";
        String token = getDataToken();
        System.out.println(token);
        String decString = new String(Base64.decode(token), "utf-8");
        String params = Des.decryptDES(decString, T1TConstants.API_SERVICE_KEY);
        System.out.println(params);
        String ip = "127.0.0.1";

        System.out.println(AccessToken.getIpToken(ip));
        
        System.out.println(FileSignatureUtil.MD5("1483787707236201412031442".getBytes("GBK")));*/

//        System.out.println(AccessToken.getToken("27AB5038"));

        String time = String.valueOf(new Date().getTime());
        System.out.println("------time="+time);
        System.out.println("------TOKEN_DURATION="+TOKEN_DURATION);
    }

}
