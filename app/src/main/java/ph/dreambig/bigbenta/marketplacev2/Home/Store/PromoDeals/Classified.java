package ph.dreambig.bigbenta.marketplacev2.Home.Store.PromoDeals;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Classified {

@SerializedName("classified")
@Expose
private String classified;
@SerializedName("code")
@Expose
private Integer code;

/**
* 
* @return
* The classified
*/
public String getClassified() {
return classified;
}

/**
* 
* @param classified
* The classified
*/
public void setClassified(String classified) {
this.classified = classified;
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