package com.rgu.ecommerce.dao;

import com.rgu.ecommerce.dao.commons.LocalityQuery;
import com.rgu.ecommerce.model.commons.Address;
import com.rgu.ecommerce.model.config.OrderStatus;
import com.rgu.ecommerce.model.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.rgu.ecommerce.dao.conn.Conn;
import com.rgu.ecommerce.model.commons.Locality;
import java.sql.Statement;

/**
 *
 * @author Nikit Khakholia
 * 
 */
public class OrderQuery {

    private static final String ADD = "INSERT INTO order(cust_id,time_of_order, b_address_id,b_line_1,b_line_2,b_locality_id,"
            + "b_phone,promocode,gross_amt,disc_applied,delivery_fee,net_amount,order_status_code) "
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String UPDATE = "UPDATE order SET cust_id=?,time_of_order=?, b_address_id=?,b_line_1=?,b_line_2=?,b_locality_id=?,"
            + "b_phone=?,promocode=?,gross_amt=?,disc_applied=?,delivery_fee=?,net_amount=?,order_status_code=? WHERE id=?";

    private static final String DELETE = "DELETE FROM order WHERE id=?";

    private static final String SELECT_BY_ORDER_ID = "SELECT * FROM order WHERE id = ?";

    private static final String SELECT_BY_CUSTOMER_ID = "SELECT * FROM order WHERE cust_id = ?";

    private static final String SELECT_BY_SELLER_ID = "SELECT * FROM order WHERE seller_id = ?";

    private static final String SELECT_ALL = "SELECT * FROM order";

    public static boolean add(Order o) {
        boolean success = false;
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, o.getCustId());
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(o.getTimeOfOrder()));
            ps.setInt(3, o.getBillingAddress().getAddressId());
            ps.setString(4, o.getBillingAddress().getLine1());
            ps.setString(5, o.getBillingAddress().getLine2());
            ps.setInt(6, o.getBillingAddress().getLocality().getLocalityId());
            ps.setInt(7, o.getBillingAddress().getPhone());
            ps.setString(8, o.getPromoCode());
            ps.setDouble(9, o.getGrossAmount());
            ps.setDouble(10, o.getDiscApplied());
            ps.setDouble(11, o.getDeliveryFee());
            ps.setDouble(12, o.getNetAmount());
            ps.setInt(13, o.getOrderStatus().getCode());
            try(ResultSet rs = ps.getGeneratedKeys()){
                o.setId(rs.getInt(1));
            }
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

            ps.setInt(7, o.getBillingAddress().getPhone());

            ps.setString(8, o.getPromoCode());
            ps.setDouble(9, o.getGrossAmount());
            ps.setDouble(10, o.getDiscApplied());
            ps.setDouble(11, o.getDeliveryFee());
            ps.setDouble(12, o.getNetAmount());
            ps.setInt(13, o.getOrderStatus().getCode());

            ps.setInt(14, o.getId());

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
        bA.setLocality(LocalityQuery.selectById(rs.getInt(7)));
        bA.setPhone(rs.getInt(8));
        o.setPromoCode(rs.getString(9));
        o.setGrossAmount(rs.getDouble(10));
        o.setDiscApplied(rs.getDouble(11));
        o.setDeliveryFee(rs.getDouble(12));
        o.setNetAmount(rs.getDouble(13));
        o.setOrderStatus(OrderStatus.valueOf(rs.getInt(14)));

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
