package ph.dreambig.bigbenta.marketplacev2.StoreItems;

/**
 * Created by jenzoned on 4/4/16.
 */
public class StoreItemsData {

    String ImgUrl;
    String Name;
    String Price;

    public StoreItemsData(String imgUrl, String name, String price) {
        ImgUrl = imgUrl;
        Name = name;
        Price = price;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public String getName() {
        return Name;
    }

    public String getPrice() {
        return Price;
    }
}
