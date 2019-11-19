
package com.rgu.ecommerce.dao;

import com.rgu.ecommerce.dao.conn.Conn;
import com.rgu.ecommerce.model.SubCategory;
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
public class SubCategoryQuery {
    
    private static String ADD = "INSERT INTO sub_category(id, name, category_id) VALUES(?,?,?)";
    
    private static String SELECT_BY_ID = "SELECT * FROM sub_category WHERE category_id=?";
    
    public static List<SubCategory> selectSubCategoryById(int id){
        List<SubCategory> subCategoryList = new ArrayList<>();
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    subCategoryList.add(mapObject(rs));
                }
            }
        }catch(SQLException ex){
            System.err.println("Select Category By Id Error: "+ex.toString());
        }
        return subCategoryList;
    }

    private static SubCategory mapObject(ResultSet rs) throws SQLException{
     
        SubCategory subCategory = new SubCategory();
        subCategory.setId(rs.getInt(1));
        subCategory.setName(rs.getString(2));
        subCategory.setCategory(CategoryQuery.selectCategoryById(rs.getInt(3)));
        
        return subCategory;
    }
    
}
