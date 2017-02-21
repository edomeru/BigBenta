package ph.dreambig.bigbenta.marketplacev2.StoreItems;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Classified {

@SerializedName("classified")
@Expose
private String classified;
@SerializedName("code")
@Expose
private Integer code;

public String getClassified() {
return classified;
}

public void setClassified(String classified) {
this.classified = classified;
}

public Integer getCode() {
return code;
}

public void setCode(Integer code) {
this.code = code;
}

}