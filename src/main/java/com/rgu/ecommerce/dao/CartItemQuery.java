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

    private static final String DELETE = "DELETE FROM cart_item WHERE id=? AND product_id=?";

    private static final String SELECT_BY_ID = "SELECT * FROM cart_item WHERE id=?";

    public static boolean add(CartItem c) {
        boolean success = false;
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD)) {

            ps.setInt(1, c.getUserId());
            ps.setInt(2, c.getProducts().getId());
            ps.setDouble(3, c.getQty());
            ps.setInt(4, c.getUnitType().getCode());
            ps.setInt(5, c.getSellerId().getId());
            

            success = ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
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
        c.setUserId(rs.getInt(1));
        c.setProducts(ProductQuery.selectProductById(rs.getInt(2)));
        c.setQty(rs.getDouble(3));
        c.setUnitType(UnitType.valueOf(rs.getInt(4)));
        
        return c;
    }

    public static List<CartItem> selectById(int id) {
        boolean success = false;
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
}
