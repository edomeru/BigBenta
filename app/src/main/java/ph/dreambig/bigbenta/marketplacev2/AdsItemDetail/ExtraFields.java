package ph.dreambig.bigbenta.marketplacev2.AdsItemDetail;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ExtraFields {

@SerializedName("developer")
@Expose
private List<Developer> developer = new ArrayList<Developer>();
@SerializedName("roi_period")
@Expose
private List<Object> roiPeriod = new ArrayList<Object>();
@SerializedName("service_year")
@Expose
private List<Object> serviceYear = new ArrayList<Object>();
@SerializedName("sell_reason")
@Expose
private List<Object> sellReason = new ArrayList<Object>();
@SerializedName("business_type")
@Expose
private List<Object> businessType = new ArrayList<Object>();
@SerializedName("mileage")
@Expose
private List<Object> mileage = new ArrayList<Object>();
@SerializedName("fuel_type")
@Expose
private List<Object> fuelType = new ArrayList<Object>();
@SerializedName("transmission")
@Expose
private List<Object> transmission = new ArrayList<Object>();
@SerializedName("brand")
@Expose
private List<Object> brand = new ArrayList<Object>();
@SerializedName("condition")
@Expose
private List<Condition> condition = new ArrayList<Condition>();

/**
* 
* @return
* The developer
*/
public List<Developer> getDeveloper() {
return developer;
}

/**
* 
* @param developer
* The developer
*/
public void setDeveloper(List<Developer> developer) {
this.developer = developer;
}

/**
* 
* @return
* The roiPeriod
*/
public List<Object> getRoiPeriod() {
return roiPeriod;
}

/**
* 
* @param roiPeriod
* The roi_period
*/
public void setRoiPeriod(List<Object> roiPeriod) {
this.roiPeriod = roiPeriod;
}

/**
* 
* @return
* The serviceYear
*/
public List<Object> getServiceYear() {
return serviceYear;
}

/**
* 
* @param serviceYear
* The service_year
*/
public void setServiceYear(List<Object> serviceYear) {
this.serviceYear = serviceYear;
}

/**
* 
* @return
* The sellReason
*/
public List<Object> getSellReason() {
return sellReason;
}

/**
* 
* @param sellReason
* The sell_reason
*/
public void setSellReason(List<Object> sellReason) {
this.sellReason = sellReason;
}

/**
* 
* @return
* The businessType
*/
public List<Object> getBusinessType() {
return businessType;
}

/**
* 
* @param businessType
* The business_type
*/
public void setBusinessType(List<Object> businessType) {
this.businessType = businessType;
}

/**
* 
* @return
* The mileage
*/
public List<Object> getMileage() {
return mileage;
}

/**
* 
* @param mileage
* The mileage
*/
public void setMileage(List<Object> mileage) {
this.mileage = mileage;
}

/**
* 
* @return
* The fuelType
*/
public List<Object> getFuelType() {
return fuelType;
}

/**
* 
* @param fuelType
* The fuel_type
*/
public void setFuelType(List<Object> fuelType) {
this.fuelType = fuelType;
}

/**
* 
* @return
* The transmission
*/
public List<Object> getTransmission() {
return transmission;
}

/**
* 
* @param transmission
* The transmission
*/
public void setTransmission(List<Object> transmission) {
this.transmission = transmission;
}

/**
* 
* @return
* The brand
*/
public List<Object> getBrand() {
return brand;
}

/**
* 
* @param brand
* The brand
*/
public void setBrand(List<Object> brand) {
this.brand = brand;
}

/**
* 
* @return
* The condition
*/
public List<Condition> getCondition() {
return condition;
}

/**
* 
* @param condition
* The condition
*/
public void setCondition(List<Condition> condition) {
this.condition = condition;
}

}