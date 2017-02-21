package ph.dreambig.bigbenta.marketplacev2.StoreItemDetail;

/**
 * Created by Edmer on 13/11/2016.
 */

public class StoreDetailRequest {
    private String app_key;
    private String category;
private  String is_mobile;
   private String  subcategory;

    @Override
    public String toString() {
        return "StoreDetailRequest{" +
                "app_key='" + app_key + '\'' +
                ", category='" + category + '\'' +
                ", is_mobile='" + is_mobile + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", item_id='" + item_id + '\'' +
                '}';
    }

    private String item_id;

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIs_mobile() {
        return is_mobile;
    }

    public void setIs_mobile(String is_mobile) {
        this.is_mobile = is_mobile;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }
}
