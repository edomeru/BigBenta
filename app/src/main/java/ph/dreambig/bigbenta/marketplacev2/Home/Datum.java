package ph.dreambig.bigbenta.marketplacev2.Home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

@SerializedName("id")
@Expose
private String id;
@SerializedName("banner_url")
@Expose
private String bannerUrl;

/**
* 
* @return
* The id
*/
public String getId() {
return id;
}

/**
* 
* @param id
* The id
*/
public void setId(String id) {
this.id = id;
}

/**
* 
* @return
* The bannerUrl
*/
public String getBannerUrl() {
return bannerUrl;
}

/**
* 
* @param bannerUrl
* The banner_url
*/
public void setBannerUrl(String bannerUrl) {
this.bannerUrl = bannerUrl;
}

}