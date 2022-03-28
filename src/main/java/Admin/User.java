package Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String login;
    private String name;
    private long age;
    private Role role;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User mapObject(ResultSet resultSet) throws SQLException {
        this.setLogin(resultSet.getString("LOGIN"));
        this.setName(resultSet.getString("NAME"));
        this.setAge(resultSet.getInt("AGE"));
        this.setRole(Role.valueOf(resultSet.getString("ROLE")));
        this.setPassword(resultSet.getString("PASSWORD"));
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", role=" + role +
                ", password='" + password + '\'' +
                '}';
    }
}
