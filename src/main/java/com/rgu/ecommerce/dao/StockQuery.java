
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
    private static final String ADD = "INSERT INTO stock(hub_id,product_id,seller_id,qty,rate)VALUES(?,?,?,?,?)";
    
    private static final String UPDATE = "UPDATE stock SET hub_id=?,qty=?,rate=? WHERE product_id=? AND seller_id=?";
    
    private static final String DELETE = "DELETE FROM stock where product_id=? AND seller_id=?";
    
    private static final String SELECT_ALL = "SELECT* FROM stock";
    
    private static final String SELECT_BY_PRODUCT = "SELECT * FROM stock WHERE product_id=?";
    
    private static final String SELECT_BY_CHEAPEST_PRODUCT = "SELECT min(rate) AS 'Minimum Rate' FROM stock WHERE product_id = ?";
    
    private static final String SELECT_BY_PRODUCT_AND_USER_ID = "SELECT * FROM stock WHERE product_id=? * seller_id=?";
    
    private static final String SELECT_BY_SELLER = "SELECT * FROM stock WHERE seller_id=?";
    
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
            
            ps.setInt(4, s.getProductId().getId());
            ps.setInt(5, s.getSellerId().getId());
            
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
        s.setSellerId(SellerQuery.selectSellerById(rs.getInt(3)));
        s.setQty(rs.getInt(4));
        s.setRate(rs.getDouble(5));
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
    
    public static Stock selectByProductAndUserId(int productId, int sellerId){
        Stock stock = new Stock();
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_PRODUCT_AND_USER_ID)){
            ps.setInt(1, productId);
            ps.setInt(2, sellerId);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    stock = mapObject(rs);
                }
            }
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return null;
        }
        return stock;
    }
    
    public static double selectCheapestRateOfProduct(int productId){
        double rate = 0;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_CHEAPEST_PRODUCT)){
            ps.setInt(1, productId);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    rate = rs.getDouble(1);
                }
            }
        }catch(SQLException ex){
            System.err.println(ex.toString());
            }
        return rate;
    }
    
    public static List<Stock> selectStockBySellerId(int id){
        List<Stock> stocks = new ArrayList<>();
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_SELLER)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    stocks.add(mapObject(rs));
                }
            }
        }catch(SQLException ex){
            System.err.println(ex.toString());
        }
        return stocks;
    }
}
