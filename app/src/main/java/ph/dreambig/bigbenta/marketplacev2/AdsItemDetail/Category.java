package ph.dreambig.bigbenta.marketplacev2.AdsItemDetail;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Category {

@SerializedName("item_category_id")
@Expose
private String itemCategoryId;
@SerializedName("name")
@Expose
private String name;
@SerializedName("category_id")
@Expose
private String categoryId;
@SerializedName("parent_category_id")
@Expose
private String parentCategoryId;
@SerializedName("category")
@Expose
private String category;
@SerializedName("category_name")
@Expose
private String categoryName;
@SerializedName("con")
@Expose
private Object con;

/**
* 
* @return
* The itemCategoryId
*/
public String getItemCategoryId() {
return itemCategoryId;
}

/**
* 
* @param itemCategoryId
* The item_category_id
*/
public void setItemCategoryId(String itemCategoryId) {
this.itemCategoryId = itemCategoryId;
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

/**
* 
* @return
* The categoryId
*/
public String getCategoryId() {
return categoryId;
}

/**
* 
* @param categoryId
* The category_id
*/
public void setCategoryId(String categoryId) {
this.categoryId = categoryId;
}

/**
* 
* @return
* The parentCategoryId
*/
public String getParentCategoryId() {
return parentCategoryId;
}

/**
* 
* @param parentCategoryId
* The parent_category_id
*/
public void setParentCategoryId(String parentCategoryId) {
this.parentCategoryId = parentCategoryId;
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
* The con
*/
public Object getCon() {
return con;
}

/**
* 
* @param con
* The con
*/
public void setCon(Object con) {
this.con = con;
}

}