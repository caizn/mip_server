package com.lingtoo.wechat;

/**
 * Created: 2015/10/13.
 * Author: Qiannan Lu
 */
public class T1TConstants {
    public static final String T1T_PLATFORM = "T1T";

    public static final String T1T_APP_ID_CONFIG_PATH = "t1t.wechat.appId";
    public static final String T1T_APP_SECRET_CONFIG_PATH = "t1t.wechat.appSecret";

    public static final String SUCCESS = "0";
    public static final String FAIL = "1";
    public static final String EXPIRE = "2";

    public static final int APP_SUCCESS = 0;
    public static final int APP_FAIL = 1;
    
    public static final Integer NUM_PER_PAGE = 5;

    public static final Integer GAME_STATE_PUBLISH = 0;
    public static final Integer GAME_STATE_START = 1;
    public static final Integer GAME_STATE_FINISH = 2;

    public static final String SESSION_USER = "session_user";
    public static final String SESSION_BACKEND = "session_backend";
    public static final String SESSION_BACKEND_MERCHANT = "session_backend_merchant";
    public static final String SESSION_CODE = "session_code"; 
    public static final String SESSION_MY_MENU="session_my_menu";
    public static final String SESSION_MY_MENU_MAP="session_my_menu_map";
    public static final String SESSION_SYSTEM = "session_system";
    public static final String SESSION_BACKEND_APPID = "session_backend_appid";
    public static final String SESSION_BACKEND_MERCHANT_ID = "session_backend_merchant_id";
    public static final String SESSION_APP = "from_app";
    
    public static final String SESSION_APP_MENU = "session_app_menu";//微信APP菜单

    public static final String TITI_TOKEN = "token";
    
    public static final Integer MATCH_STATUS_INITIATE = 0;
    public static final Integer MATCH_STATUS_ACCEPT = 1;
    public static final Integer MATCH_STATUS_START = 2;
    public static final Integer MATCH_STATUS_FINISH = 3;
    public static final Integer MATCH_STATUS_DELETE = 4;

    public static final Integer APPLY_STATUS_AGREE = 1;
    public static final Integer APPLY_STATUS_REJECT = 2;

    public static final String WECHAT_OPEN_ID = "openid";
    public static final String WECHAT_CODE = "code";
    public static final String WECHAT_STATE = "state";

    public static final String STATE_USER_BASE = "user_base";
    public static final String STATE_USER_INFO = "user_info";
    public static final String STATE_THIRD_ACCOUNT = "third_account";//第三方公众号
    public static final String SESSION_APPID = "session_appid";//会话appid
    public static final String SESSION_MERCHANT = "session_merchant";//会话appid
    public static final String SESSION_MERCHANT_ID = "session_merchant_id";//会话appid
    public static final String SESSION_USER_INFO="session_user_info";
    public static final String SESSION_DECORATION_ORDER="session_decoration_order";
    public static final String COOKIE_USER_RAND="user_rand";

//    public static final String SCOPE_BASE = "snsapi_base";
    public static final String SCOPE_BASE = "snsapi_userinfo";
    public static final String SCOPE_USER_INFO = "snsapi_userinfo";

    public static final String WECHAT_API = "https://api.weixin.qq.com/";
    public static final String WECHAT_OAUTH2_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";

    public static final String WECHAT_OAUTH2_URL_TEMPLATE = "?appid=wx4182199cbaea9d2c&redirect_uri=http%3A%2F%2Ft1t.lingtoo.com%2Fgame%2Fnearby&response_type=code&scope=snsapi_userinfo&state=user_info#wechat_redirect";
    public static final String MENU_URL_PUBLISH_GAME = WECHAT_OAUTH2_URL + "?appid=wx4182199cbaea9d2c&redirect_uri=http%3A%2F%2Ft1t.lingtoo.com%2Fgame%2Fpublish&response_type=code&scope=snsapi_userinfo&state=user_info#wechat_redirect";
    public static final String MENU_URL_NEARBY_GAMES = WECHAT_OAUTH2_URL + "?appid=wx4182199cbaea9d2c&redirect_uri=http%3A%2F%2Ft1t.lingtoo.com%2Fgame%2Fnearby&response_type=code&scope=snsapi_userinfo&state=user_info#wechat_redirect";
    public static final String MENU_URL_MY_DATA = WECHAT_OAUTH2_URL + "?appid=wx4182199cbaea9d2c&redirect_uri=http%3A%2F%2Ft1t.lingtoo.com%2Fuser%2Fdata&response_type=code&scope=snsapi_userinfo&state=user_info#wechat_redirect";
    public static final String MENU_URL_MY_TEAM = WECHAT_OAUTH2_URL + "?appid=wx4182199cbaea9d2c&redirect_uri=http%3A%2F%2Ft1t.lingtoo.com%2Fteam%2Fmy_team&response_type=code&scope=snsapi_userinfo&state=user_info#wechat_redirect";
    public static final String MENU_URL_COST = WECHAT_OAUTH2_URL + "?appid=wx4182199cbaea9d2c&redirect_uri=http%3A%2F%2Ft1t.lingtoo.com%2Fbill%2Fteam&response_type=code&scope=snsapi_userinfo&state=user_info#wechat_redirect";

    public static final String BAIDU_GEO_CODING_API = "http://api.map.baidu.com/geocoder/v2/";

    public static String RESTFUL_SERVICE_KEY="27AB5038";
    public static final String API_SERVICE_KEY="Go4AuiR_";

    public static final Integer MESSAGE_TYPE_GAME = 1;
    public static final Integer MESSAGE_TYPE_MATCH = 2;

    public static final String APPID_TITI = "wx4182199cbaea9d2c"; // 踢踢公众号
    public static final String APPID_DECORATION = "wxa467b05e3bc5bd09"; // 修一修公众号
    public static final String APPID_HUNAN = "wx27b1b5f645342135"; // 湖南商家公众号
    public static final String APPID_LONGYAN = "wxbcb6c1c45bec3032"; // 龙岩商家公众号
    public static final String APPID_CUJUBANG = "wx4afdbb15eda100db"; // 促鞠邦公众号
    public static final String APPSECRET_HUNAN = "d67b935b40b508ab379a303fe7b9032a"; // 湖南商家公众号密匙
    public static final String APPSECRET_LONGYAN = "48a6cbff3fb36a053530fe1e1b22d4d5"; // 龙岩商家公众号密匙
    public static final String APPSECRET_CUJUBANG = "b4417a218ff3b9c00fab95665545ee29"; // 促鞠邦公众号密匙
    public static final String APPSECRET_DECORATION = "9da4b30d206bb53386b287c778b7ea53"; // 促鞠邦公众号密匙

	/*public static final String LETV_USERID="817462";
	public static final String LETV_UUID="7r38mticgm";
	public static final String LETV_SECRET="db566a75a502f15fd969a8d1dbeccecd";*/
    public static final String LETV_USERID="833465";
	public static final String LETV_UUID="gbamwssz0k";
	public static final String LETV_SECRET="975d08a80dd0229f22116d20d8e46e16";
	

    public static final String SESSION_MANAGER = "session_manager";
    public static final String SESSION_MANAGER_ID = "session_manager_id";//会话appid

    public static final int CLASS_ONE = 1; //评分等级1级
    public static final int CLASS_TWO = 2;
    public static final int CLASS_THREE = 3;
    public static final int CLASS_FOUR = 4;
    public static final int CLASS_FIVE = 5;
}
