
package com.rgu.ecommerce.model;

import com.rgu.ecommerce.model.config.UnitType;

/**
 *
 * @author Nikit Khakholia
 */
public class CartItem {
    private User userId;
    private Product productId;
    private Double qty;
    private UnitType unitType;
    private Seller sellerId;
    private Double rate;

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
    

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
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

    public Seller getSellerId() {
        return sellerId;
    }

    public void setSellerId(Seller sellerId) {
        this.sellerId = sellerId;
    }

    
}
