package ph.dreambig.bigbenta.marketplacev2.Home.Store.Categories_Store;

/**
 * Created by jenzoned on 4/4/16.
 */
public class StoreData {

    String ImgUrl;
    String Name;
    int CatId;

    public StoreData(String imgUrl, String name, int catId ) {
        ImgUrl = imgUrl;
        Name = name;
        CatId = catId;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public String getName() {
        return Name;
    }

    public int getCatId() {
        return CatId;
    }
}
