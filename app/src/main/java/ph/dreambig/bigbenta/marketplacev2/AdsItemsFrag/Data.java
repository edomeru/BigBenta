package ph.dreambig.bigbenta.marketplacev2.AdsItemsFrag;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

@SerializedName("classified")
@Expose
private Classified classified;
@SerializedName("stores")
@Expose
private Stores stores;

/**
* 
* @return
* The classified
*/
public Classified getClassified() {
return classified;
}

/**
* 
* @param classified
* The classified
*/
public void setClassified(Classified classified) {
this.classified = classified;
}

/**
* 
* @return
* The stores
*/
public Stores getStores() {
return stores;
}

/**
* 
* @param stores
* The stores
*/
public void setStores(Stores stores) {
this.stores = stores;
}

}
