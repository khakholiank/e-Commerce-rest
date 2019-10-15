
package com.rgu.ecommerce.dao;

import com.rgu.ecommerce.model.config.UnitType;
import com.rgu.ecommerce.model.Stock;
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
public class StockQuery {
    private static final String ADD = "INSERT INTO stock(hub_id,product_id,seller_id,qty,rate,units)VALUES(?,?,?,?,?,?)";
    
    private static final String UPDATE = "UPDATE stock SET hub_id=?,qty=?,rate=?,units=? WHERE product_id=? AND seller_id=?";
    
    private static final String DELETE = "DELETE FROM stock where product_id=? AND seller_id=?";
    
    private static final String SELECT_ALL = "SELECT* FROM stock";
    
    private static final String SELECT_BY_PRODUCT = "SELECT * FROM stock WHERE product_id=?";
    
    public static boolean add(Stock s){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD)){
            ps.setInt(1, s.getHubId());
            ps.setInt(2, s.getProductId().getId());
            ps.setInt(3, s.getSellerId().getId());
            ps.setInt(4, s.getQty());
            ps.setDouble(5, s.getRate());
            
            success = ps.executeUpdate()==1;
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }
    public static boolean update(Stock s){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(UPDATE)){
            ps.setInt(1, s.getHubId());
            
            ps.setInt(2, s.getQty());
            ps.setDouble(3, s.getRate());
            ps.setInt(4, s.getUnits().getCode());
            
            ps.setInt(5, s.getProductId().getId());
            ps.setInt(6, s.getSellerId().getId());
            
            success = ps.executeUpdate()==1;
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }
    public static boolean delete(int id_p, int id_s){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(DELETE)){
            ps.setInt(1, id_p);
            ps.setInt(2, id_s);
            
            success = ps.executeUpdate()==1;
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }
    
    private static Stock mapObject(ResultSet rs)throws SQLException{
        Stock s = new Stock();
        s.setHubId(rs.getInt(1));
        s.setProductId(ProductQuery.selectProductById(rs.getInt(2)));
        s.setSellerId(UserQuery.selectUserById(rs.getInt(3)));
        s.setQty(rs.getInt(4));
        s.setRate(rs.getDouble(5));
        s.setUnits(UnitType.valueOf(rs.getInt(6)));
        return s;
    }
    
    public static List<Stock> selectByProduct(int id){
        List<Stock> list = new ArrayList<>();
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_PRODUCT)){
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
    
    public static List<Stock> selectAll(){
        List<Stock> list = new ArrayList<>();
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
