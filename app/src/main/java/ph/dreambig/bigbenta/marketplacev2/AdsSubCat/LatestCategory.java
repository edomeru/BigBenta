package ph.dreambig.bigbenta.marketplacev2.AdsSubCat;

/**
 * Created by edmeralarte on 23/08/2016.
 */

public class LatestCategory {


    private String price;
    private String classif_name;
    private String classif_place;
    private String image;



    private int category_id;







    public LatestCategory(String classif_name,int category_id){
//        this.setprice(price);
        this.setclassif_name(classif_name);
        this.setCategory_id(category_id);
//        this.setclassif_place(classif_place);
//        this.setimage(img);
//        this.setclassif_date(classif_date);
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
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




    @Override
    public String toString() {
        return "LatestStoreItems{" +
               // "price=" + price +
                ", classif_name='" + classif_name + '\'' +
               // ", classif_place='" + classif_place + '\'' +
              // ", image='" + image + '\'' +
              //  ", classif_date='" + classif_date + '\'' +
                '}';
    }
}