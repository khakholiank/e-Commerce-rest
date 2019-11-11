
package com.rgu.ecommerce.model;

import com.rgu.ecommerce.model.config.UnitType;

/**
 *
 * @author Nikit Khakholia
 */
public class CartItem {
    private int userId;
    private Product products;
    
    private Double qty;
    private UnitType unitType;
    
    private User sellerId;
    private Stock rate;
    
    
    

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public User getSellerId() {
        return sellerId;
    }

    public void setSellerId(User seller_id) {
        this.sellerId = seller_id;
    }

    public Stock getRate() {
        return rate;
    }

    public void setRate(Stock rate) {
        this.rate = rate;
    }


    

    
    



}
