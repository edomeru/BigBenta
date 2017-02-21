package ph.dreambig.bigbenta.marketplacev2.StoreItemDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("classified")
@Expose
private Classified classified;
@SerializedName("stores")
@Expose
private Stores stores;

public Classified getClassified() {
return classified;
}

public void setClassified(Classified classified) {
this.classified = classified;
}

public Stores getStores() {
return stores;
}

public void setStores(Stores stores) {
this.stores = stores;
}

}