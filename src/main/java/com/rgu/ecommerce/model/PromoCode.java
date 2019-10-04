
package com.rgu.ecommerce.model;

import com.rgu.ecommerce.model.config.PromoType;

/**
 *
 * @author Nikit Khakholia
 */
public class PromoCode {
    private String code;
    private PromoType type;
    private Double amount;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PromoType getType() {
        return type;
    }

    public void setType(PromoType type) {
        this.type = type;
    }

    

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    
}
