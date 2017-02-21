package ph.dreambig.bigbenta.marketplacev2.AdsSubCat;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Datum {

@SerializedName("category_id")
@Expose
private String categoryId;
@SerializedName("name")
@Expose
private String name;
@SerializedName("subcategory")
@Expose
private List<Object> subcategory = new ArrayList<Object>();
@SerializedName("categories")
@Expose
private String categories;
@SerializedName("permalink")
@Expose
private String permalink;
@SerializedName("category_permalink")
@Expose
private String categoryPermalink;

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
* The subcategory
*/
public List<Object> getSubcategory() {
return subcategory;
}

/**
* 
* @param subcategory
* The subcategory
*/
public void setSubcategory(List<Object> subcategory) {
this.subcategory = subcategory;
}

/**
* 
* @return
* The categories
*/
public String getCategories() {
return categories;
}

/**
* 
* @param categories
* The categories
*/
public void setCategories(String categories) {
this.categories = categories;
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
* The categoryPermalink
*/
public String getCategoryPermalink() {
return categoryPermalink;
}

/**
* 
* @param categoryPermalink
* The category_permalink
*/
public void setCategoryPermalink(String categoryPermalink) {
this.categoryPermalink = categoryPermalink;
}

}