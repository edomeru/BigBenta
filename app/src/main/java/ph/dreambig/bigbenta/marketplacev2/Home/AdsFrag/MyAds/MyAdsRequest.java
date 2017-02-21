package ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.MyAds;

/**
 * Created by Edmer on 01/12/2016.
 */

public class MyAdsRequest {
    private String app_key;
    private String category;
    private String is_mobile;
    private String is_primary;
    private String limit;
    private String offset;
    private String sort_by;
    private String order;
    private String user_id;
    private String  token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSingle_edit_items() {
        return single_edit_items;
    }

    public void setSingle_edit_items(String single_edit_items) {
        this.single_edit_items = single_edit_items;
    }

    public String getGet_all_status() {
        return get_all_status;
    }

    public void setGet_all_status(String get_all_status) {
        this.get_all_status = get_all_status;
    }

    private String  type;
    private String  single_edit_items;
    private String  get_all_status;


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

    public String getIs_primary() {
        return is_primary;
    }

    public void setIs_primary(String is_primary) {
        this.is_primary = is_primary;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getSort_by() {
        return sort_by;
    }

    public void setSort_by(String sort_by) {
        this.sort_by = sort_by;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }



}
