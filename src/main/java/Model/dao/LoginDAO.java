package Model.dao;

import DBConnection.DBConnection;
import Model.entity.UserEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    
    private Connection conn;

    public LoginDAO() throws SQLException {
        this.conn = DBConnection.getInstance().getConnection();
    }

    // මෙතැනදී Return කරන්නේ UserEntity එකක්
    public UserEntity authenticateUser(String email, String password) {
        String sql = "SELECT user_id, email, password, role, status FROM users WHERE email=? AND password=?";
        
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, email);
            pst.setString(2, password);
            
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                // Database එකෙන් එන දත්ත අරන් අලුත් Entity පෙට්ටියකට දානවා
                return new UserEntity(
                    rs.getString("user_id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role"), 
                    rs.getString("status")
                );
            }
        } catch (Exception e) {
            System.out.println("Login Database Error: " + e.getMessage());
        }
        return null; 
    }
}