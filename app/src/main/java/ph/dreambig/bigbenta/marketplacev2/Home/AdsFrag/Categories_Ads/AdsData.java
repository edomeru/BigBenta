package ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.Categories_Ads;

/**
 * Created by jenzoned on 4/4/16.
 */
public class AdsData {

    String ImgUrl;
    String Name;
    int CatId;

    public AdsData(String imgUrl, String name,int catId) {
        ImgUrl = imgUrl;
        Name = name;
         CatId =  catId;
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
