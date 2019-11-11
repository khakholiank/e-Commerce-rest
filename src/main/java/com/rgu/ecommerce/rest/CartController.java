/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rgu.ecommerce.rest;

import com.rgu.ecommerce.dao.CartItemQuery;
import com.rgu.ecommerce.model.CartItem;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nikit Khakholia
 */
@RestController
@RequestMapping("cart/")
public class CartController {
    @PostMapping("add")
    public boolean add(@RequestBody CartItem c){
        return CartItemQuery.add(c);
    }
    
    @DeleteMapping("delete/{uid,pid}")
    public boolean delete(@PathVariable int uid, int pid){
        return CartItemQuery.delete(uid, pid);
    }
    
    @GetMapping("all/{uid}")
    public List<CartItem> selectAllByUserId(@PathVariable int uid){
        return CartItemQuery.selectById(uid);
    }
}
