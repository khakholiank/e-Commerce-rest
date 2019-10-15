/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rgu.ecommerce.rest;

import com.rgu.ecommerce.dao.OrderItemQuery;
import com.rgu.ecommerce.model.OrderItem;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nikit Khakholia
 */
@RestController
@RequestMapping("orderitem/")
public class OrderItemController {
    @PostMapping("add")
    public static boolean add(@RequestBody OrderItem oi){
        return OrderItemQuery.add(oi);
    }
    
    @PutMapping("update")
    public static boolean update(@RequestBody OrderItem oi){
        return OrderItemQuery.update(oi);
    }
    
    @GetMapping("id/{id}")
    public static List<OrderItem> getOrderItemsByOrderId(@PathVariable int id){
        return OrderItemQuery.getOrderItemsById(id);
    }
}