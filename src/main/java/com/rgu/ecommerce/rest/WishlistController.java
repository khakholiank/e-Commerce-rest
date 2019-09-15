
package com.rgu.ecommerce.rest;

import com.rgu.ecommerce.dao.WishlistQuery;
import com.rgu.ecommerce.model.Wishlist;
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
@RequestMapping("wishlist/")
public class WishlistController {
    @PostMapping("add")
    public static boolean add(@RequestBody Wishlist wl){
        return WishlistQuery.add(wl);
    }
    
    @DeleteMapping("delete")
    public static boolean delete(@RequestBody Wishlist wl){
        return WishlistQuery.delete(wl);
    }
    
    @GetMapping("id/{id}")
    public static List<Wishlist> selectById(@PathVariable int id){
        return WishlistQuery.selectWishlistById(id);
    }
}

