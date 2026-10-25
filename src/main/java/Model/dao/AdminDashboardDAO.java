package Model.dao;

import DBConnection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class AdminDashboardDAO {

    // 1. Payment Pie Chart එක සඳහා දත්ත ලබාගැනීම
    public Map<String, Integer> getPaymentStatusCounts() {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("Paid", 0);
        counts.put("Not Paid", 0);
        counts.put("Exempted", 0);
        
        String query = "SELECT fee_status, COUNT(student_id) as total FROM student_fee_status GROUP BY fee_status";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (Statement stm = con.createStatement(); ResultSet rst = stm.executeQuery(query)) {
                while (rst.next()) {
                    counts.put(rst.getString("fee_status"), rst.getInt("total"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counts;
    }

    // 2. Users Pie/Bar Chart එක සඳහා Role අනුව ගණන ලබාගැනීම (Admin, Teacher, Student)
    public Map<String, Integer> getUserRoleCounts() {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("Admin", 0);
        counts.put("Teacher", 0);
        counts.put("Student", 0);
        
        String query = "SELECT role, COUNT(user_id) as total FROM users GROUP BY role";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (Statement stm = con.createStatement(); ResultSet rst = stm.executeQuery(query)) {
                while (rst.next()) {
                    counts.put(rst.getString("role"), rst.getInt("total"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counts;
    }
}