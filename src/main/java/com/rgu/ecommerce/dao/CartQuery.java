package com.rgu.ecommerce.dao;

import com.rgu.ecommerce.model.Cart;
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
public class CartQuery {

    private static final String ADD = "INSERT INTO cart(userId, productId) VALUES(?,?)";

    private static final String DELETE = "DELETE FROM cart WHERE userId=? AND productId=?";

    private static final String SELECT_BY_ID = "SELECT FROM cart WHERE userId=?";

    public static boolean add(Cart c) {
        boolean success = false;
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD)) {
            
            ps.setInt(1, c.getUserId());
            ps.setInt(2, c.getProducts().getId());

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

    private static Cart mapObject(ResultSet rs) throws SQLException {
        Cart c = new Cart();
        c.setUserId(rs.getInt(1));
//            c.setProducts(Product.selectProductById(rs.......));
        return c;
    }

    public static List<Cart> selectById(int id) {
        boolean success = false;
        List<Cart> list = new ArrayList<>();
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
