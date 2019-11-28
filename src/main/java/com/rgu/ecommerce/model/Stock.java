
package com.rgu.ecommerce.model;

import com.rgu.ecommerce.model.config.UnitType;

/**
 *
 * @author Nikit Khakholia
 */
public class Stock {
    private int hubId;
    private Product productId;
    private Seller sellerId;
    private int qty;
    private Double rate;
    private UnitType units;
    private Rating ratingId;

    public int getHubId() {
        return hubId;
    }

    public void setHubId(int hubId) {
        this.hubId = hubId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Seller getSellerId() {
        return sellerId;
    }

    public void setSellerId(Seller sellerId) {
        this.sellerId = sellerId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public UnitType getUnits() {
        return units;
    }

    public void setUnits(UnitType units) {
        this.units = units;
    }

    public Rating getRatingId() {
        return ratingId;
    }

    public void setRatingId(Rating ratingId) {
        this.ratingId = ratingId;
    }


    
}
