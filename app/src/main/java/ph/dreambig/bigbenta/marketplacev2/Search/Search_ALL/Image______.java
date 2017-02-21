package ph.dreambig.bigbenta.marketplacev2.Search.Search_ALL;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Image______ {

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

/**
* 
* @return
* The formId
*/
public String getFormId() {
return formId;
}

/**
* 
* @param formId
* The form_id
*/
public void setFormId(String formId) {
this.formId = formId;
}

/**
* 
* @return
* The imageSm
*/
public String getImageSm() {
return imageSm;
}

/**
* 
* @param imageSm
* The image_sm
*/
public void setImageSm(String imageSm) {
this.imageSm = imageSm;
}

/**
* 
* @return
* The imageMd
*/
public String getImageMd() {
return imageMd;
}

/**
* 
* @param imageMd
* The image_md
*/
public void setImageMd(String imageMd) {
this.imageMd = imageMd;
}

/**
* 
* @return
* The isPrimary
*/
public String getIsPrimary() {
return isPrimary;
}

/**
* 
* @param isPrimary
* The is_primary
*/
public void setIsPrimary(String isPrimary) {
this.isPrimary = isPrimary;
}

}