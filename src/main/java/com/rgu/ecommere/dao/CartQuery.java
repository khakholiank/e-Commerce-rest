
package com.rgu.ecommere.dao;

/**
 *
 * @author Nikit Khakholia
 */
public class CartQuery {
    private final String ADD = "INSERT INTO cart(id, products, deliveryAddress) VALUES(?,?,?)";
    
    private final String UPDATE = "UPDATE cart SET products=?, deliveryAddress=? WHERE id=?";
    
    private final String SELECT_BY_ID = "SELECT FROM cart WHERE id=?";
    
    public boolean add(){
        
    }
    
    
}
