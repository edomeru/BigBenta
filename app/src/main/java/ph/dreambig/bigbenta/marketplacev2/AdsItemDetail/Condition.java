package ph.dreambig.bigbenta.marketplacev2.AdsItemDetail;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Condition {

@SerializedName("condition_id")
@Expose
private String conditionId;
@SerializedName("name")
@Expose
private String name;

/**
* 
* @return
* The conditionId
*/
public String getConditionId() {
return conditionId;
}

/**
* 
* @param conditionId
* The condition_id
*/
public void setConditionId(String conditionId) {
this.conditionId = conditionId;
}

/**
* 
* @return
* The name
*/
public String getName() {
return name;
}

/**
* 
* @param name
* The name
*/
public void setName(String name) {
this.name = name;
}

}