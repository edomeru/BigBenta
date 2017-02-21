package ph.dreambig.bigbenta.marketplacev2.Store_SubCat;

/**
 * Created by Edmer on 08/11/2016.
 */

public class StoreSubCatRequest {

    private  String app_key;
    private  String category_id;
    private  String is_mobile;

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getIs_mobile() {
        return is_mobile;
    }

    public void setIs_mobile(String is_mobile) {
        this.is_mobile = is_mobile;
    }
}
