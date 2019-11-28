
package com.rgu.ecommerce.model;

import com.rgu.ecommerce.model.commons.Address;
import com.rgu.ecommerce.model.config.OrderStatus;
import java.util.List;

/**
 *
 * @author Nikit Khakholia
 */
public class Order {
    
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    private User user;
    private Address bAddress;
    private String promoCode;
    private double grossAmount;
    private double discApplied;
    private double deliveryFee;
    private List<OrderItem> orderItems;
    private OrderStatus orderStatus;
    private double netAmt;

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getNetAmt() {
        return netAmt;
    }

    public void setNetAmt(double netAmt) {
        this.netAmt = netAmt;
    }
    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getbAddress() {
        return bAddress;
    }

    public void setbAddress(Address bAddress) {
        this.bAddress = bAddress;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(double grossAmount) {
        this.grossAmount = grossAmount;
    }

    public double getDiscApplied() {
        return discApplied;
    }

    public void setDiscApplied(double discApplied) {
        this.discApplied = discApplied;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
