package Admin;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();
        Scanner scanner = new Scanner(System.in);
        Boolean menu = true, userMenu = true;
        User currentUser = null;
        while (menu) {
            System.out.println("Select: login / register / 0 - exit app:");
            switch (scanner.next()) {
                case "login":
                    try {
                        String login, password;
                        System.out.println("Login: ");
                        login = scanner.next();
                        System.out.println("Password: ");
                        password = scanner.next();
                        currentUser = userController.loginUser(login, password);
                    } catch (Exception e) {
                        System.out.println("There is no user with such login and password");
                        break;
                    }
                    while (userMenu) {
                        System.out.println("Select: about/ list /another - exit account");
                        switch (scanner.next()) {
                            case "about":
                                System.out.println(currentUser);
                                break;
                            case "list":
                                try {
                                    System.out.println(userController.getListOfUsers(currentUser));
                                } catch (Exception e) {
                                    System.err.println(e.getMessage());
                                }
                                break;
                            default:
                                currentUser = null;
                                System.out.println("exit account");
                                userMenu = false;
                                break;
                        }
                    }
                    break;
                case "register":
                    userController.registeredUser();
                    break;
                case "0":
                    menu = false;
                    break;
                default:
                    break;
            }
        }
    }
}
