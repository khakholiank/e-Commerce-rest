
package com.rgu.ecommerce.dao;

import com.rgu.ecommerce.dao.conn.Conn;
import com.rgu.ecommerce.model.config.UnitType;
import com.rgu.ecommerce.model.DeliveryTime;
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
public class DeliveryTimeQuery {

    private static final String ADD = "INSERT INTO delivery_time(fromPincode, toPincode, timeRequired, fee, unitType)VALUES(?,?,?,?,?)";
    
    private static final String UPDATE = "UPDATE delivery_time SET timeRequired=?, fee=?, unitType=? WHERE fromPincode=? AND toPincode=?";
    
    private static final String DELETE = "DELETE FROM delivery_time WHERE fromPincode=? AND toPincode=?";
    
    private static final String SELECT_BY_ID = "SELECT * FROM delivery_time WHERE fromPincode=? AND toPincode=?";
    
    private static final String SELECT_ALL = "SELECT * FROM delivery_time";
    
    public static boolean add(DeliveryTime dt){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD)){
            ps.setInt(1, dt.getFromPincode());
            ps.setInt(2, dt.getToPincode());
            ps.setDouble(3, dt.getTimeRequired());
            ps.setDouble(4, dt.getFee());
            ps.setInt(5, dt.getUnits().getCode());
            
            success = ps.executeUpdate()==1;
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }
    
    public static boolean update(DeliveryTime dt){
        boolean success =false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(UPDATE)){
            
            ps.setDouble(1, dt.getTimeRequired());
            ps.setDouble(2, dt.getFee());
            ps.setInt(3, dt.getUnits().getCode());
            ps.setInt(4, dt.getFromPincode());
            ps.setInt(5, dt.getToPincode());
            
            success = ps.executeUpdate()==1;
            
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }
    
    public  static boolean delete(int fromId, int toId){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(DELETE)){
            ps.setInt(1, fromId);
            ps.setInt(2, toId);
            
            success = ps.executeUpdate()==1;
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }
    
    private static DeliveryTime mapObject(ResultSet rs)throws SQLException{
        DeliveryTime dt = new DeliveryTime();
        dt.setFromPincode(rs.getInt(1));
        dt.setToPincode(rs.getInt(2));
        dt.setTimeRequired(rs.getDouble(3));
        dt.setFee(rs.getDouble(4));
        dt.setUnits(UnitType.valueOf(rs.getInt(5)));
        return dt;
    }
    
    public static DeliveryTime getDeliveryTimeById(int fromId, int toId){
        DeliveryTime dt = new DeliveryTime();
        try(Connection con = Conn.getConnection();
               PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)){
            ps.setInt(1, fromId);
            ps.setInt(2, toId);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    dt=mapObject(rs);
                }
            }
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return null;
        }
        return dt;
    }
    
    public static List<DeliveryTime> getAllDeliveryTime(){
        List<DeliveryTime> list = new ArrayList<>();
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









