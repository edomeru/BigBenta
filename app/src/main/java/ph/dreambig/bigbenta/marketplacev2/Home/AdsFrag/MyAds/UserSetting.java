package ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.MyAds;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserSetting {

@SerializedName("user_id")
@Expose
private String userId;
@SerializedName("timezone_id")
@Expose
private String timezoneId;
@SerializedName("show_contact_numbers")
@Expose
private String showContactNumbers;
@SerializedName("show_email")
@Expose
private String showEmail;
@SerializedName("item_list_display_view")
@Expose
private String itemListDisplayView;
@SerializedName("item_list_display_order")
@Expose
private String itemListDisplayOrder;
@SerializedName("item_list_no_of_ads")
@Expose
private String itemListNoOfAds;
@SerializedName("allow_item_send_message")
@Expose
private String allowItemSendMessage;

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
* The timezoneId
*/
public String getTimezoneId() {
return timezoneId;
}

/**
* 
* @param timezoneId
* The timezone_id
*/
public void setTimezoneId(String timezoneId) {
this.timezoneId = timezoneId;
}

/**
* 
* @return
* The showContactNumbers
*/
public String getShowContactNumbers() {
return showContactNumbers;
}

/**
* 
* @param showContactNumbers
* The show_contact_numbers
*/
public void setShowContactNumbers(String showContactNumbers) {
this.showContactNumbers = showContactNumbers;
}

/**
* 
* @return
* The showEmail
*/
public String getShowEmail() {
return showEmail;
}

/**
* 
* @param showEmail
* The show_email
*/
public void setShowEmail(String showEmail) {
this.showEmail = showEmail;
}

/**
* 
* @return
* The itemListDisplayView
*/
public String getItemListDisplayView() {
return itemListDisplayView;
}

/**
* 
* @param itemListDisplayView
* The item_list_display_view
*/
public void setItemListDisplayView(String itemListDisplayView) {
this.itemListDisplayView = itemListDisplayView;
}

/**
* 
* @return
* The itemListDisplayOrder
*/
public String getItemListDisplayOrder() {
return itemListDisplayOrder;
}

/**
* 
* @param itemListDisplayOrder
* The item_list_display_order
*/
public void setItemListDisplayOrder(String itemListDisplayOrder) {
this.itemListDisplayOrder = itemListDisplayOrder;
}

/**
* 
* @return
* The itemListNoOfAds
*/
public String getItemListNoOfAds() {
return itemListNoOfAds;
}

/**
* 
* @param itemListNoOfAds
* The item_list_no_of_ads
*/
public void setItemListNoOfAds(String itemListNoOfAds) {
this.itemListNoOfAds = itemListNoOfAds;
}

/**
* 
* @return
* The allowItemSendMessage
*/
public String getAllowItemSendMessage() {
return allowItemSendMessage;
}

/**
* 
* @param allowItemSendMessage
* The allow_item_send_message
*/
public void setAllowItemSendMessage(String allowItemSendMessage) {
this.allowItemSendMessage = allowItemSendMessage;
}

}