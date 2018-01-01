/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Date;
import model.Student;

/**
 *
 * @author Blank
 */
public class serverDAO {

    private Connection conn;

    public serverDAO() {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            String url="jdbc:mysql://localhost:3306/mydatabase";
//            conn=DriverManager.getConnection(url+"?useUnicode=true&chatacterEncoding=utf-8", "root", "12345678");
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase"
                    + "?useUnicode=true&characterEncoding=utf-8", "root", "12345678");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean addStudent(Student ss) {
        String sql = "INSERT INTO tblstudent(id,name,address,year,dob) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setInt(1, ss.getId());
            ps.setString(2, ss.getName());
            ps.setString(3, ss.getAddress());
            ps.setInt(4, ss.getYear());
            ps.setDate(5, new Date(ss.getDob().getTime()));
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
