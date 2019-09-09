/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rgu.ecommerce.config;

/**
 *
 * @author Nikit Khakholia
 */
public enum OrderStatus {
    ORDERED(0),SHIPPED(2),DELIVERED(3),PROCESSED(4),PACKED(5);
    
    private final int code;
    private OrderStatus(int code){
        this.code = code;
    }
    
    public int getCode(){
        return this.code;
    }
    
    public static OrderStatus valueOf(int code){
        switch(code){
            case 0 : return ORDERED;
            case 1 : return SHIPPED;
            case 2 : return DELIVERED;
            default: return PROCESSED;
        }
    }
}
