
package ph.dreambig.bigbenta.marketplacev2.Home.Service.Categories_Service.MyBookings;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class BookingListData extends RealmObject {

    @PrimaryKey
    private int id;
    private String SPStatus;
    private String ImageUrl;
    public String SPName;
    private String Availability;
    private String DateBooked;
    private String RefNo;
    private int Amount;
    private String Duration;

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    private String SPDate;
    private String SPAddress;
    private String PaymentStatus;

    @Override
    public String toString() {
        return "BookingListData{" +
                "Iid=" + id +
                ", ImageUrl='" + ImageUrl + '\'' +
                ", SPName='" + SPName + '\'' +
                ", Availability='" + Availability + '\'' +
                ", DateBooked='" + DateBooked + '\'' +
                ", RefNo=" + RefNo +
                ", Amount=" + Amount +
                ", Duration=" + Duration +
                ", SPDate='" + SPDate + '\'' +
                ", SPAddress='" + SPAddress + '\'' +
                ", PaymentStatus='" + PaymentStatus + '\'' +
                ", SPStatus='" + SPStatus + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getSPName() {
        return SPName;
    }

    public void setSPName(String SPName) {
        this.SPName = SPName;
    }

    public String getAvailability() {
        return Availability;
    }

    public void setAvailability(String availability) {
        Availability = availability;
    }

    public String getDateBooked() {
        return DateBooked;
    }

    public void setDateBooked(String dateBooked) {
        DateBooked = dateBooked;
    }

    public String getRefNo() {
        return RefNo;
    }

    public void setRefNo(String refNo) {
        RefNo = refNo;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }



    public String getSPDate() {
        return SPDate;
    }

    public void setSPDate(String SPDate) {
        this.SPDate = SPDate;
    }

    public String getSPAddress() {
        return SPAddress;
    }

    public void setSPAddress(String SPAddress) {
        this.SPAddress = SPAddress;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
    }

    public String getSPStatus() {
        return SPStatus;
    }

    public void setSPStatus(String SPStatus) {
        this.SPStatus = SPStatus;
    }



}

