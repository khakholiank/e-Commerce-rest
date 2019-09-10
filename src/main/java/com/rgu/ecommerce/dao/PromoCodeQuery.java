
package com.rgu.ecommerce.dao;

import com.rgu.ecommerce.config.PromoType;
import com.rgu.ecommerce.model.PromoCode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nikit Khakholia
 */
public class PromoCodeQuery {
    private static final String ADD = "INSERT INTO promo_code(code,type,amount)VALUES(?,?,?)";
    
    private static final String UPDATE = "UPDATE promo_code SET type = ?,amount = ? WHERE code=?";
    
    private static final String DELETE = "DELETE from promo_code WHERE code = ?";
    
    private static final String SELECT_ALL = "SELECT * FROM promo_code";
    
    private static final String SELECT_BY_ID = "SELECT * FROM promo_code WHERE code = ?";
    
    public static boolean add(PromoCode pc){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD)){
            ps.setString(1, pc.getCode());
            ps.setInt(2, pc.getType().getCode());
            ps.setDouble(3, pc.getAmount());
            
            success = ps.executeUpdate()==1;
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }
    
    public static boolean update(PromoCode pc){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(UPDATE)){
            ps.setInt(1, pc.getType().getCode());
            ps.setDouble(2, pc.getAmount());
            
            ps.setString(3, pc.getCode());

            
            success = ps.executeUpdate()==1;
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }
    
    public static boolean delete(String code){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(DELETE)){
            ps.setString(1, code);
            success = ps.executeUpdate()==1;
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }
    private static PromoCode mapObject(ResultSet rs)throws SQLException{
        PromoCode pc = new PromoCode();
        pc.setCode(rs.getString(1));
        pc.setType(PromoType.valueOf(rs.getInt(2)));
        pc.setAmount(rs.getDouble(3));
        return pc;
    }
    
    public static PromoCode selectById(String code){
        PromoCode pc = new PromoCode();
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)){
            ps.setString(1, code);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    pc=mapObject(rs);
                }
            }
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return null;
        }
        return pc;
    }
    
    public static List<PromoCode> selectAll(){
        List<PromoCode> list = new ArrayList<>();
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
