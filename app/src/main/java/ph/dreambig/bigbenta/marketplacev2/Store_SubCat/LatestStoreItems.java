package ph.dreambig.bigbenta.marketplacev2.Store_SubCat;

/**
 * Created by edmeralarte on 23/08/2016.
 */

public class LatestStoreItems {

    private int id;
    private int price;
    private int minimum_order;
    private int quantity;
    private String prod_name;
    private String store_name;
    private String image;
    private int cat_id;

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }
    //    private String IMAGE_MD;
//    private String IMAGE_SMD;
//    private String IMAGE_LG;
//    private String IMAGE_ORIG;

    public LatestStoreItems(String prod_name,int cat_id){
//        this.setid(id);
//        this.setprice(price);
        this.setprod_name(prod_name);
        this.setCat_id(cat_id);
//        this.setstore_name(store_name);
//        this.setimage(img);
//        this.setquantity(quantity);
//        this.setminimum_order(minimum_order);
//        this.setimage(IMAGE_MD);
//        this.setimage(IMAGE_SMD);
//        this.setimage(IMAGE_LG);
//        this.setimage(IMAGE_ORIG);

    }

    public int getquantity() {
        return quantity;
    }

    public void setquantity(int quantity) {
        this.quantity = quantity;
    }

    public int getminimum_order() {
        return minimum_order;
    }

    public void setminimum_order(int minimum_order) {
        this.minimum_order = minimum_order;
    }
    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public int getprice() {
        return price;
    }

    public void setprice(int price) {
        this.price = price;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image= image;
    }

    public String getprod_name() {
        return prod_name;
    }

    public void setprod_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getstore_name() {
        return store_name;
    }

    public void setstore_name(String store_name) {
        this.store_name = store_name;
    }


//    public String getIMAGE_MD() {
//        return IMAGE_MD;
//    }
//
//    public void setIMAGE_MD(String IMAGE_MD) {
//        this.IMAGE_MD= IMAGE_MD;
//    }
//
//    public String getIMAGE_SMD() {
//        return IMAGE_SMD;
//    }
//
//    public void setIMAGE_SMD(String IMAGE_SMD) {
//        this.IMAGE_SMD= IMAGE_SMD;
//    }
//
//    public String getIMAGE_LG() {
//        return IMAGE_LG;
//    }
//
//    public void setIMAGE_LG(String IMAGE_LG) {
//        this.IMAGE_LG= IMAGE_LG;
//    }
//
//    public String getIMAGE_ORIG() {
//        return IMAGE_ORIG;
//    }
//
//    public void setIMAGE_ORIG(String IMAGE_ORIG) {
//        this.IMAGE_ORIG= IMAGE_ORIG;
//    }

    @Override
    public String toString() {
        return "LatestStoreItems{" +
//                "price=" + price +
                ", prod_name='" + prod_name + '\'' +
//                ", store_name='" + store_name + '\'' +
//                ", store_name='" + image + '\'' +
                '}';
    }
}