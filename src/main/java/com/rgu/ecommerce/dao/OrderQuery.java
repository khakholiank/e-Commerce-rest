package com.rgu.ecommerce.dao;

import com.rgu.ecommerce.commons.Address;
import com.rgu.ecommerce.config.OrderStatus;
import com.rgu.ecommerce.model.Order;
import com.rgu.ecommerce.model.Product;
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

    public static boolean add(Order o) {
        boolean success = false;
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD)) {
            ps.setInt(1, o.getId());
            ps.setInt(2, o.getSellerId());
            ps.setInt(3, o.getCustId());
            ps.setTimestamp(4, java.sql.Timestamp(o.getTimeOfOrder()));
            ps.setInt(5, o.getDeliveryAddress().getAddressId());
            ps.setString(6, o.getDeliveryAddress().getLine1());
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

            success = ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }

    public static boolean update(Order o) {
        boolean success = false;
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(UPDATE)) {
            ps.setInt(1, o.getSellerId());
            ps.setInt(2, o.getCustId());
            ps.setTimestamp(3, java.sql.Timestamp(o.getTimeOfOrder()));
            ps.setInt(4, o.getDeliveryAddress().getAddressId());
            ps.setString(5, o.getDeliveryAddress().getLine1());
            ps.setString(6, o.getDeliveryAddress().getLine2());
            ps.setInt(7, o.getDeliveryAddress().getLocality().getLocalityId());
            ps.setInt(8, o.getDeliveryAddress().getCity().getCityId());
            ps.setInt(9, o.getDeliveryAddress().getState().getStateId());
            ps.setInt(10, o.getDeliveryAddress().getCountry().getCountryId());
            ps.setInt(11, o.getDeliveryAddress().getPhone());
            ps.setInt(12, o.getBillingAddress().getAddressId());
            ps.setString(13, o.getBillingAddress().getLine1());
            ps.setString(14, o.getBillingAddress().getLine2());
            ps.setInt(15, o.getBillingAddress().getLocality().getLocalityId());
            ps.setInt(16, o.getBillingAddress().getCity().getCityId());
            ps.setInt(17, o.getBillingAddress().getState().getStateId());
            ps.setInt(18, o.getBillingAddress().getCountry().getCountryId());
            ps.setInt(19, o.getBillingAddress().getPhone());
            ps.setInt(20, o.getProduct().getId());
            ps.setString(21, o.getProduct().getNameOfItem());
            ps.setString(22, o.getProduct().getDescription());
            ps.setInt(23, o.getProduct().getImgMediaId());
            ps.setInt(24, o.getQty());
            ps.setString(25, o.getTrackingId());
            ps.setTimestamp(26, java.sql.Timestamp(o.getEstDeliveryTime()));
            ps.setString(27, o.getPromoCode());
            ps.setDouble(28, o.getGrossAmount());
            ps.setDouble(29, o.getDiscApplied());
            ps.setDouble(30, o.getDeliveryFee());
            ps.setDouble(31, o.getNetAmount());
            ps.setInt(32, o.getOrderStatus().getCode());

            ps.setInt(33, o.getId());

            success = ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }

    public static boolean delete(int id) {
        boolean success = false;
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            success = ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }

    private static Order mapObject(ResultSet rs) throws SQLException {
        Order o = new Order();
        o.setId(rs.getInt(1));
        o.setSellerId(rs.getInt(2));
        o.setCustId(rs.getInt(3));
        o.setTimeOfOrder(rs.getTimestamp(4).toLocalDateTime());
        Address dA = new Address();
        dA.setAddressId(rs.getInt(5));
        dA.setLine1(rs.getString(6));
        dA.setLine2(rs.getString(7));
        dA.setLocality(rs.getInt(8));
        dA.setCity(rs.getInt(9));
        dA.setState(rs.getInt(10));
        dA.setCountry(rs.getInt(11));
        dA.setPhone(rs.getInt(12));
        o.setDeliveryAddress(dA);

        Address bA = new Address();
        bA.setAddressId(rs.getInt(13));
        bA.setLine1(rs.getString(14));
        bA.setLine2(rs.getString(15));
        bA.setLocality(rs.getInt(16));
        bA.setCity(rs.getInt(17));
        bA.setState(rs.getInt(18));
        bA.setCountry(rs.getInt(19));
        bA.setPhone(rs.getInt(20));
        o.setDeliveryAddress(bA);
//        "INSERT INTO order(id,seller_id,cust_id,time_of_order,d_address_id,d_line_1,d_line_2,d_locality_id,d_city_id,"
//            + "d_state_id,d_country_id,d_phone,b_address_id,b_line_1,b_line_2,b_locality_id,b_city_id,b_state,b_country,b_phone,item_id,item_name,item_desc,"
//            + "item_img_media_id,qty,tracking_id,est_delivery_time,promocode,gross_amt,disc_applied,delivery_fee,net_amount,order_status_code) "
        Product p = new Product();
        p.setId(rs.getInt(21));
        p.setNameOfItem(rs.getString(22));
        p.setDescription(rs.getString(23));
        p.setImgMediaId(rs.getInt(24));
        o.setProduct(p);

        o.setQty(rs.getInt(25));
        o.setTrackingId(rs.getString(26));
        o.setEstDeliveryTime(rs.getTimestamp(27).toLocalDateTime());
        o.setPromoCode(rs.getString(28));
        o.setGrossAmount(rs.getDouble(29));
        o.setDiscApplied(rs.getDouble(30));
        o.setDeliveryFee(rs.getDouble(31));
        o.setNetAmount(rs.getDouble(32));
        o.setOrderStatus(OrderStatus.valueOf(rs.getInt(33)));

        return o;
    }

    public static List<Order> getOrderById(int id) {
        List<Order> list = new ArrayList<>();
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_ORDER_ID)) {
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

    public static List<Order> getOrderByCustId(int id) {
        List<Order> list = new ArrayList<>();
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_CUSTOMER_ID)) {
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

    public static List<Order> getOrderBySellerId(int id) {
        List<Order> list = new ArrayList<>();
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_SELLER_ID)) {
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

    public static List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_ALL)) {
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
