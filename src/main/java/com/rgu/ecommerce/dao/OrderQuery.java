package com.rgu.ecommerce.dao;

import com.rgu.ecommerce.commons.Address;
import com.rgu.ecommerce.config.OrderStatus;
import com.rgu.ecommerce.model.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nikit Khakholia
 * 
 */
public class OrderQuery {

    private static final String ADD = "INSERT INTO order(id,cust_id,time_of_order, b_address_id,b_line_1,b_line_2,b_locality_id,b_city_id,b_state,"
            + "b_country,b_phone,promocode,gross_amt,disc_applied,delivery_fee,net_amount,order_status_code) "
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String UPDATE = "UPDATE order SET cust_id=?,time_of_order=?, b_address_id=?,b_line_1=?,b_line_2=?,b_locality_id=?,b_city_id=?,"
            + "b_state=?,b_country=?,b_phone=?,promocode=?,gross_amt=?,disc_applied=?,delivery_fee=?,net_amount=?,order_status_code=? WHERE id=?";

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
            ps.setInt(2, o.getCustId());
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(o.getTimeOfOrder()));
            ps.setInt(4, o.getBillingAddress().getAddressId());
            ps.setString(5, o.getBillingAddress().getLine1());
            ps.setString(6, o.getBillingAddress().getLine2());
            ps.setInt(7, o.getBillingAddress().getLocality().getLocalityId());
            ps.setInt(8, o.getBillingAddress().getCity().getCityId());
            ps.setInt(9, o.getBillingAddress().getState().getStateId());
            ps.setInt(10, o.getBillingAddress().getCountry().getCountryId());
            ps.setInt(11, o.getBillingAddress().getPhone());
            ps.setString(12, o.getPromoCode());
            ps.setDouble(13, o.getGrossAmount());
            ps.setDouble(14, o.getDiscApplied());
            ps.setDouble(15, o.getDeliveryFee());
            ps.setDouble(16, o.getNetAmount());
            ps.setInt(17, o.getOrderStatus().getCode());

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
            ps.setInt(1, o.getCustId());
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(o.getTimeOfOrder()));

            ps.setInt(3, o.getBillingAddress().getAddressId());
            ps.setString(4, o.getBillingAddress().getLine1());
            ps.setString(5, o.getBillingAddress().getLine2());
            ps.setInt(6, o.getBillingAddress().getLocality().getLocalityId());
            ps.setInt(7, o.getBillingAddress().getCity().getCityId());
            ps.setInt(8, o.getBillingAddress().getState().getStateId());
            ps.setInt(9, o.getBillingAddress().getCountry().getCountryId());
            ps.setInt(10, o.getBillingAddress().getPhone());

            ps.setString(11, o.getPromoCode());
            ps.setDouble(12, o.getGrossAmount());
            ps.setDouble(13, o.getDiscApplied());
            ps.setDouble(14, o.getDeliveryFee());
            ps.setDouble(15, o.getNetAmount());
            ps.setInt(16, o.getOrderStatus().getCode());

            ps.setInt(17, o.getId());

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
        o.setCustId(rs.getInt(2));
        o.setTimeOfOrder(rs.getTimestamp(3).toLocalDateTime());
        Address bA = new Address();
        bA.setAddressId(rs.getInt(4));
        bA.setLine1(rs.getString(5));
        bA.setLine2(rs.getString(6));
//        bA.setLocality(rs.getInt(7));
//        bA.setCity(rs.getInt(8));
//        bA.setState(rs.getInt(9));
//        bA.setCountry(rs.getInt(10));
        bA.setPhone(rs.getInt(11));
        o.setPromoCode(rs.getString(12));
        o.setGrossAmount(rs.getDouble(13));
        o.setDiscApplied(rs.getDouble(14));
        o.setDeliveryFee(rs.getDouble(15));
        o.setNetAmount(rs.getDouble(16));
        o.setOrderStatus(OrderStatus.valueOf(rs.getInt(17)));

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
