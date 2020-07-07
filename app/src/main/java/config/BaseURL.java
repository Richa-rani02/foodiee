package config;

public class BaseURL {
 static final String APP_NAME="Foodiee";
    public static final String PREFS_NAME = "TifinLoginPrefs";

    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_ID="user_id";
    public static final String KEY_USER="user_name";
    public static final String KEY_EMAIL="user_email";
    public static final String KEY_MOBILE="user_mobile";
    public static final String KEY_IMAGE="user_img";
    public static final String KEY_DATE="date";
    public static final String KEY_TIME="time";
    public static final String PACKAGE_ID="pack_id";
    public static final String ADDON_ID="extra_id";

    /////////////////// URL'S///////////////////
    public static String BASE_URL = "http://localhost/FoodAppOwm/Api";
   public static String slider_url = "http://192.168.137.1/FoodAppOwn/Api/slider/slider";
   public  static  String package_url = "http://192.168.137.1/FoodAppOwn/Api/products/package";
   public  static  String addon_url = "http://192.168.137.1/FoodAppOwn/Api/products/addon";
    public  static  String SIGNUP = "http://192.168.137.1/FoodAppOwn/Api/user/signup";
    public  static  String LOGIN = "http://192.168.137.1/FoodAppOwn/Api/user/login";
    public  static  String REGISTER_FCM = "http://192.168.137.1/FoodAppOwn/Api/user/register_fcm";
}
