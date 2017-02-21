package ph.dreambig.bigbenta.marketplacev2.Profile;

/**
 * Created by Edmer on 21/11/2016.
 */

public class ProfileRequest {
    private String app_key;
    private String user_id;
    private String is_mobile;
    private String token;

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getIs_mobile() {
        return is_mobile;
    }

    public void setIs_mobile(String is_mobile) {
        this.is_mobile = is_mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
