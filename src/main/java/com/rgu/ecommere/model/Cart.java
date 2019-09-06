
package com.rgu.ecommere.model;

import com.rgu.ecommere.commons.Address;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Nikit Khakholia
 */
public class Cart {
    private int id;
//    private LocalDateTime estDeliveryTime; 
    private List<Product> products;
    private Address deiveryAddress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public LocalDateTime getEstDeliveryTime() {
//        return estDeliveryTime;
//    }
//
//    public void setEstDeliveryTime(LocalDateTime estDeliveryTime) {
//        this.estDeliveryTime = estDeliveryTime;
//    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Address getDeiveryAddress() {
        return deiveryAddress;
    }

    public void setDeiveryAddress(Address deiveryAddress) {
        this.deiveryAddress = deiveryAddress;
    }
    
    
    
}
