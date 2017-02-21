package ph.dreambig.bigbenta.marketplacev2.Home.Store.PromoDeals;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Store {

@SerializedName("id")
@Expose
private String id;
@SerializedName("title")
@Expose
private String title;
@SerializedName("permalink")
@Expose
private String permalink;
@SerializedName("price")
@Expose
private String price;
@SerializedName("original_price")
@Expose
private String originalPrice;
@SerializedName("quantity")
@Expose
private String quantity;
@SerializedName("short_description")
@Expose
private String shortDescription;
@SerializedName("long_description")
@Expose
private String longDescription;
@SerializedName("store_category_id")
@Expose
private String storeCategoryId;
@SerializedName("location")
@Expose
private List<Location> location = new ArrayList<Location>();
@SerializedName("about_seller")
@Expose
private String aboutSeller;
@SerializedName("image")
@Expose
private List<Image> image = new ArrayList<Image>();
@SerializedName("variants")
@Expose
private String variants;
@SerializedName("created_date")
@Expose
private String createdDate;
@SerializedName("user_id")
@Expose
private String userId;
@SerializedName("category")
@Expose
private String category;
@SerializedName("variant_id")
@Expose
private String variantId;
@SerializedName("minimum_order")
@Expose
private String minimumOrder;
@SerializedName("store_id")
@Expose
private String storeId;
@SerializedName("store_name")
@Expose
private String storeName;
@SerializedName("store_page")
@Expose
private String storePage;
@SerializedName("status")
@Expose
private String status;
@SerializedName("category_name")
@Expose
private String categoryName;
@SerializedName("search_name")
@Expose
private String searchName;
@SerializedName("category_name_permalink")
@Expose
private String categoryNamePermalink;
@SerializedName("is_fragile")
@Expose
private String isFragile;
@SerializedName("is_cod")
@Expose
private String isCod;
@SerializedName("weight")
@Expose
private String weight;
@SerializedName("height")
@Expose
private String height;
@SerializedName("length")
@Expose
private String length;
@SerializedName("width")
@Expose
private String width;
@SerializedName("promo_title")
@Expose
private String promoTitle;
@SerializedName("promo_sale_price_percentage")
@Expose
private String promoSalePricePercentage;
@SerializedName("promo_start")
@Expose
private String promoStart;
@SerializedName("promo_end")
@Expose
private String promoEnd;
@SerializedName("promo_type")
@Expose
private String promoType;
@SerializedName("is_sale")
@Expose
private Integer isSale;
@SerializedName("today")
@Expose
private String today;

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
* The originalPrice
*/
public String getOriginalPrice() {
return originalPrice;
}

/**
* 
* @param originalPrice
* The original_price
*/
public void setOriginalPrice(String originalPrice) {
this.originalPrice = originalPrice;
}

/**
* 
* @return
* The quantity
*/
public String getQuantity() {
return quantity;
}

/**
* 
* @param quantity
* The quantity
*/
public void setQuantity(String quantity) {
this.quantity = quantity;
}

/**
* 
* @return
* The shortDescription
*/
public String getShortDescription() {
return shortDescription;
}

/**
* 
* @param shortDescription
* The short_description
*/
public void setShortDescription(String shortDescription) {
this.shortDescription = shortDescription;
}

/**
* 
* @return
* The longDescription
*/
public String getLongDescription() {
return longDescription;
}

/**
* 
* @param longDescription
* The long_description
*/
public void setLongDescription(String longDescription) {
this.longDescription = longDescription;
}

/**
* 
* @return
* The storeCategoryId
*/
public String getStoreCategoryId() {
return storeCategoryId;
}

/**
* 
* @param storeCategoryId
* The store_category_id
*/
public void setStoreCategoryId(String storeCategoryId) {
this.storeCategoryId = storeCategoryId;
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
* The aboutSeller
*/
public String getAboutSeller() {
return aboutSeller;
}

/**
* 
* @param aboutSeller
* The about_seller
*/
public void setAboutSeller(String aboutSeller) {
this.aboutSeller = aboutSeller;
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
* The variants
*/
public String getVariants() {
return variants;
}

/**
* 
* @param variants
* The variants
*/
public void setVariants(String variants) {
this.variants = variants;
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
* The category
*/
public String getCategory() {
return category;
}

/**
* 
* @param category
* The category
*/
public void setCategory(String category) {
this.category = category;
}

/**
* 
* @return
* The variantId
*/
public String getVariantId() {
return variantId;
}

/**
* 
* @param variantId
* The variant_id
*/
public void setVariantId(String variantId) {
this.variantId = variantId;
}

/**
* 
* @return
* The minimumOrder
*/
public String getMinimumOrder() {
return minimumOrder;
}

/**
* 
* @param minimumOrder
* The minimum_order
*/
public void setMinimumOrder(String minimumOrder) {
this.minimumOrder = minimumOrder;
}

/**
* 
* @return
* The storeId
*/
public String getStoreId() {
return storeId;
}

/**
* 
* @param storeId
* The store_id
*/
public void setStoreId(String storeId) {
this.storeId = storeId;
}

/**
* 
* @return
* The storeName
*/
public String getStoreName() {
return storeName;
}

/**
* 
* @param storeName
* The store_name
*/
public void setStoreName(String storeName) {
this.storeName = storeName;
}

/**
* 
* @return
* The storePage
*/
public String getStorePage() {
return storePage;
}

/**
* 
* @param storePage
* The store_page
*/
public void setStorePage(String storePage) {
this.storePage = storePage;
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
* The categoryName
*/
public String getCategoryName() {
return categoryName;
}

/**
* 
* @param categoryName
* The category_name
*/
public void setCategoryName(String categoryName) {
this.categoryName = categoryName;
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
* The isFragile
*/
public String getIsFragile() {
return isFragile;
}

/**
* 
* @param isFragile
* The is_fragile
*/
public void setIsFragile(String isFragile) {
this.isFragile = isFragile;
}

/**
* 
* @return
* The isCod
*/
public String getIsCod() {
return isCod;
}

/**
* 
* @param isCod
* The is_cod
*/
public void setIsCod(String isCod) {
this.isCod = isCod;
}

/**
* 
* @return
* The weight
*/
public String getWeight() {
return weight;
}

/**
* 
* @param weight
* The weight
*/
public void setWeight(String weight) {
this.weight = weight;
}

/**
* 
* @return
* The height
*/
public String getHeight() {
return height;
}

/**
* 
* @param height
* The height
*/
public void setHeight(String height) {
this.height = height;
}

/**
* 
* @return
* The length
*/
public String getLength() {
return length;
}

/**
* 
* @param length
* The length
*/
public void setLength(String length) {
this.length = length;
}

/**
* 
* @return
* The width
*/
public String getWidth() {
return width;
}

/**
* 
* @param width
* The width
*/
public void setWidth(String width) {
this.width = width;
}

/**
* 
* @return
* The promoTitle
*/
public String getPromoTitle() {
return promoTitle;
}

/**
* 
* @param promoTitle
* The promo_title
*/
public void setPromoTitle(String promoTitle) {
this.promoTitle = promoTitle;
}

/**
* 
* @return
* The promoSalePricePercentage
*/
public String getPromoSalePricePercentage() {
return promoSalePricePercentage;
}

/**
* 
* @param promoSalePricePercentage
* The promo_sale_price_percentage
*/
public void setPromoSalePricePercentage(String promoSalePricePercentage) {
this.promoSalePricePercentage = promoSalePricePercentage;
}

/**
* 
* @return
* The promoStart
*/
public String getPromoStart() {
return promoStart;
}

/**
* 
* @param promoStart
* The promo_start
*/
public void setPromoStart(String promoStart) {
this.promoStart = promoStart;
}

/**
* 
* @return
* The promoEnd
*/
public String getPromoEnd() {
return promoEnd;
}

/**
* 
* @param promoEnd
* The promo_end
*/
public void setPromoEnd(String promoEnd) {
this.promoEnd = promoEnd;
}

/**
* 
* @return
* The promoType
*/
public String getPromoType() {
return promoType;
}

/**
* 
* @param promoType
* The promo_type
*/
public void setPromoType(String promoType) {
this.promoType = promoType;
}

/**
* 
* @return
* The isSale
*/
public Integer getIsSale() {
return isSale;
}

/**
* 
* @param isSale
* The is_sale
*/
public void setIsSale(Integer isSale) {
this.isSale = isSale;
}

/**
* 
* @return
* The today
*/
public String getToday() {
return today;
}

/**
* 
* @param today
* The today
*/
public void setToday(String today) {
this.today = today;
}

}
