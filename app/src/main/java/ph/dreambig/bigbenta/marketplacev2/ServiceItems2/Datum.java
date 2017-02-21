package ph.dreambig.bigbenta.marketplacev2.ServiceItems2;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Datum {
    @Override
    public String toString() {
        return "Datum{" +
                "display='" + display + '\'' +
                ", srvcId='" + srvcId + '\'' +
                ", spId='" + spId + '\'' +
                ", mainCategoryId='" + mainCategoryId + '\'' +
                ", subCategoryId='" + subCategoryId + '\'' +
                ", feeType='" + feeType + '\'' +
                ", categoryItemId='" + categoryItemId + '\'' +
                ", expertise='" + expertise + '\'' +
                ", type='" + type + '\'' +
                ", stage='" + stage + '\'' +
                ", isVerified='" + isVerified + '\'' +
                ", businessName='" + businessName + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", schedule='" + schedule + '\'' +
                ", smDomain='" + smDomain + '\'' +
                ", smGroup='" + smGroup + '\'' +
                ", smName='" + smName + '\'' +
                ", smExtension='" + smExtension + '\'' +
                ", regionName='" + regionName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", rateName='" + rateName + '\'' +
                ", rate=" + rate +
                ", subCatName='" + subCatName + '\'' +
                ", catItemName='" + catItemName + '\'' +
                ", catItemMask='" + catItemMask + '\'' +
                ", expertiseValue='" + expertiseValue + '\'' +
                '}';
    }

    @SerializedName("display")
@Expose
private String display;
@SerializedName("srvc_id")
@Expose
private String srvcId;
@SerializedName("sp_id")
@Expose
private String spId;
@SerializedName("main_category_id")
@Expose
private String mainCategoryId;
@SerializedName("sub_category_id")
@Expose
private String subCategoryId;
@SerializedName("fee_type")
@Expose
private String feeType;
@SerializedName("category_item_id")
@Expose
private String categoryItemId;
@SerializedName("expertise")
@Expose
private String expertise;
@SerializedName("type")
@Expose
private String type;
@SerializedName("stage")
@Expose
private String stage;
@SerializedName("is_verified")
@Expose
private String isVerified;
@SerializedName("business_name")
@Expose
private String businessName;
@SerializedName("region")
@Expose
private String region;
@SerializedName("city")
@Expose
private String city;
@SerializedName("last_name")
@Expose
private String lastName;
@SerializedName("first_name")
@Expose
private String firstName;
@SerializedName("schedule")
@Expose
private String schedule;
@SerializedName("sm_domain")
@Expose
private String smDomain;
@SerializedName("sm_group")
@Expose
private String smGroup;
@SerializedName("sm_name")
@Expose
private String smName;
@SerializedName("sm_extension")
@Expose
private String smExtension;
@SerializedName("region_name")
@Expose
private String regionName;
@SerializedName("city_name")
@Expose
private String cityName;
@SerializedName("rate_name")
@Expose
private String rateName;
@SerializedName("rate")
@Expose
private Integer rate;
@SerializedName("sub_cat_name")
@Expose
private String subCatName;
@SerializedName("cat_item_name")
@Expose
private String catItemName;
@SerializedName("cat_item_mask")
@Expose
private String catItemMask;
@SerializedName("expertise_value")
@Expose
private String expertiseValue;

/**
* 
* @return
* The display
*/
public String getDisplay() {
return display;
}

/**
* 
* @param display
* The display
*/
public void setDisplay(String display) {
this.display = display;
}

/**
* 
* @return
* The srvcId
*/
public String getSrvcId() {
return srvcId;
}

/**
* 
* @param srvcId
* The srvc_id
*/
public void setSrvcId(String srvcId) {
this.srvcId = srvcId;
}

/**
* 
* @return
* The spId
*/
public String getSpId() {
return spId;
}

/**
* 
* @param spId
* The sp_id
*/
public void setSpId(String spId) {
this.spId = spId;
}

/**
* 
* @return
* The mainCategoryId
*/
public String getMainCategoryId() {
return mainCategoryId;
}

/**
* 
* @param mainCategoryId
* The main_category_id
*/
public void setMainCategoryId(String mainCategoryId) {
this.mainCategoryId = mainCategoryId;
}

/**
* 
* @return
* The subCategoryId
*/
public String getSubCategoryId() {
return subCategoryId;
}

/**
* 
* @param subCategoryId
* The sub_category_id
*/
public void setSubCategoryId(String subCategoryId) {
this.subCategoryId = subCategoryId;
}

/**
* 
* @return
* The feeType
*/
public String getFeeType() {
return feeType;
}

/**
* 
* @param feeType
* The fee_type
*/
public void setFeeType(String feeType) {
this.feeType = feeType;
}

/**
* 
* @return
* The categoryItemId
*/
public String getCategoryItemId() {
return categoryItemId;
}

/**
* 
* @param categoryItemId
* The category_item_id
*/
public void setCategoryItemId(String categoryItemId) {
this.categoryItemId = categoryItemId;
}

/**
* 
* @return
* The expertise
*/
public String getExpertise() {
return expertise;
}

/**
* 
* @param expertise
* The expertise
*/
public void setExpertise(String expertise) {
this.expertise = expertise;
}

/**
* 
* @return
* The type
*/
public String getType() {
return type;
}

/**
* 
* @param type
* The type
*/
public void setType(String type) {
this.type = type;
}

/**
* 
* @return
* The stage
*/
public String getStage() {
return stage;
}

/**
* 
* @param stage
* The stage
*/
public void setStage(String stage) {
this.stage = stage;
}

/**
* 
* @return
* The isVerified
*/
public String getIsVerified() {
return isVerified;
}

/**
* 
* @param isVerified
* The is_verified
*/
public void setIsVerified(String isVerified) {
this.isVerified = isVerified;
}

/**
* 
* @return
* The businessName
*/
public String getBusinessName() {
return businessName;
}

/**
* 
* @param businessName
* The business_name
*/
public void setBusinessName(String businessName) {
this.businessName = businessName;
}

/**
* 
* @return
* The region
*/
public String getRegion() {
return region;
}

/**
* 
* @param region
* The region
*/
public void setRegion(String region) {
this.region = region;
}

/**
* 
* @return
* The city
*/
public String getCity() {
return city;
}

/**
* 
* @param city
* The city
*/
public void setCity(String city) {
this.city = city;
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
* The schedule
*/
public String getSchedule() {
return schedule;
}

/**
* 
* @param schedule
* The schedule
*/
public void setSchedule(String schedule) {
this.schedule = schedule;
}

/**
* 
* @return
* The smDomain
*/
public String getSmDomain() {
return smDomain;
}

/**
* 
* @param smDomain
* The sm_domain
*/
public void setSmDomain(String smDomain) {
this.smDomain = smDomain;
}

/**
* 
* @return
* The smGroup
*/
public String getSmGroup() {
return smGroup;
}

/**
* 
* @param smGroup
* The sm_group
*/
public void setSmGroup(String smGroup) {
this.smGroup = smGroup;
}

/**
* 
* @return
* The smName
*/
public String getSmName() {
return smName;
}

/**
* 
* @param smName
* The sm_name
*/
public void setSmName(String smName) {
this.smName = smName;
}

/**
* 
* @return
* The smExtension
*/
public String getSmExtension() {
return smExtension;
}

/**
* 
* @param smExtension
* The sm_extension
*/
public void setSmExtension(String smExtension) {
this.smExtension = smExtension;
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
* The rateName
*/
public String getRateName() {
return rateName;
}

/**
* 
* @param rateName
* The rate_name
*/
public void setRateName(String rateName) {
this.rateName = rateName;
}

/**
* 
* @return
* The rate
*/
public Integer getRate() {
return rate;
}

/**
* 
* @param rate
* The rate
*/
public void setRate(Integer rate) {
this.rate = rate;
}

/**
* 
* @return
* The subCatName
*/
public String getSubCatName() {
return subCatName;
}

/**
* 
* @param subCatName
* The sub_cat_name
*/
public void setSubCatName(String subCatName) {
this.subCatName = subCatName;
}

/**
* 
* @return
* The catItemName
*/
public String getCatItemName() {
return catItemName;
}

/**
* 
* @param catItemName
* The cat_item_name
*/
public void setCatItemName(String catItemName) {
this.catItemName = catItemName;
}

/**
* 
* @return
* The catItemMask
*/
public String getCatItemMask() {
return catItemMask;
}

/**
* 
* @param catItemMask
* The cat_item_mask
*/
public void setCatItemMask(String catItemMask) {
this.catItemMask = catItemMask;
}

/**
* 
* @return
* The expertiseValue
*/
public String getExpertiseValue() {
return expertiseValue;
}

/**
* 
* @param expertiseValue
* The expertise_value
*/
public void setExpertiseValue(String expertiseValue) {
this.expertiseValue = expertiseValue;
}

}