package ph.dreambig.bigbenta.marketplacev2.ServiceItems2;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ServiceResponse {

@SerializedName("code")
@Expose
private Integer code;

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }

    @SerializedName("data")
@Expose
private List<Datum> data = new ArrayList<Datum>();

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

}