/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rgu.ecommerce.model.config;

/**
 *
 * @author Nikit Khakholia
 */
public enum UnitType {
    Hrs(0),Min(1),Rs(2), Sec(3);
    
    
    private final int code;
    private UnitType(int code){
        this.code = code;
    }
    
    public int getCode(){
        return this.code;
    }
    
    public static UnitType valueOf(int code){
        switch(code){
            case 0 : return Hrs;
            case 1 : return Min;
            case 2 : return Rs;
            default: return Sec;
        }
    }
}
