
package com.rgu.ecommerce.model;

import com.rgu.ecommerce.model.config.UnitType;

/**
 *
 * @author Nikit Khakholia
 */
public class DeliveryTime {
    private int fromPincode;
    private int toPincode;
    private Double timeRequired;
    private Double fee;
    private UnitType units;


    public int getFromPincode() {
        return fromPincode;
    }

    public void setFromPincode(int fromPincode) {
        this.fromPincode = fromPincode;
    }

    public int getToPincode() {
        return toPincode;
    }

    public void setToPincode(int toPincode) {
        this.toPincode = toPincode;
    }

    public Double getTimeRequired() {
        return timeRequired;
    }

    public void setTimeRequired(Double timeRequired) {
        this.timeRequired = timeRequired;
    }

    public UnitType getUnits() {
        return units;
    }

    public void setUnits(UnitType units) {
        this.units = units;
    }



    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }
    
    

    
}
