
package com.rgu.ecommerce.dao;

import com.rgu.ecommerce.commons.Address;
import com.rgu.ecommerce.model.OrderItem;
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
public class OrderItemQuery {
    
    private static final String ADD = "INSERT INTO order_item(order_id, seller_id, d_address_id,d_line_1,d_line_2,d_locality_id,d_city_id,d_state,"
            + "d_country,d_phone)VALUES(?,?,?,?,?,?,?,?,?,?)";

    private static final String UPDATE = "UPDATE order_item SET seller_id=?, d_address_id=?,d_line_1=?,d_line_2=?,d_locality_id=?,d_city_id=?,d_state=?,"
            + "d_country=?,d_phone=? WHERE order_id = ?";
    
    private static final String SELECT_BY_ID = "SELECT * FROM order_item WHERE order_id=?";
    
    public static boolean add(OrderItem oi){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD)){
            ps.setInt(1, oi.getOrderId());
            ps.setInt(2, oi.getSellerId());
            ps.setInt(3, oi.getDeliveryAddress().getAddressId());
            ps.setString(4, oi.getDeliveryAddress().getLine1());
            ps.setString(5, oi.getDeliveryAddress().getLine2());
            ps.setInt(6, oi.getDeliveryAddress().getLocality().getLocalityId());
            ps.setInt(7, oi.getDeliveryAddress().getCity().getCityId());
            ps.setInt(8, oi.getDeliveryAddress().getState().getStateId());
            ps.setInt(9, oi.getDeliveryAddress().getCountry().getCountryId());
            ps.setInt(10, oi.getDeliveryAddress().getPhone());
            
            success = ps.executeUpdate()==1;
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }
    
    public static boolean update(OrderItem oi){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(UPDATE)){

            ps.setInt(1, oi.getSellerId());
            ps.setInt(2, oi.getDeliveryAddress().getAddressId());
            ps.setString(3, oi.getDeliveryAddress().getLine1());
            ps.setString(4, oi.getDeliveryAddress().getLine2());
            ps.setInt(5, oi.getDeliveryAddress().getLocality().getLocalityId());
            ps.setInt(6, oi.getDeliveryAddress().getCity().getCityId());
            ps.setInt(7, oi.getDeliveryAddress().getState().getStateId());
            ps.setInt(8, oi.getDeliveryAddress().getCountry().getCountryId());
            ps.setInt(9, oi.getDeliveryAddress().getPhone());
            
            ps.setInt(10, oi.getOrderId());

            success = ps.executeUpdate()==1;
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }
    
    private static OrderItem mapObject(ResultSet rs)throws SQLException{
        OrderItem oi = new OrderItem();
        oi.setOrderId(rs.getInt(1));
        oi.setSellerId(rs.getInt(2));
        Address a = new Address();
        a.setAddressId(rs.getInt(3));
        a.setLine1(rs.getString(4));
        a.setLine2(rs.getString(5));
//        a.setLocality(Loca);
//        a.setCity(rs.get);
//        a.setState(state);
//        a.setCountry(country);
        a.setPhone(rs.getInt(10));
        
        oi.setDeliveryAddress(a);
        
        return oi;
    }
    
    public static List<OrderItem> getOrderItemsById(int id){
        List<OrderItem> list = new ArrayList<>();
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
