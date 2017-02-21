package ph.dreambig.bigbenta.marketplacev2.AdsSubCat;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AdsSubCatRetrofitResponse {

@SerializedName("message")
@Expose
private String message;
@SerializedName("data")
@Expose
private List<Datum> data = new ArrayList<Datum>();
@SerializedName("code")
@Expose
private Integer code;

/**
* 
* @return
* The message
*/
public String getMessage() {
return message;
}

/**
* 
* @param message
* The message
*/
public void setMessage(String message) {
this.message = message;
}

/**
* 
* @return
* The data
*/
public List<Datum> getData() {
return data;
}

/**
* 
* @param data
* The data
*/
public void setData(List<Datum> data) {
this.data = data;
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