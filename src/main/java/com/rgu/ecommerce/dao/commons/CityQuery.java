package com.rgu.ecommerce.dao.commons;

import com.rgu.ecommerce.dao.conn.Conn;
import com.rgu.ecommerce.model.commons.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author nikitkhakholia
 */
public class CityQuery {

    private static String ADD = "INSERT INTO city(city_name)VALUES(?)";

    private static String DELETE = "DELETE FROM city where city_id =?";

    private static String SELECT_BY_ID = "SELECT * FROM city WHERE city_id=?";

    public static boolean add(City c) {
        boolean success = false;
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getCityName());
            try (ResultSet rs = ps.getGeneratedKeys()) {
                c.setCityId(rs.getInt(1));
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

    public static City selectById(int id) {
        City c = new City();
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    c.setCityId(rs.getInt(1));
                    c.setCityName(rs.getString(2));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error... " + e.toString());
        }
        return c;
    }
}