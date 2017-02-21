package ph.dreambig.bigbenta.marketplacev2.AdsItemsFrag;

/**
 * Created by edmeralarte on 23/08/2016.
 */

public class AdsItemModel {


    private String price;
    private String classif_name;
    private String classif_place;
    private String image;
    private String classif_date;
    private String itemid;

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public AdsItemModel(String price, String classif_name, String classif_place, String img, String classif_date,String iteeemid){
        this.setprice(price);
        this.setclassif_name(classif_name);
        this.setclassif_place(classif_place);
        this.setimage(img);
        this.setclassif_date(classif_date);
        this.setItemid(iteeemid);
    }

    public String getprice() {
        return price;
    }

    public void setprice(String price) {
        this.price = price;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image= image;
    }

    public String getclassif_name() {
        return classif_name;
    }

    public void setclassif_name(String classif_name) {
        this.classif_name = classif_name;
    }

    public String getclassif_place() {
        return classif_place;
    }

    public void setclassif_place(String classif_place) {
        this.classif_place = classif_place;
    }

    public String getclassif_date() {
        return classif_date;
    }

    public void setclassif_date(String classif_date) {
        this.classif_date = classif_date;
    }


    @Override
    public String toString() {
        return "LatestStoreItems{" +
                "price=" + price +
                ", classif_name='" + classif_name + '\'' +
                ", classif_place='" + classif_place + '\'' +
                ", image='" + image + '\'' +
                ", classif_date='" + classif_date + '\'' +
                '}';
    }
}