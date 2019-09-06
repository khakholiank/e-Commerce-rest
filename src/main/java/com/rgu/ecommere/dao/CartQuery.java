package com.rgu.ecommere.dao;

import com.rgu.ecommere.model.Cart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nikit Khakholia
 */
public class CartQuery {

    private final String ADD = "INSERT INTO cart(id, products, deliveryAddress) VALUES(?,?,?)";

    private final String UPDATE = "UPDATE cart SET products=?, deliveryAddress=? WHERE id=?";

    private final String SELECT_BY_ID = "SELECT FROM cart WHERE id=?";

    public boolean add() {
        boolean success = false;
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD)) {
            Cart c = new Cart();
            ps.setInt(1, c.getId());
            ps.setString(2, c.getProducts() + "");
            ps.setInt(3, c.getDeiveryAddress().getAddressId());

            success = ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }

        return success;
    }

    public boolean update() {
        boolean success = false;
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(UPDATE)) {
            Cart c = new Cart();
            ps.setString(1, c.getProducts()+"");
            ps.setInt(2, c.getDeiveryAddress().getAddressId());
            ps.setInt(3, c.getId());
            
            success=ps.executeUpdate()==1;
            
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
        return success;
    }

    public Cart selectById(int id){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Cart c = new Cart();
                    c.setId(rs.getInt(1));
                    c.setProducts(rs.getString(2));
                    c.getDeiveryAddress().setAddressId(3);
                }
            }
            
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return null;
        }
    }
}
