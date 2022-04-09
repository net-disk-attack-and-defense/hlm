package main.CURD;

import java.sql.*;

public class JDBC {
    private static String JDBC_URL = "jdbc:mysql://localhost:3306/mypan?serverTimezone=UTC";
    private static String JDBC_USER = "root";
    private static String JDBC_PASSWORD = "123456";
    static Connection conn;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL,JDBC_USER, JDBC_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean create_user(String userName, String password, String email){
        try {
            PreparedStatement ps = conn.prepareStatement("Insert into users (name, passwd,email) value (?,?,?)");
            ps.setObject(1, userName);
            ps.setObject(2,password);
            ps.setObject(3, email);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String Retrieve_user(String email, String password) {
        try {
            PreparedStatement ps = conn.prepareStatement("select passwd from users where email=?");
            ps.setObject(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String pw = rs.getString("passwd");
                if (pw.equals(password)) {
                    PreparedStatement preparedStatement = conn.prepareStatement("select name from users where email=?");
                    preparedStatement.setObject(1, email);
                    ResultSet ns = preparedStatement.executeQuery();
                    if(ns.next()){
                        String name = ns.getString("name");
                        return name;
                    };
                    return null;
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static boolean Retrieve_user(String email) {
        try {
            PreparedStatement ps = conn.prepareStatement("select passwd from users where email=?");
            ps.setObject(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
