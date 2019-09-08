
package com.rgu.ecommerce.model;

/**
 *
 * @author Nikit Khakholia
 */
public class Stock {
    private int hubId;
    private int productId;
    private int sellerId;
    private int qty;
    private int rate;
    private int units;
    private Rating rateing;

    public int getHubId() {
        return hubId;
    }

    public void setHubId(int hubId) {
        this.hubId = hubId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public Rating getRateing() {
        return rateing;
    }

    public void setRateing(Rating rateing) {
        this.rateing = rateing;
    }
    
    
}
