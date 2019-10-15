
package com.rgu.ecommerce.dao.commons;

import com.rgu.ecommerce.dao.conn.Conn;
import com.rgu.ecommerce.model.commons.Country;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author nikitkhakholia
 */
public class CountryQuery {
    private static String ADD = "INSERT INTO country(country_name)VALUES(?)";

    private static String DELETE = "DELETE FROM country where country_id =?";

    private static String SELECT_BY_ID = "SELECT * FROM country WHERE country_id=?";

    public static boolean add(Country c) {
        boolean success = false;
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getCountryName());
            try (ResultSet rs = ps.getGeneratedKeys()) {
                c.setCountryId(rs.getInt(1));
            }
            success = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Error... " + e.toString());
        }
        return success;
    }

    public static boolean delete(int id) {
        boolean success = false;
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            success = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Error... " + e.toString());
        }
        return success;
    }

    public static Country selectById(int id) {
        Country c = new Country();
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    c.setCountryId(rs.getInt(1));
                    c.setCountryName(rs.getString(2));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error... " + e.toString());
        }
        return c;
    }
}
