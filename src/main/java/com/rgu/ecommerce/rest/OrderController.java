
package com.rgu.ecommerce.rest;

import com.rgu.ecommerce.dao.OrderQuery;
import com.rgu.ecommerce.model.Order;
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
@RequestMapping("/order")
public class OrderController {
    @PostMapping("add")
    public static boolean add(@RequestBody Order o){
        return OrderQuery.add(o);
    }
    @PutMapping("update")
    public static boolean update(@RequestBody Order o){
        return OrderQuery.update(o);
    }
    @DeleteMapping("delete/{id}")
    public static boolean delete(@PathVariable int id){
        return OrderQuery.delete(id);
    }
    @GetMapping("user/{id}")
    public static List<Order> selectByCustId(@PathVariable int id){
        return OrderQuery.getOrderByCustId(id);
    }
    @GetMapping("seller/{id}")
    public static List<Order> selectOrderBySelerId(@PathVariable int id){
        return OrderQuery.getOrderBySellerId(id);
    }
    @GetMapping("id/{id}")
    public static List<Order> selectOrderById(@PathVariable int id){
        return OrderQuery.getOrderById(id);
    }
    @GetMapping("/all")
    public static List<Order> selectAllOrders(){
        return OrderQuery.getAllOrders();
    }
}
