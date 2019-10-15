
package com.rgu.ecommerce.dao;

import com.rgu.ecommerce.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.rgu.ecommerce.dao.conn.Conn;


/**
 *
 * @author Nikit Khakholia
 */
public class ProductQuery {
    private static final String ADD = "INSERT INTO product(name_of_item,description,tags,image_media_id) VALUES(?,?,?,?)";
    
    private static final String UPDATE = "UPDATE product SET name_of_item=?,description=?,tags=?,image_media_id=? WHERE id=?";
    
    private static final String DELETE = "DELETE from product WHERE id=?";
    
    private static final String SELECT_ALL = "SELECT * FROM product";
    
    private static final String SELECT_BY_ID = "SELECT * FROM product WHERE id=?";
    
    public static boolean add(Product product){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD)){
            ps.setString(1, product.getNameOfItem());
            ps.setString(2, product.getDescription());
            ps.setString(3, product.getTags());
            ps.setInt(4, product.getImgMediaId());
            try(ResultSet rs = ps.getGeneratedKeys()){
                product.setId(rs.getInt(1));
            }
            
            success = ps.executeUpdate()==1;
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }
    
    public static boolean update(Product product){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(UPDATE)){
            ps.setString(1, product.getNameOfItem());
            ps.setString(2, product.getDescription());
            ps.setString(3, product.getTags());
            ps.setInt(4, product.getImgMediaId());
            
            ps.setInt(5, product.getId());

            
            success = ps.executeUpdate()==1;
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }
    
    public  static boolean delete(int id){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(DELETE)){
            ps.setInt(1, id);
            success=ps.executeUpdate()==1;
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }
    
    
    private static Product mapObject(ResultSet rs)throws SQLException{
        Product product = new Product();
            product.setId(rs.getInt(1));
            product.setNameOfItem(rs.getString(2));
            product.setDescription(rs.getString(3));
            product.setTags(rs.getString(4));
            product.setImgMediaId(rs.getInt(5));
            
        return product;
    }
    
    public static Product selectProductById(int id){
        Product product = new Product();
        try(Connection con =Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)){
            ps.setInt(1, id);
            
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    product=mapObject(rs);
                }
            }
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return null;
        }
        return product;
    }

    public static List<Product> selectAllProducts(){
        List<Product> list = new ArrayList<>();
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_ALL)){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    list.add(mapObject(rs));
                }
            }
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return null;
        }
        return list;
    }
}
