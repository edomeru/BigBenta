package ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.MyAds;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SellReason {

@SerializedName("sell_reason_id")
@Expose
private String sellReasonId;
@SerializedName("name")
@Expose
private String name;

/**
* 
* @return
* The sellReasonId
*/
public String getSellReasonId() {
return sellReasonId;
}

/**
* 
* @param sellReasonId
* The sell_reason_id
*/
public void setSellReasonId(String sellReasonId) {
this.sellReasonId = sellReasonId;
}

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

}