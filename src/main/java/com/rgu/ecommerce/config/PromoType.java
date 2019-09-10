
package com.rgu.ecommerce.config;

/**
 *
 * @author Nikit Khakholia
 */
public enum PromoType {
    Rupees(0),Percentage(1);
    
    private final int code;
    private PromoType(int code){
        this.code = code;
    }
    
    public int getCode(){
        return this.code;
    }
    
    public static PromoType valueOf(int code){
        switch(code){
            case 0 : return Rupees;
            case 1 : return Percentage;
            
            default: return Rupees;
        }
    }
}
