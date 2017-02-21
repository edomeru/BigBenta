package ph.dreambig.bigbenta.marketplacev2.AdsItemsFrag;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Image_ {

@SerializedName("item_image_id")
@Expose
private String itemImageId;
@SerializedName("image_id")
@Expose
private String imageId;
@SerializedName("is_primary")
@Expose
private String isPrimary;
@SerializedName("image_sm")
@Expose
private String imageSm;
@SerializedName("image_md")
@Expose
private String imageMd;
@SerializedName("image_smd")
@Expose
private String imageSmd;
@SerializedName("image_lg")
@Expose
private String imageLg;
@SerializedName("image_orig")
@Expose
private String imageOrig;

/**
* 
* @return
* The itemImageId
*/
public String getItemImageId() {
return itemImageId;
}

/**
* 
* @param itemImageId
* The item_image_id
*/
public void setItemImageId(String itemImageId) {
this.itemImageId = itemImageId;
}

/**
* 
* @return
* The imageId
*/
public String getImageId() {
return imageId;
}

/**
* 
* @param imageId
* The image_id
*/
public void setImageId(String imageId) {
this.imageId = imageId;
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
* The imageSmd
*/
public String getImageSmd() {
return imageSmd;
}

/**
* 
* @param imageSmd
* The image_smd
*/
public void setImageSmd(String imageSmd) {
this.imageSmd = imageSmd;
}

/**
* 
* @return
* The imageLg
*/
public String getImageLg() {
return imageLg;
}

/**
* 
* @param imageLg
* The image_lg
*/
public void setImageLg(String imageLg) {
this.imageLg = imageLg;
}

/**
* 
* @return
* The imageOrig
*/
public String getImageOrig() {
return imageOrig;
}

/**
* 
* @param imageOrig
* The image_orig
*/
public void setImageOrig(String imageOrig) {
this.imageOrig = imageOrig;
}

}