package com.rgu.ecommerce.dao;

import com.rgu.ecommerce.dao.commons.AddressQuery;
import com.rgu.ecommerce.dao.commons.LocalityQuery;
import com.rgu.ecommerce.model.commons.Address;
import com.rgu.ecommerce.model.OrderItem;
import com.rgu.ecommerce.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.rgu.ecommerce.dao.conn.Conn;
import com.rgu.ecommerce.model.CartItem;
import java.time.LocalDateTime;

/**
 *
 * @author Nikit Khakholia
 */
public class OrderItemQuery {

    private static final String ADD = "INSERT INTO order_item(order_id, seller_id, d_address_id,d_line_1,d_line_2,d_locality_id,"
            + "d_phone,product_id,product_name,product_description,tags,img_media_id,qty,tracking_id,est_delivery_time)"
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

//    private static final String UPDATE = "UPDATE order_item SET seller_id=?, d_address_id=?,d_line_1=?,d_line_2=?,d_locality_id=?,"
//            + "d_phone=?,product_id=?,product_name=?,product_description=?,tags=?,img_media_id=?,qty=?,tracking_id=?,est_delivery_time=? WHERE order_id = ?";

    private static final String SELECT_BY_ID = "SELECT * FROM order_item WHERE order_id=?";

    public static boolean add(List<OrderItem> oi) {
         List<Boolean> successes = new ArrayList<>();
        boolean finalSuccess = false;
        for (int i = 0; i < oi.size(); i++) {
            try (Connection con = Conn.getConnection();
                    PreparedStatement ps = con.prepareStatement(ADD)) {
                
                
                ps.setInt(1, oi.get(i).getOrderId());
                ps.setInt(2, oi.get(i).getSeller().getId());
                ps.setInt(3, oi.get(i).getDeliveryAddress().getAddressId());
                ps.setString(4, oi.get(i).getDeliveryAddress().getLine1());
                ps.setString(5, oi.get(i).getDeliveryAddress().getLine2());
                ps.setInt(6, oi.get(i).getDeliveryAddress().getLocality().getLocalityId());
                ps.setInt(7, oi.get(i).getDeliveryAddress().getPhone());
                ps.setInt(8, oi.get(i).getProduct().getId());
                ps.setString(9, oi.get(i).getProduct().getNameOfItem());
                ps.setString(10, oi.get(i).getProduct().getDescription());
                ps.setString(11, oi.get(i).getProduct().getTags());
                ps.setInt(12, oi.get(i).getProduct().getImgMediaId());
                ps.setDouble(13, oi.get(i).getQty());
                ps.setString(14, oi.get(i).getTrackingId());
                ps.setTimestamp(15, java.sql.Timestamp.valueOf(oi.get(i).getEstDeliveryTime()));
                
               

                successes.add(ps.executeUpdate() == 1);
            } catch (SQLException ex) {
                System.err.println("OrderItemError"+ex.toString());
                return finalSuccess;
            }
        }
        
        for(boolean item : successes)if(!item)return false;
        return true;
    }

//    public static boolean update(OrderItem oi) {
//        boolean success = false;
//        try (Connection con = Conn.getConnection();
//                PreparedStatement ps = con.prepareStatement(UPDATE)) {
//
//            ps.setInt(1, oi.getSellerId());
//            ps.setInt(2, oi.getDeliveryAddress().getAddressId());
//            ps.setString(3, oi.getDeliveryAddress().getLine1());
//            ps.setString(4, oi.getDeliveryAddress().getLine2());
//            ps.setInt(5, oi.getDeliveryAddress().getLocality().getLocalityId());
//            ps.setInt(6, oi.getDeliveryAddress().getPhone());
//            ps.setInt(7, oi.getProduct().getId());
//            ps.setString(8, oi.getProduct().getNameOfItem());
//            ps.setString(9, oi.getProduct().getDescription());
//            ps.setString(10, oi.getProduct().getTags());
//            ps.setInt(11, oi.getProduct().getImgMediaId());
//            ps.setInt(12, oi.getQty());
//            ps.setString(13, oi.getTrackingId());
//            ps.setTimestamp(14, java.sql.Timestamp.valueOf(oi.getEstDeliveryTime()));
//
//            ps.setInt(15, oi.getOrderId());
//
//            success = ps.executeUpdate() == 1;
//        } catch (SQLException ex) {
//            System.err.println(ex.toString());
//            return success;
//        }
//        return success;
//    }

    private static OrderItem mapObject(ResultSet rs) throws SQLException {
        OrderItem oi = new OrderItem();
        oi.setOrderId(rs.getInt(1));
        oi.setSeller(SellerQuery.selectSellerById(rs.getInt(2)));
        
        Address a = new Address();
        a.setAddressId(rs.getInt(3));
        a.setLine1(rs.getString(4));
        a.setLine2(rs.getString(5));
        a.setLocality(LocalityQuery.selectById(rs.getInt(6)));
        a.setPhone(rs.getInt(7));
        oi.setDeliveryAddress(a);

        Product p = new Product();
        p.setId(rs.getInt(8));
        p.setNameOfItem(rs.getString(9));
        p.setDescription(rs.getString(10));
        p.setTags(rs.getString(11));
        p.setImgMediaId(rs.getInt(12));
        oi.setProduct(p);
        
        oi.setQty(rs.getInt(13));
        oi.setTrackingId(rs.getString(14));
        oi.setEstDeliveryTime(rs.getTimestamp(15).toLocalDateTime());

        return oi;
    }

    public static List<OrderItem> getOrderItemsById(int id) {
        List<OrderItem> list = new ArrayList<>();
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapObject(rs));
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
        return list;
    }
}
