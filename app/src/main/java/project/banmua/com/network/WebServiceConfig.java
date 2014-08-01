package project.banmua.com.network;

/**
 * Created by Zin9x on 8/2/2014.
 */
public final class WebServiceConfig {

    // Network time out: 60s
    public static int NETWORK_TIME_OUT = 30000;
    public static int NETWORK_LONG_THREADSHOT = 30000;
    public static int NETWORK_LIMIT_SHUTDOWN_CONNECTION = 60000;
    //==================== DOMAIN ==================

    public static String PROTOCOL_HTTP = "http://";

    public static String PROTOCOL_HTTPS = "https:";


    public static String APP_DOMAIN = PROTOCOL_HTTP + "ibanmua.vn/api/";

    //=================== NAME OF SERVICE==============
    public static String SERVICE_LOGIN = "login";

    public static String SERVICE_GET_CATEGORY_HOME = "Product/GetCategoriesByParentId";


    //=================== WEB SERVICE LINK ============

    public static String URL_LOGIN = APP_DOMAIN + SERVICE_LOGIN;

    public static String URL_GET_CATEGORY_HOME = APP_DOMAIN + SERVICE_GET_CATEGORY_HOME;

    //=================== PARAMETER =================

    public static String PARAM_USER = "user";

    public static String PARAM_PARENT_ID = "parentId";

    //=================== KEY ===================
    public static String KEY_ID = "Id";

    public static String KEY_CATEGORY_NAME = "CategoryName";

    public static String KEY_ICON = "Icon";


}
