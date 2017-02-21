package ph.dreambig.bigbenta.marketplacev2.AdsItemsFrag;

/**
 * Created by jenzoned on 4/4/16.
 */
public class AdsItemsData {

    String ImgUrl;
    String Name;
    String Price;

    public AdsItemsData(String imgUrl, String name, String price) {
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
