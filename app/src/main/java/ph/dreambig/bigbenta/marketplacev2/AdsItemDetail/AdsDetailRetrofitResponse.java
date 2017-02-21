package ph.dreambig.bigbenta.marketplacev2.AdsItemDetail;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AdsDetailRetrofitResponse {

@SerializedName("message")
@Expose
private String message;
@SerializedName("data")
@Expose
private Data data;
@SerializedName("code")
@Expose
private Integer code;

    @Override
    public String toString() {
        return "AdsDetailRetrofitResponse{" +
                "message='" + message + '\'' +
                ", data=" + data +
                ", code=" + code +
                '}';
    }

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
public Data getData() {
return data;
}

/**
* 
* @param data
* The data
*/
public void setData(Data data) {
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