package ph.dreambig.bigbenta.marketplacev2.StoreItemDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Variant {

@SerializedName("id")
@Expose
private String id;
@SerializedName("form_id")
@Expose
private String formId;
@SerializedName("variant_name")
@Expose
private String variantName;
@SerializedName("choices")
@Expose
private String choices;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getFormId() {
return formId;
}

public void setFormId(String formId) {
this.formId = formId;
}

public String getVariantName() {
return variantName;
}

public void setVariantName(String variantName) {
this.variantName = variantName;
}

public String getChoices() {
return choices;
}

public void setChoices(String choices) {
this.choices = choices;
}

}