package ph.dreambig.bigbenta.marketplacev2.ServiceItems2;

/**
 * Created by Edmer on 26/10/2016.
 */

public class ServiceItem2Model {
    private  String ImgUrl;
    private String BusinessName;
    private String Price;
    private String job;
    private String category;
    private String srvc_id;

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String businessName) {
        BusinessName = businessName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSrvc_id() {
        return srvc_id;
    }

    public void setSrvc_id(String srvc_id) {
        this.srvc_id = srvc_id;
    }

    @Override
    public String toString() {
        return "ServiceItem2Model{" +
                "ImgUrl='" + ImgUrl + '\'' +
                ", BusinessName='" + BusinessName + '\'' +
                ", Price='" + Price + '\'' +
                ", job='" + job + '\'' +
                ", category='" + category + '\'' +
                ", srvc_id='" + srvc_id + '\'' +
                '}';
    }

    public ServiceItem2Model(String imgUrl, String businessName, String price, String job, String category, String srvc_id) {
        ImgUrl = imgUrl;
        BusinessName = businessName;
        Price = price;
        this.job = job;
        this.category = category;
        this.srvc_id = srvc_id;

    }
}
