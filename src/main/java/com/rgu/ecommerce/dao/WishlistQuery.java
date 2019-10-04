
package com.rgu.ecommerce.dao;

import com.rgu.ecommerce.model.Wishlist;
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
public class WishlistQuery {
    private static final String ADD = "INSERT INTO wishlist(id,product_id)VALUES(?,?)";
    
    private static final String DELETE = "DELETE FROM wishlist WHERE id=? AND product_id=?";
    
    private static final String SELECT_BY_ID = "SELECT * FROM wishliat WHERE id=?";
    
    public static boolean add(Wishlist wl){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD)){
            ps.setInt(1, wl.getId());
            ps.setInt(2, wl.getProductId().getId());
            
            success = ps.executeUpdate()==1;
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }
    public static boolean delete(Wishlist wl){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(DELETE)){
            ps.setInt(1, wl.getId());
            ps.setInt(2, wl.getProductId().getId());
            
            success = ps.executeUpdate()==1;
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }
    private static Wishlist mapObject(ResultSet rs)throws SQLException{
        Wishlist wl = new Wishlist();
        wl.setId(rs.getInt(1));
        wl.setProductId(ProductQuery.selectProductById(rs.getInt(2)));
        return wl;
    }
    
    public static List<Wishlist> selectWishlistById(int id){
        List<Wishlist> list = new ArrayList<>();
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)){
            ps.setInt(1, id);
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
