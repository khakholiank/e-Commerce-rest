package com.rgu.ecommerce.dao;

import com.rgu.ecommerce.dao.conn.Conn;
import com.rgu.ecommerce.model.CartItem;
import com.rgu.ecommerce.model.config.UnitType;
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
public class CartItemQuery {

    private static final String ADD = "INSERT INTO cart_item(id, product_id,qty,unit_type,seller_id) VALUES(?,?,?,?,?)";

    private static final String UPDATE = "UPDATE cart_item SET qty=? WHERE id=? AND product_id=?";

    private static final String DELETE = "DELETE FROM cart_item WHERE id=? AND product_id=?";

    private static final String SELECT_BY_ID = "SELECT * FROM cart_item WHERE id=?";

    private static final String SELECT_PARTICULAR_ITEM_FROM_CART = "SELECT * FROM cart_item WHERE id=? AND product_id=?";

    private static CartItem existingCartItem;

    public static boolean add(CartItem c) {
        boolean success = false;

        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD)) {

            if (getParticularItemFromCart(c.getUserId().getId(), c.getSellerId().getId())) {
                success = update(c.getUserId().getId(), c.getSellerId().getId(), c.getQty()+existingCartItem.getQty());
            } else {
                ps.setInt(1, c.getUserId().getId());
                ps.setInt(2, c.getProductId().getId());
                ps.setDouble(3, c.getQty());
                ps.setInt(4, c.getUnitType().getCode());
                ps.setInt(5, c.getSellerId().getId());
            }
            success = ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }

        return success;
    }

    public static boolean update(int userId, int productId, double qty) {
        boolean success = false;
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(UPDATE)) {

            ps.setDouble(1, qty);
            ps.setInt(2, userId);
            ps.setInt(3, productId);
            success = ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return success;
    }

    public static boolean delete(int userid, int productId) {
        boolean success = false;
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(DELETE)) {
            ps.setInt(1, userid);
            ps.setInt(2, productId);

            success = ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
        return success;
    }

    private static CartItem mapObject(ResultSet rs) throws SQLException {
        CartItem c = new CartItem();
        c.setUserId(UserQuery.selectUserById(rs.getInt(1)));
        c.setProductId(ProductQuery.selectProductById(rs.getInt(2)));
        c.setQty(rs.getDouble(3));
        c.setUnitType(UnitType.valueOf(rs.getInt(4)));
        c.setSellerId(SellerQuery.selectSellerById(rs.getInt(5)));
        c.setRate(StockQuery.selectCheapestRateOfProduct(rs.getInt(2)));
        return c;
    }

    public static List<CartItem> selectById(int id) {
        List<CartItem> list = new ArrayList<>();
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

    private static boolean getParticularItemFromCart(int userId, int productId) {
        boolean exist = false;
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_PARTICULAR_ITEM_FROM_CART)) {

            ps.setInt(1, userId);
            ps.setInt(2, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    if (rs == null) {
                        exist = false;
                    } else {
                        existingCartItem = mapObject(rs);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return exist;
    }
}
