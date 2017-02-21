package ph.dreambig.bigbenta.marketplacev2.AdsItemsFrag;

/**
 * Created by Edmer on 16/11/2016.
 */

public class AdsItemRequest {
private String app_key;
private String category_id;
    private String is_mobile;
    private String category;
    private String is_primary;
    private String limit;
    private String offset;
    private String sort_by;
    private String order;
    private String subcategory;

    @Override
    public String toString() {
        return "AdsItemRequest{" +
                "app_key='" + app_key + '\'' +
                ", category_id='" + category_id + '\'' +
                ", is_mobile='" + is_mobile + '\'' +
                ", category='" + category + '\'' +
                ", is_primary='" + is_primary + '\'' +
                ", limit='" + limit + '\'' +
                ", offset='" + offset + '\'' +
                ", sort_by='" + sort_by + '\'' +
                ", order='" + order + '\'' +
                ", subcategory='" + subcategory + '\'' +
                '}';
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }
}
