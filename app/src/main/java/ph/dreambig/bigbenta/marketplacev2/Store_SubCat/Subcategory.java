package ph.dreambig.bigbenta.marketplacev2.Store_SubCat;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Subcategory {

@SerializedName("category_id")
@Expose
private String categoryId;
@SerializedName("name")
@Expose
private String name;
@SerializedName("permalink")
@Expose
private String permalink;
@SerializedName("link")
@Expose
private String link;
@SerializedName("count")
@Expose
private Integer count;

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