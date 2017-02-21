package ph.dreambig.bigbenta.marketplacev2.LogIn.Facebook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("user_id")
@Expose
private String userId;
@SerializedName("access_token")
@Expose
private String accessToken;
@SerializedName("types")
@Expose
private String types;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getAccessToken() {
return accessToken;
}

public void setAccessToken(String accessToken) {
this.accessToken = accessToken;
}

public String getTypes() {
return types;
}

public void setTypes(String types) {
this.types = types;
}

}