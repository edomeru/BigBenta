package ph.dreambig.bigbenta.marketplacev2.Home.Service.Categories_Service;


public class ServiceData {

    String ImgUrl;
    String Name;
    String CategoryId;

    public ServiceData(String imgUrl, String name, String categoryId) {
        ImgUrl = imgUrl;
        Name = name;
        CategoryId = categoryId;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public String getName() {
        return Name;
    }

    public String getCategoryId() {
        return CategoryId;
    }
}
