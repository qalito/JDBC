package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/basetest", "root", "1111");
             Statement statement = conn.createStatement()) {
            int row = statement.executeUpdate(deleteByName("Tom"));
            System.out.println(row + " - count row affected");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String deleteByName(String name) {
        return "DELETE FROM EMPLOYEE WHERE NAME = '" + name + "'";
    }
}
