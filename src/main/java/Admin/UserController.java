package Admin;

import java.util.List;
import java.util.Scanner;

public class UserController {
    private UserRepository userRepository = new UserRepository();

    public List<User> getListOfUsers(User currentUser) throws Exception {
        List<User> users;
        if (currentUser.getRole() == Role.ADMIN) {
            users = userRepository.executeQuery("SELECT * FROM USER");
        } else {
            throw new Exception("USER is not ADMIN");
        }
        return users;
    }

    public void registeredUser() {
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Registered user:");
        try {
            System.out.println("Login:");
            user.setLogin(scanner.next());
            System.out.println("Name:");
            user.setName(scanner.next());
            System.out.println("Age:");
            user.setAge(scanner.nextInt());
            System.out.println("Role:ADMIN/USER");
            user.setRole(Role.valueOf(scanner.next()));
            System.out.println("Password:");
            user.setPassword(scanner.next());
        } catch (Exception e) {
            System.out.println("Enter uncorrected data, please repeat");
            registeredUser();
        }
        userRepository.executeUpdate("INSERT INTO USER (LOGIN, NAME, AGE, ROLE, PASSWORD) VALUES (?,?,?,?,?)", user);
    }

    public User loginUser(String login, String password) throws Exception {
        List<User> users = userRepository.executeQuery("SELECT * FROM USER WHERE LOGIN LIKE '" + login + "' AND PASSWORD LIKE '" + password + "'");
        if (users.size() == 0) {
            throw new Exception("There is no user with such login and password");
        }
        return users.get(0);
    }

}
