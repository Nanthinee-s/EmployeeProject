package com.ideas2it.util;

import java.util.regex.Pattern;

public class Util {
 
    /** 
     * Method is used to check the Mobile Number pattern 
     * is must be present in the given format or not 
     * @param it take a mobile number in string type
     * @return boolean value true and false
     */   
    public static boolean validateNumber(String phone) {
        String regex = "^[6-9]{1}[0-9]{9}$";
        return phone.matches(regex);
    }
    
    /**
     * This boolean method is used to check the pincode pattern 
     * is must be present in the given format or not
     * @return this method return boolean value true and false
     */  
    public static boolean validatePinCode(String pinCode) {
        String pattern = "^[0-9]{6}$";
        return pinCode.matches(pattern);
    }

    /**
     * This method is used to validate the Email_id 
     * is must be present in the following format or not 
     * @ return boolean value if its  valid it return true or else return false
     */     
    public static boolean isEmailIdValid(String emailId) {
        String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*"
                         +"@[A-za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return emailId.matches(pattern);
    }
    
    /**
     * This Method is used to validate the show time 
     * is must be present in the given format or not
     * @ return the boolean value for the given input
     */
    public static boolean isValidShowTime(String showTime) {
        String pattern =   "(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)";
        return showTime.matches(pattern);
    }
}
