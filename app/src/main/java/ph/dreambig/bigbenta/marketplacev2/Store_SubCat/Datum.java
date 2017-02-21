package ph.dreambig.bigbenta.marketplacev2.Store_SubCat;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Datum {

@SerializedName("categories")
@Expose
private String categories;
@SerializedName("link")
@Expose
private String link;
@SerializedName("category_id")
@Expose
private String categoryId;
@SerializedName("subcategory")
@Expose
private List<Subcategory> subcategory = new ArrayList<Subcategory>();
@SerializedName("permalink")
@Expose
private String permalink;
@SerializedName("count")
@Expose
private Integer count;

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
* The link
*/
public String getLink() {
return link;
}

/**
* 
* @param link
* The link
*/
public void setLink(String link) {
this.link = link;
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
* The subcategory
*/
public List<Subcategory> getSubcategory() {
return subcategory;
}

/**
* 
* @param subcategory
* The subcategory
*/
public void setSubcategory(List<Subcategory> subcategory) {
this.subcategory = subcategory;
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
* The count
*/
public Integer getCount() {
return count;
}

/**
* 
* @param count
* The count
*/
public void setCount(Integer count) {
this.count = count;
}

}