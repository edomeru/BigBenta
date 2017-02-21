package ph.dreambig.bigbenta.marketplacev2.Search;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Classified {

@SerializedName("classified")
@Expose
private List<Classified_> classified = new ArrayList<Classified_>();
@SerializedName("code")
@Expose
private Integer code;

/**
* 
* @return
* The classified
*/
public List<Classified_> getClassified() {
return classified;
}

/**
* 
* @param classified
* The classified
*/
public void setClassified(List<Classified_> classified) {
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