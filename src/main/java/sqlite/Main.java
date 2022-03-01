package sqlite;


import java.io.File;
import java.sql.*;

public class Main {

    public static void createNewDatabase(String directoryName, String fileName) {

        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdir();
        }

        String url = "jdbc:sqlite:" + directoryName + "\\" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTable(String directoryName, String fileName, String tableName) {
        // SQLite connection string
        String url = "jdbc:sqlite:" + directoryName + "\\" + fileName;

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	capacity real\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            ((Statement) stmt).execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        createNewDatabase(
                "c:\\tmp\\sqlite_db",
                "test.db");
        createNewTable(
                "c:\\tmp\\sqlite_db",
                "test.db",
                "warehouses");
    }
}
