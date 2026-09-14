package Model.dao;

import Model.entity.AdminEntity;
import DBConnection.DBConnection;
import DateandTimeConnection.DateandTimeConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    public String generateNextAdminId() {
        String nextId = "CCAA001";
        String query = "SELECT admin_id FROM admin ORDER BY admin_id DESC LIMIT 1";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (Statement stm = con.createStatement(); ResultSet rst = stm.executeQuery(query)) {
                if (rst.next()) {
                    String lastId = rst.getString("admin_id");
                    int numericPart = Integer.parseInt(lastId.replace("CCAA", ""));
                    numericPart++;
                    nextId = String.format("CCAA%03d", numericPart);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return nextId;
    }

    public boolean addAdmin(AdminEntity admin) {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            int nextUserId = 1;
            String getMaxIdQuery = "SELECT MAX(user_id) FROM users";
            try (Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(getMaxIdQuery)) {
                if (rs.next() && rs.getObject(1) != null) nextUserId = rs.getInt(1) + 1;
            }

            String userQuery = "INSERT INTO users (user_id, email, password, role, status) VALUES (?, ?, ?, 'Admin', ?)";
            PreparedStatement userPst = con.prepareStatement(userQuery);
            userPst.setInt(1, nextUserId);
            userPst.setString(2, admin.getEmail());
            userPst.setString(3, admin.getPassword());
            userPst.setString(4, admin.getStatus());

            if (userPst.executeUpdate() == 0) { con.rollback(); return false; }

            String currentDateTime = DateandTimeConnection.getInstance().getCurrentDateTime();
            String adminQuery = "INSERT INTO admin (admin_id, user_id, name, nic, birthday, contact_number, address, gender, salary, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement adminPst = con.prepareStatement(adminQuery);
            adminPst.setString(1, admin.getAdminId());
            adminPst.setInt(2, nextUserId);
            adminPst.setString(3, admin.getName());
            adminPst.setString(4, admin.getNic());
            adminPst.setString(5, admin.getBirthday());
            adminPst.setString(6, admin.getContactNumber());
            adminPst.setString(7, admin.getAddress());
            adminPst.setString(8, admin.getGender());
            adminPst.setDouble(9, admin.getSalary());
            adminPst.setString(10, currentDateTime);

            if (adminPst.executeUpdate() > 0) {
                con.commit(); return true;
            } else { con.rollback(); return false; }

        } catch (Exception e) {
            if (con != null) try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace(); return false;
        } finally {
            if (con != null) try { con.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

    public List<AdminEntity> getAllAdmins() {
        List<AdminEntity> admins = new ArrayList<>();
        String query = "SELECT a.admin_id, a.name, a.nic, a.birthday, a.contact_number, u.email, a.address, a.gender, a.salary, u.password, u.status, a.registration_date " +
                       "FROM admin a JOIN users u ON a.user_id = u.user_id";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (Statement stm = con.createStatement(); ResultSet rst = stm.executeQuery(query)) {
                while (rst.next()) {
                    AdminEntity admin = new AdminEntity(
                        rst.getString("admin_id"), rst.getString("name"), rst.getString("nic"), rst.getString("birthday"),
                        rst.getString("contact_number"), rst.getString("email"), rst.getString("address"), rst.getString("gender"),
                        rst.getDouble("salary"), rst.getString("password"), rst.getString("status")
                    );
                    admin.setRegistrationDate(rst.getString("registration_date"));
                    admins.add(admin);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return admins;
    }

    public AdminEntity searchAdmin(String adminId) {
        String query = "SELECT a.admin_id, a.name, a.nic, a.birthday, a.contact_number, u.email, a.address, a.gender, a.salary, u.password, u.status " +
                       "FROM admin a JOIN users u ON a.user_id = u.user_id WHERE a.admin_id = ?";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, adminId);
                try (ResultSet rst = pst.executeQuery()) {
                    if (rst.next()) {
                        return new AdminEntity(
                            rst.getString("admin_id"), rst.getString("name"), rst.getString("nic"), rst.getString("birthday"),
                            rst.getString("contact_number"), rst.getString("email"), rst.getString("address"), rst.getString("gender"),
                            rst.getDouble("salary"), rst.getString("password"), rst.getString("status")
                        );
                    }
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public boolean updateAdmin(AdminEntity admin) {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            String userQuery = "UPDATE users SET email=?, password=?, status=? WHERE user_id = (SELECT user_id FROM admin WHERE admin_id=?)";
            PreparedStatement userPst = con.prepareStatement(userQuery);
            userPst.setString(1, admin.getEmail());
            userPst.setString(2, admin.getPassword());
            userPst.setString(3, admin.getStatus());
            userPst.setString(4, admin.getAdminId());
            userPst.executeUpdate();

            String adminQuery = "UPDATE admin SET name=?, nic=?, birthday=?, contact_number=?, address=?, gender=?, salary=? WHERE admin_id=?";
            PreparedStatement adminPst = con.prepareStatement(adminQuery);
            adminPst.setString(1, admin.getName());
            adminPst.setString(2, admin.getNic());
            adminPst.setString(3, admin.getBirthday());
            adminPst.setString(4, admin.getContactNumber());
            adminPst.setString(5, admin.getAddress());
            adminPst.setString(6, admin.getGender());
            adminPst.setDouble(7, admin.getSalary());
            adminPst.setString(8, admin.getAdminId());

            if (adminPst.executeUpdate() > 0) { con.commit(); return true;
            } else { con.rollback(); return false; }
        } catch (Exception e) {
            if (con != null) try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace(); return false;
        } finally {
            if (con != null) try { con.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

    public boolean deleteAdmin(String adminId) {
        String query = "DELETE FROM users WHERE user_id = (SELECT user_id FROM admin WHERE admin_id = ?)";
        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, adminId);
                return pst.executeUpdate() > 0;
            }
        } catch (Exception e) { e.printStackTrace(); return false; }
    }
}