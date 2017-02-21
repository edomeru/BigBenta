package ph.dreambig.bigbenta.marketplacev2.StoreItemDetail;

/**
 * Created by edmeralarte on 23/08/2016.
 */

public class StoreItemDetailModel {


    private String price;
    private String classif_name;
    private String long_description;

    public String getLong_description() {
        return long_description;
    }

    public void setLong_description(String long_description) {
        this.long_description = long_description;
    }

    private String storename;


    private String imagef;

    public String getImagef() {
        return imagef;
    }

    public void setImagef(String imagef) {
        this.imagef = imagef;
    }

    public static String item_id;

    public static String getItem_id() {
        return item_id;
    }

    public static void setItem_id(String item_id) {
        StoreItemDetailModel.item_id = item_id;
    }

    private String classif_date;


    public StoreItemDetailModel(String price, String classif_name, String storename, String img, String item_id,String long_description){
        this.setprice(price);
        this.setclassif_name(classif_name);
        this.setStorename(storename);
        this.setImagef(img);
        this.setItem_id(item_id);
        this.setLong_description(long_description);

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

               // ", image='" + image + '\'' +
                ", classif_date='" + classif_date + '\'' +
                '}';
    }
}