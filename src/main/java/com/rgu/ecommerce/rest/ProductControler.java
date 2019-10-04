
package com.rgu.ecommerce.rest;

import com.rgu.ecommerce.dao.ProductQuery;
import com.rgu.ecommerce.model.Product;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("product/")
public class ProductControler {
    @PostMapping("add")
    public static boolean add(@RequestBody Product p){
        return ProductQuery.add(p);
    }
    
    @PutMapping("update")
    public static boolean update(@RequestBody Product p){
        return ProductQuery.update(p);
    }
    
    @DeleteMapping("delete/{id}")
    public static boolean delete(@PathVariable int id){
        return ProductQuery.delete(id);
    }
    
    @GetMapping("all")
    public static List<Product> selectAll(){
        return ProductQuery.selectAllProducts();
    }
    
    @GetMapping("id/{id}")
    public static Product selectById(@PathVariable int id){
        return ProductQuery.selectProductById(id);
    }
}
