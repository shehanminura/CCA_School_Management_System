package DBConnection;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DBTableConnection {

    // Create Tables
    public static void generateTables(Connection conn) {
        try (Statement stmt = conn.createStatement()) {

            // 1. users table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users ("
                    + "user_id INT PRIMARY KEY, "
                    + "email VARCHAR(100) UNIQUE NOT NULL, "
                    + "password VARCHAR(255) NOT NULL, "
                    + "role ENUM('Admin', 'Teacher', 'Student') NOT NULL, "
                    + "status ENUM('Active', 'Deactivated') DEFAULT 'Active'"
                    + ")");

            // 2. class table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS class ("
                    + "class_id VARCHAR(20) PRIMARY KEY, "
                    + "class_name VARCHAR(50) NOT NULL"
                    + ")");

            // 3. subject table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS subject ("
                    + "subject_id VARCHAR(20) PRIMARY KEY, "
                    + "subject_name VARCHAR(50) NOT NULL"
                    + ")");

            // 4. student table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS student ("
                    + "student_id VARCHAR(20) PRIMARY KEY, "
                    + "user_id INT UNIQUE NOT NULL, "
                    + "name VARCHAR(100) NOT NULL, "
                    + "birthday DATE, "
                    + "contact_number VARCHAR(15), "
                    + "address VARCHAR(255), "
                    + "gender ENUM('Male', 'Female', 'Other'), "
                    + "registration_date DATETIME NOT NULL, "
                    + "class_id VARCHAR(20), "
                    + "FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE, "
                    + "FOREIGN KEY (class_id) REFERENCES class(class_id) ON DELETE SET NULL"
                    + ")");

            // 5. teacher table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS teacher("
                    + "teacher_id VARCHAR(50) PRIMARY KEY, "
                    + "user_id INT UNIQUE NOT NULL, "
                    + "name VARCHAR(150) NOT NULL, "
                    + "nic VARCHAR(20) UNIQUE NOT NULL, "
                    + "birthday DATE, "
                    + "contact_number VARCHAR(20), "
                    + "address VARCHAR(255), "
                    + "gender ENUM('Male', 'Female', 'Other'), "
                    + "salary DECIMAL(10,2), "
                    + "registration_date DATETIME NOT NULL, "
                    + "FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE"
                    + ")");

            // 6. admin table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS admin ("
                    + "admin_id VARCHAR(50) PRIMARY KEY, "
                    + "user_id INT UNIQUE NOT NULL, "
                    + "name VARCHAR(150) NOT NULL, "
                    + "nic VARCHAR(20) UNIQUE NOT NULL, "
                    + "birthday DATE, "
                    + "contact_number VARCHAR(20), "
                    + "address VARCHAR(255), "
                    + "gender ENUM('Male', 'Female', 'Other'), "
                    + "salary DECIMAL(10,2), "
                    + "registration_date DATETIME NOT NULL, "
                    + "FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE"
                    + ")");

            // 7. student_fee_status table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS student_fee_status ("
                    + "status_id VARCHAR(20) PRIMARY KEY, "
                    + "student_id VARCHAR(20) NOT NULL, "
                    + "class_id VARCHAR(20) NOT NULL, "
                    + "fee_status ENUM('Paid', 'Not Paid', 'Exempted') DEFAULT 'Not Paid', "
                    + "remark VARCHAR(255) DEFAULT 'Not Applicable', "
                    + "updated_date DATETIME, "
                    + "FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE, "
                    + "FOREIGN KEY (class_id) REFERENCES class(class_id) ON DELETE CASCADE"
                    + ")");

            // 8. assignment table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS assignment ("
                    + "assignment_id VARCHAR(20) PRIMARY KEY, "
                    + "subject_id VARCHAR(20) NOT NULL, "
                    + "title VARCHAR(100) NOT NULL, "
                    + "description TEXT, "
                    + "assignment_link VARCHAR(255) NOT NULL, "
                    + "due_date DATE NOT NULL, "
                    + "FOREIGN KEY (subject_id) REFERENCES subject(subject_id) ON UPDATE CASCADE ON DELETE CASCADE"
                    + ")");

            // Default Admin Access
            stmt.executeUpdate("INSERT IGNORE INTO users (user_id, email, password, role, status) VALUES "
                    + "(1, 'admin@gmail.com', '123456', 'Admin', 'Active')");
            
            // Default Admin Info
            stmt.executeUpdate("INSERT IGNORE INTO admin (admin_id, user_id, name, nic, birthday, contact_number, address, gender, salary, registration_date) VALUES "
                    + "('CCAA001', 6, 'System Admin', '000000000000', '1990-01-01', '0700000000', 'System Configuration', 'Other', 0.00, NOW())");

            // Default Classes
            stmt.executeUpdate("INSERT IGNORE INTO class (class_id, class_name) VALUES "
                    + "('CCAC001', 'Grade 10 - A'),"
                    + "('CCAC002', 'Grade 10 - B'),"
                    + "('CCAC003', 'Grade 11 - A')");

            // Default Subjects
            stmt.executeUpdate("INSERT IGNORE INTO subject (subject_id, subject_name) VALUES "
                    + "('CCASU001', 'Mathematics'),"
                    + "('CCASU002', 'Science'),"
                    + "('CCASU003', 'English')");

            System.out.println("All tables and default admin user created successfully!");

        } catch (SQLException e) {
            System.err.println("Error generating tables: " + e.getMessage());
        }
    }
}