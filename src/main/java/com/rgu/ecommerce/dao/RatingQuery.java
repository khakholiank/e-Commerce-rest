
package com.rgu.ecommerce.dao;

import com.rgu.ecommerce.model.Rating;
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
public class RatingQuery {
    private static final String ADD = "INSERT INTO rating(id,rating_id,score,description)VALUES(?,?,?)";
    
    private static final String UPDATE = "UPDATE rating SET id=?,score=?, description=? WHERE rating_id=?";
    
    private static final String DELETE = "DELETE FROM rating WHERE rating_id=?";
    
    private static final String SELECT_BY_ID = "SELECT * FROM rating WHERE id=?";
    
    private static final String SELECT_BY_RATING_ID = "SELECT * FROM rating WHERE rating_id=?";
    
//    private static final String SELECT_AVERAGE
    
    public static boolean add(Rating r){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD)){
            ps.setInt(1, r.getId());
            ps.setInt(2, r.getRatingId());
            ps.setInt(3, r.getScore());
            ps.setString(4, r.getDescription());
            
            success = ps.executeUpdate()==1;
            
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return success;
        }
        return success;
    }
    
    public static boolean update(Rating r){
        boolean success = false;
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(UPDATE)){
            ps.setInt(1, r.getId());
            ps.setInt(2, r.getScore());
            ps.setString(3, r.getDescription());
            ps.setInt(4, r.getRatingId());

            success = ps.executeUpdate()==1;
            
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
            success = ps.executeUpdate()==1;
        }catch(SQLException ex){
            System.out.println(ex.toString());
            return success;
        }
        return success;
    }
    private static Rating mapObject(ResultSet rs)throws SQLException{
        Rating r = new Rating();
        r.setId(rs.getInt(1));
        r.setRatingId(rs.getInt(2));
        r.setScore(rs.getInt(3));
        r.setDescription(rs.getString(4));
        return r;
    }
    
    public static List<Rating> selectByUserId(int id){
        List<Rating> list = new ArrayList<>();
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)){
            ps.setInt(1, id);
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
    
    public static Rating selectByRatingId(int id){
        Rating r = new Rating();
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_BY_RATING_ID)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    r=mapObject(rs);
                }
            }
        }catch(SQLException ex){
            System.err.println(ex.toString());
            return null;
        }
        return r;
    }
}
