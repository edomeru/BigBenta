package ph.dreambig.bigbenta.marketplacev2.StoreItems;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

@SerializedName("city_name")
@Expose
private Object cityName;
@SerializedName("region_name")
@Expose
private Object regionName;

public Object getCityName() {
return cityName;
}

public void setCityName(Object cityName) {
this.cityName = cityName;
}

public Object getRegionName() {
return regionName;
}

public void setRegionName(Object regionName) {
this.regionName = regionName;
}

}