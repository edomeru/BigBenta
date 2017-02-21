package ph.dreambig.bigbenta.marketplacev2;

import android.content.Context;
import android.content.SharedPreferences;

//import ph.jumpdigital.httpfresh.freshonline.FirstPage.FirstPage;


public class SessionManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;
    Context context;

    private static final String PREF_NAME ="Login";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "name";
    public static final String KEY_CATID = "catid";
    public static final String KEY_TOKEN= "token";
    public static final String KEY_SUBCATID = "subcatid";
    public static final String KEY_ADSITEMSUBCATID = "adsitemsubcatid";
    public static final String KEY_USER_ID = "user_id";

    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(String access_token, String user_id){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_TOKEN, access_token);
        editor.putString(KEY_USER_ID, user_id);
        editor.commit();
    }

    public void getcatId(String catId){
        editor.putString(KEY_CATID, catId);
        editor.commit();
    }
    public String getToken(){

        return sharedPreferences.getString(KEY_TOKEN,"token");
    }
    public void setSubcatId(String catId){
        editor.putString(KEY_SUBCATID, catId);
        editor.commit();
    }
    public void setAdsitemcatId(String catId){
        editor.putString(KEY_ADSITEMSUBCATID, catId);
        editor.commit();
    }
    public String getsubcatid(){

        return sharedPreferences.getString(KEY_SUBCATID,"subcatId");
    }
    public String getUser_id(){

        return sharedPreferences.getString(KEY_USER_ID,"user_id");
    }
    public String getAdsitemsubcatid(){

        return sharedPreferences.getString(KEY_ADSITEMSUBCATID,"adsitemsubcatid");
    }
    public String getcatid(){

        return sharedPreferences.getString(KEY_CATID,"catId");
    }
    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void logoutUser(){

        editor.clear();
        editor.commit();

    }

}
