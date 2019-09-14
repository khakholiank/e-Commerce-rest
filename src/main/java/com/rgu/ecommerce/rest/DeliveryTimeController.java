
package com.rgu.ecommerce.rest;

import com.rgu.ecommerce.dao.DeliveryTimeQuery;
import com.rgu.ecommerce.model.DeliveryTime;
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
@RequestMapping("deliverytime/")
public class DeliveryTimeController {
    @PostMapping("add")
    public boolean add(@RequestBody DeliveryTime dt){
        return DeliveryTimeQuery.add(dt);
    }
    
    @PutMapping("update")
    public boolean update(@RequestBody DeliveryTime dt){
        return DeliveryTimeQuery.update(dt);
    }
    
    @DeleteMapping("delete/{fid,tid}")
    public boolean delete(@PathVariable int fid, int tid){
        return DeliveryTimeQuery.delete(fid, tid);
    }
    
    @GetMapping("all")
    public List<DeliveryTime> selectAll(){
        return DeliveryTimeQuery.getAllDeliveryTime();
    }
    
    @GetMapping("{fid,tid}")
    public DeliveryTime selectById(@PathVariable int fid, int tid){
        return DeliveryTimeQuery.getDeliveryTimeById(fid, tid);
    }
}
