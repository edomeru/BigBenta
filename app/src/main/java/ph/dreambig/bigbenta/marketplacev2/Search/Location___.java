package ph.dreambig.bigbenta.marketplacev2.Search;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Location___ {

@SerializedName("city_name")
@Expose
private String cityName;
@SerializedName("region_name")
@Expose
private String regionName;

/**
* 
* @return
* The cityName
*/
public String getCityName() {
return cityName;
}

/**
* 
* @param cityName
* The city_name
*/
public void setCityName(String cityName) {
this.cityName = cityName;
}

/**
* 
* @return
* The regionName
*/
public String getRegionName() {
return regionName;
}

/**
* 
* @param regionName
* The region_name
*/
public void setRegionName(String regionName) {
this.regionName = regionName;
}

}