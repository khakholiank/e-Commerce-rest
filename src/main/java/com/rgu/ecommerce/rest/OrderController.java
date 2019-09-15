
package com.rgu.ecommerce.rest;

import com.rgu.ecommerce.dao.OrderQuery;
import com.rgu.ecommerce.model.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nikit Khakholia
 */
@RestController
@RequestMapping("order/")
public class OrderController {
    @PostMapping("add")
    public static boolean add(@RequestBody Order o){
        return OrderQuery.
    }
}
