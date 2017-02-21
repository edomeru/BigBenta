package ph.dreambig.bigbenta.marketplacev2.AdsItemsFrag;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Stores {

@SerializedName("stores")
@Expose
private String stores;
@SerializedName("code")
@Expose
private Integer code;

/**
* 
* @return
* The stores
*/
public String getStores() {
return stores;
}

/**
* 
* @param stores
* The stores
*/
public void setStores(String stores) {
this.stores = stores;
}

/**
* 
* @return
* The code
*/
public Integer getCode() {
return code;
}

/**
* 
* @param code
* The code
*/
public void setCode(Integer code) {
this.code = code;
}

}