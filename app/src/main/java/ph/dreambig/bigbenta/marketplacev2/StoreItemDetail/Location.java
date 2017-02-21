package ph.dreambig.bigbenta.marketplacev2.StoreItemDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

@SerializedName("city_name")
@Expose
private String cityName;
@SerializedName("region_name")
@Expose
private String regionName;

public String getCityName() {
return cityName;
}

public void setCityName(String cityName) {
this.cityName = cityName;
}

public String getRegionName() {
return regionName;
}

public void setRegionName(String regionName) {
this.regionName = regionName;
}

}