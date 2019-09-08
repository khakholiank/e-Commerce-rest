
package com.rgu.ecommerce.dao;

import com.rgu.ecommerce.config.UserType;
import com.rgu.ecommerce.model.User;
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
public class UserQuery {
    private static final String ADD = "INSERT INTO user(id,name,dob,user_type,occupation, password,default_address_id) VALUES(?,?,?,?,?,?,?)";
    
    private static final String UPDATE = "UPDATE user SET name=?,dob=?,user_type=?,occupation=?, password=?,default_address_id=? WHERE id=?";
    
    private static final String DELETE = "DELETE FROM user WHERE id=?";
    
    private static final String SELECT_BY_ID = "SELECT * FROM user WHERE id=?";
    
    private static final String SELECT_ALL = "SELECT * FROM user";
    
    public static boolean add(User u){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD)){
            ps.setInt(1, u.getId());
            ps.setString(2, u.getName());
            ps.setDate(3, java.sql.Date.valueOf(u.getBirthDate()));
            ps.setInt(4, u.getUserType().getCode());
            ps.setString(5, u.getOccupation());
            ps.setString(6, u.getPassword());
            ps.setInt(7, u.getDefaultAddress().getAddressId());
            
            success=ps.executeUpdate()==1;
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }
    
    public static boolean update(User u){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(UPDATE)){
            ps.setString(1, u.getName());
            ps.setDate(2, java.sql.Date.valueOf(u.getBirthDate()));
            ps.setInt(3, u.getUserType().getCode());
            ps.setString(4, u.getOccupation());
            ps.setString(5, u.getPassword());
            ps.setInt(6, u.getDefaultAddress().getAddressId());
            ps.setInt(7, u.getId());

            
            success=ps.executeUpdate()==1;
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }
    
    public static boolean delete(int id){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(DELETE)){
            ps.setInt(1, id);
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }

    
    private static User mapObject(ResultSet rs)throws SQLException{
        User u = new User();
            u.setId(rs.getInt(1));
            u.setName(rs.getString(2));
            u.setBirthDate(rs.getDate(3).toLocalDate());
            u.setUserType(UserType.valueOf(rs.getInt(4)));
            u.setOccupation(rs.getString(5));
            u.setPassword(rs.getString(6));
            u.getDefaultAddress().setAddressId(rs.getInt(7));
        return u;
    }
    
    public static User selectUserById(int id){
        User u = new User();
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    u=mapObject(rs);
                }
            }
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return null;
        }
        return u;
    }
    
    public static List<User> selectAll(int id){
        List<User> list = new ArrayList<>();
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_ALL)){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    list.add(mapObject(rs));
                }
            }
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return null;
        }
        return list;
    }
}
