package ph.dreambig.bigbenta.marketplacev2.Search.Search_ALL.SearchStore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

@SerializedName("form_id")
@Expose
private String formId;
@SerializedName("image_sm")
@Expose
private String imageSm;
@SerializedName("image_md")
@Expose
private String imageMd;
@SerializedName("is_primary")
@Expose
private String isPrimary;

public String getFormId() {
return formId;
}

public void setFormId(String formId) {
this.formId = formId;
}

public String getImageSm() {
return imageSm;
}

public void setImageSm(String imageSm) {
this.imageSm = imageSm;
}

public String getImageMd() {
return imageMd;
}

public void setImageMd(String imageMd) {
this.imageMd = imageMd;
}

public String getIsPrimary() {
return isPrimary;
}

public void setIsPrimary(String isPrimary) {
this.isPrimary = isPrimary;
}

}