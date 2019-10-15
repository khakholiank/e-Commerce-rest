
package com.rgu.ecommerce.dao.commons;

import com.rgu.ecommerce.dao.UserQuery;
import com.rgu.ecommerce.dao.conn.Conn;
import com.rgu.ecommerce.model.commons.Address;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nikitkhakholia
 */
public class AddressQuery {
    private static String ADD = "INSERT INTO address(line_1, line_2, locality_id, phone, user_id) VALUES(?,?,?,?,?)";
    
    private static String UPDATE = "UPDATE address SET line_1=?, line_2=?, locality_id=?, phone=?, user_id=? WHERE id =?";
    
    private static String DELETE = "DELETE FROM address WHERE id=?";
    
    private static String SELECT_BY_ID = "SELECT * FROM address WHERE id=?";
    
    private static String SELECT_BY_USER_ID = "SELECT * FROM address WHERE user_id=?";
    

    public static boolean add(Address a){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, a.getLine1());
            ps.setString(2, a.getLine2());
            ps.setInt(3, a.getLocality().getLocalityId());
            ps.setInt(4, a.getPhone());
            ps.setInt(5, a.getUserId().getId());
            
            try(ResultSet rs = ps.getGeneratedKeys()){
                    if(rs.next()){
                        a.setAddressId(rs.getInt(1));
                    }
                }
            success = ps.executeUpdate()==1;
        }catch(SQLException e){
            System.err.println("Error... "+e.toString());
        }
        return success;
    }
    
    public static boolean update(Address a){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD)){
            ps.setString(1, a.getLine1());
            ps.setString(2, a.getLine2());
            ps.setInt(3, a.getLocality().getLocalityId());
            ps.setInt(4, a.getPhone());
            ps.setInt(5, a.getUserId().getId());
            
            ps.setInt(6, a.getUserId().getId());
            success = ps.executeUpdate()==1;
        }catch(SQLException e){
            System.err.println("Error... "+e.toString());
        }
        return success;
    }


    private static Address mapObject(ResultSet rs)throws SQLException{
        Address a = new Address();
        a.setAddressId(rs.getInt(1));
        a.setLine1(rs.getString(2));
        a.setLine2(rs.getString(3));
        a.setLocality(LocalityQuery.selectById(rs.getInt(4)));
        a.setPhone(rs.getInt(5));
        a.setUserId(UserQuery.selectUserById(rs.getInt(6)));
        return a;
    }
    
    public static Address selectAddressById(int id){
        Address a = new Address();
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    a=mapObject(rs);
                }
            }
        }catch(SQLException e){
            System.err.println("Error... "+e.toString());
        }
        return a;
    }
    
    public static List<Address> selectAddressByUserId(int id){
        List<Address> addresses = new ArrayList<>();
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_USER_ID)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    addresses.add(mapObject(rs));
                }
            }
        }catch(SQLException e){
            System.err.println("Error... "+e.toString());
        }
        return addresses;
    }

    

}
