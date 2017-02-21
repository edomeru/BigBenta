package ph.dreambig.bigbenta.marketplacev2.Home;

/**
 * Created by Edmer on 06/12/2016.
 */

public class BannerRequest {

  private String  app_key;
    private String is_primary;
    private String  is_mobile;

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public String getIs_primary() {
        return is_primary;
    }

    public void setIs_primary(String is_primary) {
        this.is_primary = is_primary;
    }

    public String getIs_mobile() {
        return is_mobile;
    }

    public void setIs_mobile(String is_mobile) {
        this.is_mobile = is_mobile;
    }
}
