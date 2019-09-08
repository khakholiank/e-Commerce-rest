
package com.rgu.ecommerce.model;

import com.rgu.ecommerce.commons.Address;
import com.rgu.ecommerce.commons.Name;
import com.rgu.ecommerce.config.UserType;
import java.time.LocalDate;

/**
 *
 * @author Nikit Khakholia
 */
public class User {
    private int id;
    private Name name;
    private LocalDate birthDate;
    private UserType userType;
    private String occupation;
    private String password;
    private Address defaultAddress;
//    private Rating rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name.getFirstName()+" "+name.getLastName();
    }

    public void setName(String name) {
        this.name.setFirstName(name.split(name, 1)+"");
        this.name.setLastName(name.split(name, 2)+"");
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
