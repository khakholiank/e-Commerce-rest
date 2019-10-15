
package com.rgu.ecommerce.dao.commons;

import com.rgu.ecommerce.dao.conn.Conn;
import com.rgu.ecommerce.model.commons.Locality;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author nikitkhakholia
 */
public class LocalityQuery {
    
    private static String ADD = "INSERT INTO locality(locality_name, state_id, country_id, city_id)VALUES(?,?,?,?)";

    private static String DELETE = "DELETE FROM city where city_id =?";

    private static String SELECT_BY_ID = "SELECT * FROM city WHERE city_id=?";

    public static boolean add(Locality c) {
        boolean success = false;
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getLocalityName());
            ps.setInt(2, c.getState().getStateId());
            ps.setInt(3, c.getCountry().getCountryId());
            ps.setInt(4, c.getCity().getCityId());
            try (ResultSet rs = ps.getGeneratedKeys()) {
                c.setLocalityId(rs.getInt(1));
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

    public static Locality selectById(int id) {
        Locality c = new Locality();
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    c.setLocalityId(rs.getInt(1));
                    c.setLocalityName(rs.getString(2));
                    c.setState(StateQuery.selectById(rs.getInt(3)));
                    c.setCountry(CountryQuery.selectById(rs.getInt(4)));
                    c.setCity(CityQuery.selectById(rs.getInt(5)));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error... " + e.toString());
        }
        return c;
    }
}
