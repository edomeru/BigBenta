package ph.dreambig.bigbenta.marketplacev2.Search.Search_ALL;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Stores {

@SerializedName("stores")
@Expose
private List<Store> stores = new ArrayList<Store>();
@SerializedName("code")
@Expose
private Integer code;

/**
* 
* @return
* The stores
*/
public List<Store> getStores() {
return stores;
}

/**
* 
* @param stores
* The stores
*/
public void setStores(List<Store> stores) {
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