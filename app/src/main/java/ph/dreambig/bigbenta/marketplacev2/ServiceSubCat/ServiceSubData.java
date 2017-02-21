package ph.dreambig.bigbenta.marketplacev2.ServiceSubCat;

/**
 * Created by jenzoned on 4/4/16.
 */
public class ServiceSubData {

    String ImgUrl;
    String Name;

    public ServiceSubData(String imgUrl, String name) {
        ImgUrl = imgUrl;
        Name = name;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public String getName() {
        return Name;
    }
}
