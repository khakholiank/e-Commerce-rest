
package com.rgu.ecommerce.model;

import com.rgu.ecommerce.model.commons.Address;
import java.time.LocalDate;

/**
 *
 * @author nikitkhakholia
 */
public class Seller {
    private int id;
    private String businessName;
    private String sellerName;
    private LocalDate birthDate;
    private String password;
    private Address address;
    private String GSTIN;
    private String PAN;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getGSTIN() {
        return GSTIN;
    }

    public void setGSTIN(String GSTIN) {
        this.GSTIN = GSTIN;
    }

    public String getPAN() {
        return PAN;
    }

    public void setPAN(String PAN) {
        this.PAN = PAN;
    }
    
    
}
