
package com.rgu.ecommerce.model;

import com.rgu.ecommerce.model.commons.Address;
import com.rgu.ecommerce.model.config.OrderStatus;
import java.time.LocalDateTime;

/**
 *
 * @author Nikit Khakholia
 */
public class Order {
    private int id;
//    private int sellerId;
    private int custId;
    private LocalDateTime timeOfOrder;
//    private Address deliveryAddress;
    private Address billingAddress;
//    private Product product;
//    private int qty;
//    private String trackingId;
//    private LocalDateTime estDeliveryTime;
    private String promoCode;
    private double grossAmount;
    private double discApplied;
    private double deliveryFee;
    private double netAmount;
    private OrderStatus orderStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public LocalDateTime getTimeOfOrder() {
        return timeOfOrder;
    }

    public void setTimeOfOrder(LocalDateTime timeOfOrder) {
        this.timeOfOrder = timeOfOrder;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
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

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }


    
}
