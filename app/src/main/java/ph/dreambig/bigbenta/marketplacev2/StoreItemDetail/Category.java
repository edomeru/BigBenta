package ph.dreambig.bigbenta.marketplacev2.StoreItemDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

@SerializedName("id")
@Expose
private String id;
@SerializedName("category_name")
@Expose
private String categoryName;
@SerializedName("sub_category_name")
@Expose
private String subCategoryName;
@SerializedName("category_id")
@Expose
private String categoryId;
@SerializedName("sub_category_id")
@Expose
private String subCategoryId;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getCategoryName() {
return categoryName;
}

public void setCategoryName(String categoryName) {
this.categoryName = categoryName;
}

public String getSubCategoryName() {
return subCategoryName;
}

public void setSubCategoryName(String subCategoryName) {
this.subCategoryName = subCategoryName;
}

public String getCategoryId() {
return categoryId;
}

public void setCategoryId(String categoryId) {
this.categoryId = categoryId;
}

public String getSubCategoryId() {
return subCategoryId;
}

public void setSubCategoryId(String subCategoryId) {
this.subCategoryId = subCategoryId;
}

}