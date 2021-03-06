package ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.MyAds;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Classified_ {

@SerializedName("id")
@Expose
private String id;
@SerializedName("title")
@Expose
private String title;
@SerializedName("permalink")
@Expose
private String permalink;
@SerializedName("description")
@Expose
private String description;
@SerializedName("price")
@Expose
private String price;
@SerializedName("edit_price")
@Expose
private String editPrice;
@SerializedName("location")
@Expose
private List<Location> location = new ArrayList<Location>();
@SerializedName("year_model")
@Expose
private String yearModel;
@SerializedName("image")
@Expose
private List<Image> image = new ArrayList<Image>();
@SerializedName("category")
@Expose
private List<Category> category = new ArrayList<Category>();
@SerializedName("about_seller")
@Expose
private List<AboutSeller> aboutSeller = new ArrayList<AboutSeller>();
@SerializedName("extra_fields")
@Expose
private ExtraFields extraFields;
@SerializedName("created_date")
@Expose
private String createdDate;
@SerializedName("user_settings")
@Expose
private List<UserSetting> userSettings = new ArrayList<UserSetting>();
@SerializedName("status")
@Expose
private String status;
@SerializedName("sold_date")
@Expose
private Object soldDate;
@SerializedName("rating")
@Expose
private Integer rating;
@SerializedName("category_name")
@Expose
private Object categoryName;
@SerializedName("category_name_permalink")
@Expose
private String categoryNamePermalink;
@SerializedName("search_name")
@Expose
private String searchName;
@SerializedName("category_search_name")
@Expose
private Object categorySearchName;

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
* The title
*/
public String getTitle() {
return title;
}

/**
* 
* @param title
* The title
*/
public void setTitle(String title) {
this.title = title;
}

/**
* 
* @return
* The permalink
*/
public String getPermalink() {
return permalink;
}

/**
* 
* @param permalink
* The permalink
*/
public void setPermalink(String permalink) {
this.permalink = permalink;
}

/**
* 
* @return
* The description
*/
public String getDescription() {
return description;
}

/**
* 
* @param description
* The description
*/
public void setDescription(String description) {
this.description = description;
}

/**
* 
* @return
* The price
*/
public String getPrice() {
return price;
}

/**
* 
* @param price
* The price
*/
public void setPrice(String price) {
this.price = price;
}

/**
* 
* @return
* The editPrice
*/
public String getEditPrice() {
return editPrice;
}

/**
* 
* @param editPrice
* The edit_price
*/
public void setEditPrice(String editPrice) {
this.editPrice = editPrice;
}

/**
* 
* @return
* The location
*/
public List<Location> getLocation() {
return location;
}

/**
* 
* @param location
* The location
*/
public void setLocation(List<Location> location) {
this.location = location;
}

/**
* 
* @return
* The yearModel
*/
public String getYearModel() {
return yearModel;
}

/**
* 
* @param yearModel
* The year_model
*/
public void setYearModel(String yearModel) {
this.yearModel = yearModel;
}

/**
* 
* @return
* The image
*/
public List<Image> getImage() {
return image;
}

/**
* 
* @param image
* The image
*/
public void setImage(List<Image> image) {
this.image = image;
}

/**
* 
* @return
* The category
*/
public List<Category> getCategory() {
return category;
}

/**
* 
* @param category
* The category
*/
public void setCategory(List<Category> category) {
this.category = category;
}

/**
* 
* @return
* The aboutSeller
*/
public List<AboutSeller> getAboutSeller() {
return aboutSeller;
}

/**
* 
* @param aboutSeller
* The about_seller
*/
public void setAboutSeller(List<AboutSeller> aboutSeller) {
this.aboutSeller = aboutSeller;
}

/**
* 
* @return
* The extraFields
*/
public ExtraFields getExtraFields() {
return extraFields;
}

/**
* 
* @param extraFields
* The extra_fields
*/
public void setExtraFields(ExtraFields extraFields) {
this.extraFields = extraFields;
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
* The userSettings
*/
public List<UserSetting> getUserSettings() {
return userSettings;
}

/**
* 
* @param userSettings
* The user_settings
*/
public void setUserSettings(List<UserSetting> userSettings) {
this.userSettings = userSettings;
}

/**
* 
* @return
* The status
*/
public String getStatus() {
return status;
}

/**
* 
* @param status
* The status
*/
public void setStatus(String status) {
this.status = status;
}

/**
* 
* @return
* The soldDate
*/
public Object getSoldDate() {
return soldDate;
}

/**
* 
* @param soldDate
* The sold_date
*/
public void setSoldDate(Object soldDate) {
this.soldDate = soldDate;
}

/**
* 
* @return
* The rating
*/
public Integer getRating() {
return rating;
}

/**
* 
* @param rating
* The rating
*/
public void setRating(Integer rating) {
this.rating = rating;
}

/**
* 
* @return
* The categoryName
*/
public Object getCategoryName() {
return categoryName;
}

/**
* 
* @param categoryName
* The category_name
*/
public void setCategoryName(Object categoryName) {
this.categoryName = categoryName;
}

/**
* 
* @return
* The categoryNamePermalink
*/
public String getCategoryNamePermalink() {
return categoryNamePermalink;
}

/**
* 
* @param categoryNamePermalink
* The category_name_permalink
*/
public void setCategoryNamePermalink(String categoryNamePermalink) {
this.categoryNamePermalink = categoryNamePermalink;
}

/**
* 
* @return
* The searchName
*/
public String getSearchName() {
return searchName;
}

/**
* 
* @param searchName
* The search_name
*/
public void setSearchName(String searchName) {
this.searchName = searchName;
}

/**
* 
* @return
* The categorySearchName
*/
public Object getCategorySearchName() {
return categorySearchName;
}

/**
* 
* @param categorySearchName
* The category_search_name
*/
public void setCategorySearchName(Object categorySearchName) {
this.categorySearchName = categorySearchName;
}

}