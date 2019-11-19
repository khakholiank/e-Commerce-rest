
package com.rgu.ecommerce.rest;

import com.rgu.ecommerce.dao.SubCategoryQuery;
import com.rgu.ecommerce.model.SubCategory;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nikitkhakholia
 */

@RestController
@RequestMapping("sub-category/")
public class SubCategoryController {
    
    @GetMapping("id/{id}")
    public static List<SubCategory> getSubCategoryById(@PathVariable("id") int id){
        
        return SubCategoryQuery.selectSubCategoryById(id);
        
    }
}
