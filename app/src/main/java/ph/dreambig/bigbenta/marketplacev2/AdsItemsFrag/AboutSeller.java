package ph.dreambig.bigbenta.marketplacev2.AdsItemsFrag;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AboutSeller {

@SerializedName("user_id")
@Expose
private String userId;
@SerializedName("first_name")
@Expose
private String firstName;
@SerializedName("last_name")
@Expose
private String lastName;
@SerializedName("email")
@Expose
private String email;
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

/**
* 
* @return
* The userId
*/
public String getUserId() {
return userId;
}

/**
* 
* @param userId
* The user_id
*/
public void setUserId(String userId) {
this.userId = userId;
}

/**
* 
* @return
* The firstName
*/
public String getFirstName() {
return firstName;
}

/**
* 
* @param firstName
* The first_name
*/
public void setFirstName(String firstName) {
this.firstName = firstName;
}

/**
* 
* @return
* The lastName
*/
public String getLastName() {
return lastName;
}

/**
* 
* @param lastName
* The last_name
*/
public void setLastName(String lastName) {
this.lastName = lastName;
}

/**
* 
* @return
* The email
*/
public String getEmail() {
return email;
}

/**
* 
* @param email
* The email
*/
public void setEmail(String email) {
this.email = email;
}

/**
* 
* @return
* The phone
*/
public String getPhone() {
return phone;
}

/**
* 
* @param phone
* The phone
*/
public void setPhone(String phone) {
this.phone = phone;
}

/**
* 
* @return
* The mobile
*/
public String getMobile() {
return mobile;
}

/**
* 
* @param mobile
* The mobile
*/
public void setMobile(String mobile) {
this.mobile = mobile;
}

/**
* 
* @return
* The createdDate
*/
public String getCreatedDate() {
return createdDate;
}

/**
* 
* @param createdDate
* The created_date
*/
public void setCreatedDate(String createdDate) {
this.createdDate = createdDate;
}

/**
* 
* @return
* The imageSm
*/
public String getImageSm() {
return imageSm;
}

/**
* 
* @param imageSm
* The image_sm
*/
public void setImageSm(String imageSm) {
this.imageSm = imageSm;
}

/**
* 
* @return
* The imageMd
*/
public String getImageMd() {
return imageMd;
}

/**
* 
* @param imageMd
* The image_md
*/
public void setImageMd(String imageMd) {
this.imageMd = imageMd;
}

}