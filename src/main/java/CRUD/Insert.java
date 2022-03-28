package CRUD;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class Insert {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/basetest", "root", "1111");
             Statement statement = conn.createStatement()) {
            int row = statement.executeUpdate(generateInsert("Tom", BigDecimal.valueOf(177)));
            System.out.println(row + " - count row affected");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateInsert(String name, BigDecimal salary) {
        return "INSERT INTO EMPLOYEE (NAME, SALARY, CREATED_DATE)" +
                "VALUES ('" + name + "','" + salary + "','" + LocalDateTime.now() + "')";
    }
}
