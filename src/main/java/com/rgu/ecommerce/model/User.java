 
package com.rgu.ecommerce.model;

import com.rgu.ecommerce.model.commons.Address;
import com.rgu.ecommerce.model.config.UserType;
import java.time.LocalDate;

/**
 *
 * @author Nikit Khakholia
 */
public class User {
    private int id;
    private String fName;
    private String lNmae;
    private LocalDate birthDate;
    private UserType userType;
    private String occupation;
    private String password;
    private Address defaultAddress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlNmae() {
        return lNmae;
    }

    public void setlNmae(String lNmae) {
        this.lNmae = lNmae;
    }

    

    

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
//
//    public Rating getRating() {
//        return rating;
//    }
//
//    public void setRating(Rating rating) {
//        this.rating = rating;
//    }

    public Address getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Address defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    
    
    
    
    

    
    
    
}
