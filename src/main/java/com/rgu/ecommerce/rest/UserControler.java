
package com.rgu.ecommerce.rest;

import com.rgu.ecommerce.dao.UserQuery;
import com.rgu.ecommerce.dao.commons.AddressQuery;
import com.rgu.ecommerce.model.User;
import com.rgu.ecommerce.model.commons.Address;
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
@RequestMapping("user/")
public class UserControler {
    @PostMapping("add")
    public static boolean add(@RequestBody User u){
        return UserQuery.add(u);
    }
    
    @PutMapping("update")
    public static boolean update(@RequestBody User u){
        return UserQuery.update(u);
    }
    
    @DeleteMapping("delete/{id}")
    public static boolean delete(@PathVariable int id){
        return UserQuery.delete(id);
    }
    
    @GetMapping("all")
    public static List<User> selectAll(){
        return UserQuery.selectAll();
    }
    
    @GetMapping("id/{id}")
    public static User selectById(@PathVariable int id){
        return UserQuery.selectUserById(id);
    }
    
    @GetMapping("seller/all")
    public static List<User> selectSellers(){
        return UserQuery.selectSellers();
    }
    
    
    @GetMapping("buyer/all")
    public static List<User> selectBuyers(){
        return UserQuery.selectBuyers();
    }
    
    
    @GetMapping("admin/all")
    public static List<User> selectAdmins(){
        return UserQuery.selectAdmins();
    }
    
    @GetMapping("verify/{id}/{password}")
    public static boolean verifyUser(@PathVariable int id, @PathVariable String password){
        return UserQuery.verifyUser(id, password);
    }
    
    @GetMapping("address/id/{id}")
    public static List<Address> selectAddressByUser(@PathVariable int id){
        return AddressQuery.selectAddressByUserId(id);
    }
    
}
