package ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.MyAds;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ServiceYear {

@SerializedName("service_year_id")
@Expose
private String serviceYearId;
@SerializedName("name")
@Expose
private String name;

/**
* 
* @return
* The serviceYearId
*/
public String getServiceYearId() {
return serviceYearId;
}

/**
* 
* @param serviceYearId
* The service_year_id
*/
public void setServiceYearId(String serviceYearId) {
this.serviceYearId = serviceYearId;
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