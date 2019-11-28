
package com.rgu.ecommerce.rest;

import com.rgu.ecommerce.dao.SellerQuery;
import com.rgu.ecommerce.model.Seller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nikitkhakholia
 */
@RestController
@RequestMapping("seller/")
public class SellerController {
    @PostMapping("add")
    public boolean addSeller(@RequestBody Seller s){
        return SellerQuery.addSeller(s);
    }
    
    @GetMapping("id/{id}")
    public Seller getSellerById(@PathVariable int id){
        return SellerQuery.selectSellerById(id);
    }
}
