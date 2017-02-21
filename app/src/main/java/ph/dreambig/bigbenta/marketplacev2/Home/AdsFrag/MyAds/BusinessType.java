package ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.MyAds;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BusinessType {

@SerializedName("business_type_id")
@Expose
private String businessTypeId;
@SerializedName("name")
@Expose
private String name;

/**
* 
* @return
* The businessTypeId
*/
public String getBusinessTypeId() {
return businessTypeId;
}

/**
* 
* @param businessTypeId
* The business_type_id
*/
public void setBusinessTypeId(String businessTypeId) {
this.businessTypeId = businessTypeId;
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