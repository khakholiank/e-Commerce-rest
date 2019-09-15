
package com.rgu.ecommerce.rest;

import com.rgu.ecommerce.dao.RatingQuery;
import com.rgu.ecommerce.model.Rating;
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
@RequestMapping("rating/")
public class RatingController {
    @PostMapping("add")
    public static boolean add(@RequestBody Rating r){
        return RatingQuery.add(r);
    }
    
    @PutMapping("update")
    public static boolean update(@RequestBody Rating r){
        return RatingQuery.update(r);
    }
    
    @DeleteMapping("delete/{id}")
    public static boolean delete(@PathVariable int id){
        return RatingQuery.delete(id);
    }
    
    @GetMapping("id/{id}")
    public static Rating selectRatingByRatingId(@PathVariable int id){
        return RatingQuery.selectByRatingId(id);
    }
    
    @GetMapping("user/{id}")
    public static List<Rating> selectRatingByUserId(@PathVariable int id){
        return RatingQuery.selectByUserId(id);
    }
}
