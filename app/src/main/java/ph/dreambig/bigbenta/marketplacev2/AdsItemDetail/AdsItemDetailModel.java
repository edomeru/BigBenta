package ph.dreambig.bigbenta.marketplacev2.AdsItemDetail;

/**
 * Created by Edmer on 27/10/2016.
 */

public class AdsItemDetailModel {

    private String prod_id;
    private String prodName;
    private String description;
    private String price;
    private String created_date;

    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }

    @Override
    public String toString() {
        return "AdsItemDetailModel{" +
                "prod_id='" + prod_id + '\'' +
                ", prodName='" + prodName + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", created_date='" + created_date + '\'' +
                ", category_name='" + category_name + '\'' +
                ", city_name='" + city_name + '\'' +
                ", region_name='" + region_name + '\'' +
                ", image_md='" + image_md + '\'' +
                '}';
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getImage_md() {
        return image_md;
    }

    public void setImage_md(String image_md) {
        this.image_md = image_md;
    }

    private String category_name;
    private String city_name;
    private String region_name;
    private String image_md;

    public AdsItemDetailModel(String prod_id, String prodName, String description, String price, String created_date, String category_name, String city_name, String region_name, String image_md) {
        this.prod_id = prod_id;
        this.prodName = prodName;
        this.description = description;
        this.price = price;
        this.created_date = created_date;
        this.category_name = category_name;
        this.city_name = city_name;
        this.region_name = region_name;
        this.image_md = image_md;
    }


}
