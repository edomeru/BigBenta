package ph.dreambig.bigbenta.marketplacev2.ServiceItems2;

/**
 * Created by jenzoned on 4/4/16.
 */
public class ServiceItemsData2 {

    String ImgUrl;
    String Name;
    String Price;

    public ServiceItemsData2(String imgUrl, String name, String price) {
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
