package com.rgu.ecommerce.dao;

import com.rgu.ecommerce.dao.commons.AddressQuery;
import com.rgu.ecommerce.dao.conn.Conn;
import com.rgu.ecommerce.model.Seller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author nikitkhakholia
 */
public class SellerQuery {

//    private int id;
//    private String businessName;
//    private String sellerName;
//    private LocalDate birthDate;
//    private String password;
//    private Address address;
//    private String GSTIN;
//    private String PAN;
    private static final String ADD = "INSERT INTO seller VALUES(?,?,?,?,?,?,?,?)";

    private static final String SELECT_SELLER_BY_ID = "SELECT * FROM seller WHERE id=?";
    public static boolean addSeller(Seller seller) {
        boolean success = false;
        try (Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(ADD)) {
            ps.setInt(1, seller.getId());
            ps.setString(2, seller.getBusinessName());
            ps.setString(3, seller.getSellerName());
            ps.setDate(4, java.sql.Date.valueOf(seller.getBirthDate()));
            ps.setString(5, seller.getPassword());
            ps.setInt(6, seller.getAddress().getAddressId());
            ps.setString(7, seller.getGSTIN());
            ps.setString(8, seller.getPAN());

            success = ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            System.err.println("Seller Add Error: " + ex.toString());
        }
        return success;
    }
    
    
    
    
    
    
    
    
    
    
    private static Seller mapObject(ResultSet rs)throws SQLException{
        Seller seller = new Seller();
        seller.setId(rs.getInt(1));
        seller.setBusinessName(rs.getString(2));
        seller.setSellerName(rs.getString(3));
        seller.setBirthDate(rs.getDate(4).toLocalDate());
        seller.setAddress(AddressQuery.selectAddressById(rs.getInt(6)));
        seller.setGSTIN(rs.getString(7));
        seller.setPAN(rs.getString(8));
        return seller;
    }
    
    public static Seller selectSellerById(int id){
        Seller seller = new Seller();
        try(Connection con = Conn.getConnection();
                PreparedStatement ps = con.prepareStatement(SELECT_SELLER_BY_ID)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    seller = mapObject(rs);
                }
            }
        }catch(SQLException ex){
            System.err.println("Seller Get By Id Error: "+ ex.toString());
        }
        return seller;
    }
}
