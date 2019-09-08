
package com.rgu.ecommerce.model;

/**
 *
 * @author Nikit Khakholia
 */
public class PromoCode {
    private int code;
    private int type;//enum
    private int amount;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
}
