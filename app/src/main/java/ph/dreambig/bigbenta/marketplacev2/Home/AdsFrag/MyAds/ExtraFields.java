package ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.MyAds;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ExtraFields {

@SerializedName("developer")
@Expose
private List<Object> developer = new ArrayList<Object>();
@SerializedName("roi_period")
@Expose
private List<RoiPeriod> roiPeriod = new ArrayList<RoiPeriod>();
@SerializedName("service_year")
@Expose
private List<ServiceYear> serviceYear = new ArrayList<ServiceYear>();
@SerializedName("sell_reason")
@Expose
private List<SellReason> sellReason = new ArrayList<SellReason>();
@SerializedName("business_type")
@Expose
private List<BusinessType> businessType = new ArrayList<BusinessType>();
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
private List<Object> condition = new ArrayList<Object>();

/**
* 
* @return
* The developer
*/
public List<Object> getDeveloper() {
return developer;
}

/**
* 
* @param developer
* The developer
*/
public void setDeveloper(List<Object> developer) {
this.developer = developer;
}

/**
* 
* @return
* The roiPeriod
*/
public List<RoiPeriod> getRoiPeriod() {
return roiPeriod;
}

/**
* 
* @param roiPeriod
* The roi_period
*/
public void setRoiPeriod(List<RoiPeriod> roiPeriod) {
this.roiPeriod = roiPeriod;
}

/**
* 
* @return
* The serviceYear
*/
public List<ServiceYear> getServiceYear() {
return serviceYear;
}

/**
* 
* @param serviceYear
* The service_year
*/
public void setServiceYear(List<ServiceYear> serviceYear) {
this.serviceYear = serviceYear;
}

/**
* 
* @return
* The sellReason
*/
public List<SellReason> getSellReason() {
return sellReason;
}

/**
* 
* @param sellReason
* The sell_reason
*/
public void setSellReason(List<SellReason> sellReason) {
this.sellReason = sellReason;
}

/**
* 
* @return
* The businessType
*/
public List<BusinessType> getBusinessType() {
return businessType;
}

/**
* 
* @param businessType
* The business_type
*/
public void setBusinessType(List<BusinessType> businessType) {
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
public List<Object> getCondition() {
return condition;
}

/**
* 
* @param condition
* The condition
*/
public void setCondition(List<Object> condition) {
this.condition = condition;
}

}