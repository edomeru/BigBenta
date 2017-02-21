
package ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.MyFavorites_Ads;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class AdsFavData extends RealmObject {

    @PrimaryKey
    private int id;
    private String ImageUrl;
    private String ProductName;
    private String  Price;
    private String  created_date;
    private String  city_name;
    private String region_name;

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    private String itemId;

    @Override
    public String toString() {
        return "AdsFavData{" +
                "id=" + id +
                ", ImageUrl='" + ImageUrl + '\'' +
                ", ProductName='" + ProductName + '\'' +
                ", Price=" + Price +
                ", itemId='" + itemId + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }




    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}

