package org.example;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.repositories.UserService;

import java.util.Scanner;
import java.util.UUID;

public class AuthService {
    private Authentication authentication;
    private UserService userService;

    public AuthService(UserService userService, Authentication authentication) {
        this.authentication = authentication;
        this.userService = userService;
    }

    public User login() {

        Scanner scanner = new Scanner(System.in);
        String loginInput = scanner.nextLine();

        int result = authentication.CheckForUser(loginInput);
        if (result == -1) {
            System.out.println("BŁĄD LOGOWANIA");
            return null;
        }
        String passwordInput = scanner.nextLine();

        boolean passwordResult = false;

        //passwordResult = authentication.CheckPassword(DigestUtils.sha256Hex(passwordInput));
        //String hashed = BCrypt.hashpw(passwordInput, BCrypt.gensalt());

        passwordResult = authentication.CheckPassword(passwordInput);

        if (!passwordResult) {
            System.out.println("BŁĄD LOGOWANIA");
            return null;
        }

        return userService.getUser(result);
    }

    public boolean register() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Login");
        String loginString = scanner.nextLine();

        if (userService.userExist(loginString)) {
            System.out.println("TAKI UZYTKOWNIK ISTNIEJE");
            return false;
        }

        System.out.println("Haslo");
        String passwordInput = scanner.nextLine();
        if (passwordInput.equals("")) {
            System.out.println("HASLO NIE MOZE BYC PUSTE");
            return false;
        }
        String hashed = DigestUtils.sha256Hex(passwordInput);
        UUID uuid = UUID.randomUUID();

        User user = new User(uuid.toString(), loginString, hashed);
        userService.add(user);
        userService.save();
        return true;
    }
}
