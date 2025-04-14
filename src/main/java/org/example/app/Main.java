package org.example.app;

import org.example.AuthService;
import org.example.Authentication;
import org.example.User;
import org.example.repositories.UserService;

import java.util.Scanner;

public class Main {
    public static boolean jsonMode = true;
    public static void main(String[] args) {
        /*
        try(Connection connection = JdbcConnectionManager.getInstance().getConnection();
            Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT * FROM vehicle");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        jsonMode = switch (input) {
            case "B" -> false;
            default -> jsonMode;
        };
        UserService userService = new UserService();

        Authentication authentication = new Authentication(userService);
        AuthService authService = new AuthService(userService,authentication);
        //String passwordInput = "kox";
        //System.out.println(DigestUtils.sha256Hex(passwordInput));
        System.out.println("1:Logowanie\n2:Rejestracja");
        User user = null;
        int option = scanner.nextInt();
        switch (option){
            case 1:
                user = authService.login();
                break;
            case 2:
                authService.register();
                break;
        }
        if(user == null) return;
        App app = new App(user, userService);

        app.mainProgram();

        userService.save();
    }
}