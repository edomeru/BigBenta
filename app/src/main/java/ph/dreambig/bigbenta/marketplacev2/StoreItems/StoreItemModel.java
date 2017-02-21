package ph.dreambig.bigbenta.marketplacev2.StoreItems;

/**
 * Created by edmeralarte on 23/08/2016.
 */

public class StoreItemModel {


    private String price;
    private String classif_name;



    private String storename;
    private String image;

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    private String item_id;
    private String classif_date;


    public StoreItemModel(String price, String classif_name, String storename, String img,String item_id){
        this.setprice(price);
        this.setclassif_name(classif_name);
        this.setStorename(storename);
        this.setimage(img);
        this.setItem_id(item_id);

    }
    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
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

                ", image='" + image + '\'' +
                ", classif_date='" + classif_date + '\'' +
                '}';
    }
}