package ph.dreambig.bigbenta.marketplacev2.StoreItemDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AboutSeller {

@SerializedName("user_id")
@Expose
private String userId;
@SerializedName("email")
@Expose
private String email;
@SerializedName("first_name")
@Expose
private String firstName;
@SerializedName("last_name")
@Expose
private String lastName;
@SerializedName("phone")
@Expose
private String phone;
@SerializedName("mobile")
@Expose
private String mobile;
@SerializedName("created_date")
@Expose
private String createdDate;
@SerializedName("image_sm")
@Expose
private String imageSm;
@SerializedName("image_md")
@Expose
private String imageMd;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getFirstName() {
return firstName;
}

public void setFirstName(String firstName) {
this.firstName = firstName;
}

public String getLastName() {
return lastName;
}

public void setLastName(String lastName) {
this.lastName = lastName;
}

public String getPhone() {
return phone;
}

public void setPhone(String phone) {
this.phone = phone;
}

public String getMobile() {
return mobile;
}

public void setMobile(String mobile) {
this.mobile = mobile;
}

public String getCreatedDate() {
return createdDate;
}

public void setCreatedDate(String createdDate) {
this.createdDate = createdDate;
}

public String getImageSm() {
return imageSm;
}

public void setImageSm(String imageSm) {
this.imageSm = imageSm;
}

public String getImageMd() {
return imageMd;
}

public void setImageMd(String imageMd) {
this.imageMd = imageMd;
}

}