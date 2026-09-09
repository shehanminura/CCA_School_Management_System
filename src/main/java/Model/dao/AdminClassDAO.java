package Model.dao;

import Model.entity.ClassEntity;
import DBConnection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminClassDAO {
    
    // Database එකෙන් සියලුම පන්ති ගෙන ඒමේ මෙතඩ් එක
    public List<ClassEntity> getAllClasses() {
        List<ClassEntity> classList = new ArrayList<>();
        String query = "SELECT class_id, class_name FROM class";

        try {
            Connection con = DBConnection.getInstance().getConnection();
            try (Statement stm = con.createStatement();
                 ResultSet rs = stm.executeQuery(query)) {

                while (rs.next()) {
                    ClassEntity clz = new ClassEntity(
                        rs.getString("class_id"), 
                        rs.getString("class_name")
                    );
                    classList.add(clz);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classList;
    }
}