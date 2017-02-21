package ph.dreambig.bigbenta.marketplacev2.Home.Store.MyOrderHistory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

@SerializedName("name")
@Expose
private String name;
@SerializedName("quantity")
@Expose
private String quantity;
@SerializedName("price")
@Expose
private String price;
@SerializedName("total_price")
@Expose
private String totalPrice;
@SerializedName("weight")
@Expose
private String weight;
@SerializedName("item_percentage")
@Expose
private String itemPercentage;
@SerializedName("abandoned")
@Expose
private String abandoned;
@SerializedName("sale_price_percentage")
@Expose
private Object salePricePercentage;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("images")
@Expose
private List<Image> images = null;

/**
* 
* @return
* The name
*/
public String getName() {
return name;
}

/**
* 
* @param name
* The name
*/
public void setName(String name) {
this.name = name;
}

/**
* 
* @return
* The quantity
*/
public String getQuantity() {
return quantity;
}

/**
* 
* @param quantity
* The quantity
*/
public void setQuantity(String quantity) {
this.quantity = quantity;
}

/**
* 
* @return
* The price
*/
public String getPrice() {
return price;
}

/**
* 
* @param price
* The price
*/
public void setPrice(String price) {
this.price = price;
}

/**
* 
* @return
* The totalPrice
*/
public String getTotalPrice() {
return totalPrice;
}

/**
* 
* @param totalPrice
* The total_price
*/
public void setTotalPrice(String totalPrice) {
this.totalPrice = totalPrice;
}

/**
* 
* @return
* The weight
*/
public String getWeight() {
return weight;
}

/**
* 
* @param weight
* The weight
*/
public void setWeight(String weight) {
this.weight = weight;
}

/**
* 
* @return
* The itemPercentage
*/
public String getItemPercentage() {
return itemPercentage;
}

/**
* 
* @param itemPercentage
* The item_percentage
*/
public void setItemPercentage(String itemPercentage) {
this.itemPercentage = itemPercentage;
}

/**
* 
* @return
* The abandoned
*/
public String getAbandoned() {
return abandoned;
}

/**
* 
* @param abandoned
* The abandoned
*/
public void setAbandoned(String abandoned) {
this.abandoned = abandoned;
}

/**
* 
* @return
* The salePricePercentage
*/
public Object getSalePricePercentage() {
return salePricePercentage;
}

/**
* 
* @param salePricePercentage
* The sale_price_percentage
*/
public void setSalePricePercentage(Object salePricePercentage) {
this.salePricePercentage = salePricePercentage;
}

/**
* 
* @return
* The createdAt
*/
public String getCreatedAt() {
return createdAt;
}

/**
* 
* @param createdAt
* The created_at
*/
public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

/**
* 
* @return
* The images
*/
public List<Image> getImages() {
return images;
}

/**
* 
* @param images
* The images
*/
public void setImages(List<Image> images) {
this.images = images;
}

}