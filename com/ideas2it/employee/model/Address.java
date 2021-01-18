package com.ideas2it.employee.model;

/**
 * this POJO class is to set the user address in Address type
 * and also gets the address in Address type 
 */

public class Address {
  
    private String streetName;
    private String areaName;
    private String cityName;
    private String pinCode;
    private int addressId;
    private Employee employee;
 
    /* using getter and setter method */
    
    public Address() { }
    
    public int getAddressId() {
        return addressId;
    }

    public Employee getEmployee() {
        return employee;
    }
    
    public String getStreetName() {
        return streetName;
    }  
   
    public String getAreaName() {
        return areaName;
    }
    
    public String getCityName() {
        return cityName;
    }
    
    public String getPinCode() {
        return pinCode;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;    
    } 
    
   public void setAddressId(int addressId) {
        this.addressId = addressId;    
    } 
       
    public String toString() {
        return (getAddressId() + "\t " + getStreetName() + "\t" + getAreaName() + "\t"
               + getCityName() + "\t" + getPinCode() + "\t");
    }
}
