
package com.rgu.ecommerce.model.config;

/**
 *
 * @author Nikit Khakholia
 */
public enum UserType{
    SELLER(0), BUYER(1), ADMIN(2);
    
    private final int code;
    private UserType(int code){
        this.code = code;
    }
    
    public int getCode(){
        return this.code;
    }
    
    public static UserType valueOf(int code){
        switch(code){
            case 0 : return SELLER;
            case 1 : return BUYER;
            case 2 : return ADMIN;
            default: return BUYER;
        }
    }
}
