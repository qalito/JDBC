package CRUD;

import java.math.BigDecimal;
import java.sql.*;

 class Select {
    public static void main(String[] args) {
        String sql = "SELECT * FROM EMPLOYEE";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/basetest", "root", "1111");
             Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                BigDecimal salary = resultSet.getBigDecimal("SALARY");
                Timestamp createdDate = resultSet.getTimestamp("CREATEDDATE");
                Employee obj = new Employee();
                obj.setId(id);
                obj.setName(name);
                obj.setSalary(salary);
                obj.setCreatedDate(createdDate.toLocalDateTime());
                System.out.println(obj);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
