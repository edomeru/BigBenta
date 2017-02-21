package ph.dreambig.bigbenta.marketplacev2.AdsItemDetail;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Developer {

@SerializedName("developer_id")
@Expose
private String developerId;
@SerializedName("name")
@Expose
private String name;

/**
* 
* @return
* The developerId
*/
public String getDeveloperId() {
return developerId;
}

/**
* 
* @param developerId
* The developer_id
*/
public void setDeveloperId(String developerId) {
this.developerId = developerId;
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