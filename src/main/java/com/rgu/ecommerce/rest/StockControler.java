
package com.rgu.ecommerce.rest;

import com.rgu.ecommerce.dao.StockQuery;
import com.rgu.ecommerce.model.Stock;
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
@RequestMapping("stock/")
public class StockControler {
    @PostMapping("add")
    public static boolean add(@RequestBody Stock s){
        return StockQuery.add(s);
    }
    
    @PutMapping("update")
    public static boolean update(@RequestBody Stock s){
        return StockQuery.update(s);
    }
    
    @DeleteMapping("delete/{pid,sid}")
    public static boolean delete(@PathVariable int pid, @PathVariable int sid){
        return StockQuery.delete(pid, sid);
    }
    
    @GetMapping("all")
    public static List<Stock> selectAll(){
        return StockQuery.selectAll();
    }
    
    @GetMapping("id/{id}")
    public static List<Stock> selectByProductId(@PathVariable int id){
        return StockQuery.selectByProduct(id);
    }
    
    @GetMapping("seller/{id}")
    public static List<Stock> selectStockBySeller(@PathVariable int id){
        return StockQuery.selectStockBySellerId(id);
    }
}
