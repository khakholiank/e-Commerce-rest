
package com.rgu.ecommerce.rest;

import com.rgu.ecommerce.dao.PromoCodeQuery;
import com.rgu.ecommerce.model.PromoCode;
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
@RequestMapping("promocode/")
public class PromoCodeController {
    @PostMapping("add")
    public static boolean add(@RequestBody PromoCode pc){
        return PromoCodeQuery.add(pc);
    }
    
    @PutMapping("update")
    public static boolean update(@RequestBody PromoCode pc){
        return PromoCodeQuery.update(pc);
    }
    
    @DeleteMapping("delete/{code}")
    public static boolean delete(@PathVariable String code){
        return PromoCodeQuery.delete(code);
    }
    
    @GetMapping("all")
    public static List<PromoCode> selectAll(){
        return PromoCodeQuery.selectAll();
    }
    
    @GetMapping("id/{code}")
    public static PromoCode selectById(@PathVariable String code){
        return PromoCodeQuery.selectById(code);
    }
}
