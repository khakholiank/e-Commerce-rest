
package com.rgu.ecommerce.rest;

import com.rgu.ecommerce.dao.CategoryQuery;
import com.rgu.ecommerce.model.Category;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *z
 * @author nikitkhakholia
 */
@RestController
@RequestMapping("category/")
public class CatgeoryController {
    @GetMapping("all")
    public List<Category> getAllCategories(){
        return CategoryQuery.selectAllCategories();
    }
}
