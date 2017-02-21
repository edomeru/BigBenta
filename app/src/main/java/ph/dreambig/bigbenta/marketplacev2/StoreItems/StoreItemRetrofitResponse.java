package ph.dreambig.bigbenta.marketplacev2.StoreItems;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreItemRetrofitResponse {

@SerializedName("message")
@Expose
private String message;
@SerializedName("data")
@Expose
private Data data;
@SerializedName("code")
@Expose
private Integer code;

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public Data getData() {
return data;
}

public void setData(Data data) {
this.data = data;
}

public Integer getCode() {
return code;
}

public void setCode(Integer code) {
this.code = code;
}

}