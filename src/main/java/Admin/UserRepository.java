package Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/basetest";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "1111";

    public List<User> executeQuery(String sql) {
        List<User> userList = new ArrayList<User>();
        try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASSWORD); Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.mapObject(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public int executeUpdate(String sql, User user) {
        int row = 0;
        try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASSWORD); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getName());
            statement.setLong(3, user.getAge());
            statement.setString(4, String.valueOf(user.getRole()));
            statement.setString(5, user.getPassword());
            row = statement.executeUpdate();
            System.out.println(row + " - count row affected");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
}
