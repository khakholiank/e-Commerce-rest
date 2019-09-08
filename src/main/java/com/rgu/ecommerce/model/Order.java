
package com.rgu.ecommerce.model;

import com.rgu.ecommere.commons.Address;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Nikit Khakholia
 */
public class Order {
    private int id;
    private int sellerId;
    private int custId;
    private LocalDateTime timeOfOrder;
    private Address deliveryAddress;
    private Address billingAddress;
    private List<Product> listOfProducts;
    private List listOfOty;
    private String trackingId;
    private LocalDateTime estDeliveryTime;
    private String promoCode;
    private double grossAmount;
    private double discApplied;
    private double deliveryFee;
    private double netAmount;
    private String orderStatus;

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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
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

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public List<Product> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(List<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public List getListOfOty() {
        return listOfOty;
    }

    public void setListOfOty(List listOfOty) {
        this.listOfOty = listOfOty;
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
