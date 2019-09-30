package com.rgu.ecommerce.model;

import com.rgu.ecommerce.commons.Address;
import java.time.LocalDateTime;

/**
 *
 * @author Nikit Khakholia
 */
public class OrderItem {

    private int orderId;
    private int sellerId;
    private Address deliveryAddress;
    private Product product;
    private int qty;
    private String trackingId;
    private LocalDateTime estDeliveryTime;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    
    
    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public LocalDateTime getEstDeliveryTime() {
        return estDeliveryTime;
    }

    public void setEstDeliveryTime(LocalDateTime estDeliveryTime) {
        this.estDeliveryTime = estDeliveryTime;
    }
    
    
    
}
