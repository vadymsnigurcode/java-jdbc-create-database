package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    static final String DB_URL = "jdbc:mysql://localhost:3307/";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        // Open a connection

        String[] queries = new String[] {
                "DROP DATABASE IF EXISTS STUDENTS;",
                "CREATE DATABASE IF NOT EXISTS STUDENTS;"
        };
        for (String query:queries) {
            executeQuery(query);
        };
    }

    private static void executeQuery(String query) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            //String sql = "CREATE DATABASE STUDENTS";
            String sql = query;
            stmt.executeUpdate(sql);
            System.out.println("Database query: " + sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
