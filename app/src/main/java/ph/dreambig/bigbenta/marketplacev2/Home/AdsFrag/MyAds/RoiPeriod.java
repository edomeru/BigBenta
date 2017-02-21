package ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.MyAds;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RoiPeriod {

@SerializedName("roi_period_id")
@Expose
private String roiPeriodId;
@SerializedName("name")
@Expose
private String name;

/**
* 
* @return
* The roiPeriodId
*/
public String getRoiPeriodId() {
return roiPeriodId;
}

/**
* 
* @param roiPeriodId
* The roi_period_id
*/
public void setRoiPeriodId(String roiPeriodId) {
this.roiPeriodId = roiPeriodId;
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