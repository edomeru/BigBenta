package ph.dreambig.bigbenta.marketplacev2.Search.Search_ALL.SearchStore;

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
private String location;
@SerializedName("about_seller")
@Expose
private String aboutSeller;
@SerializedName("image")
@Expose
private List<Image> image = null;
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
@SerializedName("is_paymaya")
@Expose
private String isPaymaya;
@SerializedName("is_free_shipping")
@Expose
private String isFreeShipping;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getPermalink() {
return permalink;
}

public void setPermalink(String permalink) {
this.permalink = permalink;
}

public String getPrice() {
return price;
}

public void setPrice(String price) {
this.price = price;
}

public String getOriginalPrice() {
return originalPrice;
}

public void setOriginalPrice(String originalPrice) {
this.originalPrice = originalPrice;
}

public String getQuantity() {
return quantity;
}

public void setQuantity(String quantity) {
this.quantity = quantity;
}

public String getShortDescription() {
return shortDescription;
}

public void setShortDescription(String shortDescription) {
this.shortDescription = shortDescription;
}

public String getLongDescription() {
return longDescription;
}

public void setLongDescription(String longDescription) {
this.longDescription = longDescription;
}

public String getStoreCategoryId() {
return storeCategoryId;
}

public void setStoreCategoryId(String storeCategoryId) {
this.storeCategoryId = storeCategoryId;
}

public String getLocation() {
return location;
}

public void setLocation(String location) {
this.location = location;
}

public String getAboutSeller() {
return aboutSeller;
}

public void setAboutSeller(String aboutSeller) {
this.aboutSeller = aboutSeller;
}

public List<Image> getImage() {
return image;
}

public void setImage(List<Image> image) {
this.image = image;
}

public String getVariants() {
return variants;
}

public void setVariants(String variants) {
this.variants = variants;
}

public String getCreatedDate() {
return createdDate;
}

public void setCreatedDate(String createdDate) {
this.createdDate = createdDate;
}

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getCategory() {
return category;
}

public void setCategory(String category) {
this.category = category;
}

public String getVariantId() {
return variantId;
}

public void setVariantId(String variantId) {
this.variantId = variantId;
}

public String getMinimumOrder() {
return minimumOrder;
}

public void setMinimumOrder(String minimumOrder) {
this.minimumOrder = minimumOrder;
}

public String getStoreId() {
return storeId;
}

public void setStoreId(String storeId) {
this.storeId = storeId;
}

public String getStoreName() {
return storeName;
}

public void setStoreName(String storeName) {
this.storeName = storeName;
}

public String getStorePage() {
return storePage;
}

public void setStorePage(String storePage) {
this.storePage = storePage;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getCategoryName() {
return categoryName;
}

public void setCategoryName(String categoryName) {
this.categoryName = categoryName;
}

public String getSearchName() {
return searchName;
}

public void setSearchName(String searchName) {
this.searchName = searchName;
}

public String getCategoryNamePermalink() {
return categoryNamePermalink;
}

public void setCategoryNamePermalink(String categoryNamePermalink) {
this.categoryNamePermalink = categoryNamePermalink;
}

public String getIsFragile() {
return isFragile;
}

public void setIsFragile(String isFragile) {
this.isFragile = isFragile;
}

public String getIsCod() {
return isCod;
}

public void setIsCod(String isCod) {
this.isCod = isCod;
}

public String getWeight() {
return weight;
}

public void setWeight(String weight) {
this.weight = weight;
}

public String getHeight() {
return height;
}

public void setHeight(String height) {
this.height = height;
}

public String getLength() {
return length;
}

public void setLength(String length) {
this.length = length;
}

public String getWidth() {
return width;
}

public void setWidth(String width) {
this.width = width;
}

public String getPromoTitle() {
return promoTitle;
}

public void setPromoTitle(String promoTitle) {
this.promoTitle = promoTitle;
}

public String getPromoSalePricePercentage() {
return promoSalePricePercentage;
}

public void setPromoSalePricePercentage(String promoSalePricePercentage) {
this.promoSalePricePercentage = promoSalePricePercentage;
}

public String getPromoStart() {
return promoStart;
}

public void setPromoStart(String promoStart) {
this.promoStart = promoStart;
}

public String getPromoEnd() {
return promoEnd;
}

public void setPromoEnd(String promoEnd) {
this.promoEnd = promoEnd;
}

public String getPromoType() {
return promoType;
}

public void setPromoType(String promoType) {
this.promoType = promoType;
}

public Integer getIsSale() {
return isSale;
}

public void setIsSale(Integer isSale) {
this.isSale = isSale;
}

public String getIsPaymaya() {
return isPaymaya;
}

public void setIsPaymaya(String isPaymaya) {
this.isPaymaya = isPaymaya;
}

public String getIsFreeShipping() {
return isFreeShipping;
}

public void setIsFreeShipping(String isFreeShipping) {
this.isFreeShipping = isFreeShipping;
}

}