package ph.dreambig.bigbenta.marketplacev2.Home.Store.MyOrderHistory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

@SerializedName("buyer_id")
@Expose
private String buyerId;
@SerializedName("buyer_email")
@Expose
private String buyerEmail;
@SerializedName("buyer_name")
@Expose
private String buyerName;
@SerializedName("buyer_order_number")
@Expose
private String buyerOrderNumber;
@SerializedName("buyer_payment_method")
@Expose
private String buyerPaymentMethod;
@SerializedName("buyer_payment_status")
@Expose
private String buyerPaymentStatus;
@SerializedName("buyer_abandoned")
@Expose
private String buyerAbandoned;
@SerializedName("buyer_expiration_date")
@Expose
private String buyerExpirationDate;
@SerializedName("items")
@Expose
private List<Item> items = null;

/**
* 
* @return
* The buyerId
*/
public String getBuyerId() {
return buyerId;
}

/**
* 
* @param buyerId
* The buyer_id
*/
public void setBuyerId(String buyerId) {
this.buyerId = buyerId;
}

/**
* 
* @return
* The buyerEmail
*/
public String getBuyerEmail() {
return buyerEmail;
}

/**
* 
* @param buyerEmail
* The buyer_email
*/
public void setBuyerEmail(String buyerEmail) {
this.buyerEmail = buyerEmail;
}

/**
* 
* @return
* The buyerName
*/
public String getBuyerName() {
return buyerName;
}

/**
* 
* @param buyerName
* The buyer_name
*/
public void setBuyerName(String buyerName) {
this.buyerName = buyerName;
}

/**
* 
* @return
* The buyerOrderNumber
*/
public String getBuyerOrderNumber() {
return buyerOrderNumber;
}

/**
* 
* @param buyerOrderNumber
* The buyer_order_number
*/
public void setBuyerOrderNumber(String buyerOrderNumber) {
this.buyerOrderNumber = buyerOrderNumber;
}

/**
* 
* @return
* The buyerPaymentMethod
*/
public String getBuyerPaymentMethod() {
return buyerPaymentMethod;
}

/**
* 
* @param buyerPaymentMethod
* The buyer_payment_method
*/
public void setBuyerPaymentMethod(String buyerPaymentMethod) {
this.buyerPaymentMethod = buyerPaymentMethod;
}

/**
* 
* @return
* The buyerPaymentStatus
*/
public String getBuyerPaymentStatus() {
return buyerPaymentStatus;
}

/**
* 
* @param buyerPaymentStatus
* The buyer_payment_status
*/
public void setBuyerPaymentStatus(String buyerPaymentStatus) {
this.buyerPaymentStatus = buyerPaymentStatus;
}

/**
* 
* @return
* The buyerAbandoned
*/
public String getBuyerAbandoned() {
return buyerAbandoned;
}

/**
* 
* @param buyerAbandoned
* The buyer_abandoned
*/
public void setBuyerAbandoned(String buyerAbandoned) {
this.buyerAbandoned = buyerAbandoned;
}

/**
* 
* @return
* The buyerExpirationDate
*/
public String getBuyerExpirationDate() {
return buyerExpirationDate;
}

/**
* 
* @param buyerExpirationDate
* The buyer_expiration_date
*/
public void setBuyerExpirationDate(String buyerExpirationDate) {
this.buyerExpirationDate = buyerExpirationDate;
}

/**
* 
* @return
* The items
*/
public List<Item> getItems() {
return items;
}

/**
* 
* @param items
* The items
*/
public void setItems(List<Item> items) {
this.items = items;
}

}