
package com.rgu.ecommerce.dao;

import com.rgu.ecommerce.model.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Nikit Khakholia
 */

//    private int id;
//    private int sellerId;
//    private int custId;
//    private LocalDateTime timeOfOrder;
//    private Address deliveryAddress;

//    private Address billingAddress;
//    private Product product;
//    private int qty;
//    private String trackingId;
//    private LocalDateTime estDeliveryTime;
//    private String promoCode;
//    private double grossAmount;
//    private double discApplied;
//    private double deliveryFee;
//    private double netAmount;
//    private OrderStatus orderStatus;


public class OrderQuery {
    
    private static final String ADD = "INSERT INTO order(id,seller_id,cust_id,time_of_order,d_address_id,d_line_1,d_line_2,d_locality_id,d_city_id,"
            + "d_state_id,d_country_id,d_phone,b_address_id,b_line_1,b_line_2,b_locality_id,b_city_id,b_state,b_country,b_phone,item_id,item_name,item_desc,"
            + "item_img_media_id,qty,tracking_id,est_delivery_time,promocode,gross_amt,disc_applied,delivery_fee,net_amount,order_status_code) "
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private static final String UPDATE = "UPDATE order SET seller_id=?,cust_id=?,time_of_order=?,d_address_id=?,d_line_1=?,d_line_2=?,d_locality_id=?,d_city_id=?,"
            + "d_state=?,d_country=?,d_phone=?,b_address_id=?,b_line_1=?,b_line_2=?,b_locality_id=?,b_city_id=?,b_state=?,b_country=?,b_phone=?,item_id=?,item_name=?,item_desc=?,"
            + "item_img_media_id=?,qty=?,tracking_id=?,est_delivery_time=?,promocode=?,gross_amt=?,disc_applied=?,delivery_fee=?,net_amount=?,order_status_code=?WHERE id=?";
    
    private static final String DELETE = "DELETE FROM order WHERE id=?";
    
    private static final String SELECT_BY_ORDER_ID = "SELECT * FROM order WHERE id = ?";
    
    private static final String SELECT_BY_CUSTOMER_ID = "SELECT * FROM order WHERE cust_id = ?";
    
    private static final String SELECT_BY_SELLER_ID = "SELECT * FROM order WHERE seller_id = ?";
    

    private static final String SELECT_ALL = "SELECT * FROM order";   
    
    public static boolean add(Order o){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD)){
            ps.setInt(1, o.getId());
            ps.setInt(2, o.getSellerId());
            ps.setInt(3, o.getSellerId());
            ps.setTimestamp(4, java.sql.Timestamp(o.getTimeOfOrder()));
            ps.setInt(5, o.getDeliveryAddress().getAddressId());
            ps.setString(5, o.getDeliveryAddress().getLine1());
            ps.setString(7, o.getDeliveryAddress().getLine2());
            ps.setInt(8, o.getDeliveryAddress().getLocality().getLocalityId());
            ps.setInt(9, o.getDeliveryAddress().getCity().getCityId());
            ps.setInt(10, o.getDeliveryAddress().getState().getStateId());
            ps.setInt(11, o.getDeliveryAddress().getCountry().getCountryId());
            ps.setInt(12, o.getDeliveryAddress().getPhone());
            ps.setInt(13, o.getBillingAddress().getAddressId());
            ps.setString(14, o.getBillingAddress().getLine1());
            ps.setString(15, o.getBillingAddress().getLine2());
            ps.setInt(16, o.getBillingAddress().getLocality().getLocalityId());
            ps.setInt(17, o.getBillingAddress().getCity().getCityId());
            ps.setInt(18, o.getBillingAddress().getState().getStateId());
            ps.setInt(19, o.getBillingAddress().getCountry().getCountryId());
            ps.setInt(20, o.getBillingAddress().getPhone());
            ps.setInt(21, o.getProduct().getId());
            ps.setString(22, o.getProduct().getNameOfItem());
            ps.setString(23, o.getProduct().getDescription());
            ps.setInt(24, o.getProduct().getImgMediaId());
            ps.setInt(25, o.getQty());
            ps.setString(26, o.getTrackingId());
            ps.setTimestamp(27, java.sql.Timestamp(o.getEstDeliveryTime()));
            ps.setString(28, o.getPromoCode());
            ps.setDouble(29, o.getGrossAmount());
            ps.setDouble(30, o.getDiscApplied());
            ps.setDouble(31, o.getDeliveryFee());
            ps.setDouble(32, o.getNetAmount());
            ps.setInt(33, o.getOrderStatus().getCode());
            
            success= ps.executeUpdate()==1;
            
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }

    public static boolean update(Order o){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD)){
            ps.setInt(1, o.getId());
            ps.setInt(2, o.getSellerId());
            ps.setInt(3, o.getSellerId());
            ps.setTimestamp(4, java.sql.Timestamp(o.getTimeOfOrder()));
            ps.setInt(5, o.getDeliveryAddress().getAddressId());
            ps.setString(5, o.getDeliveryAddress().getLine1());
            ps.setString(7, o.getDeliveryAddress().getLine2());
            ps.setInt(8, o.getDeliveryAddress().getLocality().getLocalityId());
            ps.setInt(9, o.getDeliveryAddress().getCity().getCityId());
            ps.setInt(10, o.getDeliveryAddress().getState().getStateId());
            ps.setInt(11, o.getDeliveryAddress().getCountry().getCountryId());
            ps.setInt(12, o.getDeliveryAddress().getPhone());
            ps.setInt(13, o.getBillingAddress().getAddressId());
            ps.setString(14, o.getBillingAddress().getLine1());
            ps.setString(15, o.getBillingAddress().getLine2());
            ps.setInt(16, o.getBillingAddress().getLocality().getLocalityId());
            ps.setInt(17, o.getBillingAddress().getCity().getCityId());
            ps.setInt(18, o.getBillingAddress().getState().getStateId());
            ps.setInt(19, o.getBillingAddress().getCountry().getCountryId());
            ps.setInt(20, o.getBillingAddress().getPhone());
            ps.setInt(21, o.getProduct().getId());
            ps.setString(22, o.getProduct().getNameOfItem());
            ps.setString(23, o.getProduct().getDescription());
            ps.setInt(24, o.getProduct().getImgMediaId());
            ps.setInt(25, o.getQty());
            ps.setString(26, o.getTrackingId());
            ps.setTimestamp(27, java.sql.Timestamp(o.getEstDeliveryTime()));
            ps.setString(28, o.getPromoCode());
            ps.setDouble(29, o.getGrossAmount());
            ps.setDouble(30, o.getDiscApplied());
            ps.setDouble(31, o.getDeliveryFee());
            ps.setDouble(32, o.getNetAmount());
            ps.setInt(33, o.getOrderStatus().getCode());
            
            success= ps.executeUpdate()==1;
            
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }
    
}
