
package com.rgu.ecommerce.dao;

import com.rgu.ecommerce.dao.conn.Conn;
import com.rgu.ecommerce.model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nikitkhakholia
 */
public class CategoryQuery {
    
    
    private static String GET_ALL_CATEGORIES = "SELECT * FROM category";
    private static String SELECT_CATEGORY_BY_ID = "SELECT * FROM category WHERE id = ?";
    
    private static Category mapObject(ResultSet rs)throws SQLException{
        Category c = new Category();
        c.setId(rs.getInt(1));
        c.setName(rs.getString(2));
        return c;
        
    }
    
    public static List<Category> selectAllCategories(){
        List<Category> categories = new ArrayList<>();
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(GET_ALL_CATEGORIES)){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    categories.add(mapObject(rs));
                }
            }
            
        }catch(SQLException sqle){
            System.err.println("Category Query Error: "+sqle.toString());
        }
        return categories;
    }
    
    public static Category selectCategoryById(int id){
        Category category = new Category();
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_CATEGORY_BY_ID)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    category = mapObject(rs);
                }
            }
            
        }catch(SQLException sqle){
            System.err.println("Category Query Error: "+sqle.toString());
        }
        return category;
    }
    
}
