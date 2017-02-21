package ph.dreambig.bigbenta.marketplacev2.StoreItems;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stores {

@SerializedName("stores")
@Expose
private List<Store> stores = null;
@SerializedName("code")
@Expose
private Integer code;

public List<Store> getStores() {
return stores;
}

public void setStores(List<Store> stores) {
this.stores = stores;
}

public Integer getCode() {
return code;
}

public void setCode(Integer code) {
this.code = code;
}

}