package com.ideas2it.employee.model;

import java.util.Set;
import com.ideas2it.employee.model.Employee;
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
    
    public Address(String streetName, String areaName, String cityName, String pinCode) {
		super();
		this.streetName = streetName;
		this.areaName = areaName;
		this.cityName = cityName;
		this.pinCode = pinCode;
	}
    
   public int getAddressId() {
        return addressId;
    }

    public String getStreetName() {
        return streetName;
    } 
    
    public Employee getEmployee() {
        return employee;
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
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }  
   
    public String toString() {
        return (getAddressId() + "\t " + getStreetName() + "\t" + getAreaName() + "\t"
               + getCityName() + "\t" + getPinCode() + "\t");
    }
}