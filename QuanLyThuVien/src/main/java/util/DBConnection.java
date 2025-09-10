package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL =
        "jdbc:sqlserver://localhost:1433;databaseName=QuanLyThuVien;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
}
