package org.example;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();

        Scanner scanner = new Scanner(System.in);
        String loginInput = scanner.nextLine();

        Authentication authentication = new Authentication(userRepository);

        int result = authentication.CheckForUser(loginInput);
        if(result == -1){
            System.out.println("BŁĄD LOGOWANIA");
            return;
        }
        String passwordInput = scanner.nextLine();

        boolean passwordResult = false;

        passwordResult = authentication.CheckPassword(DigestUtils.sha256Hex(passwordInput));

        if(!passwordResult) {
            System.out.println("BŁĄD LOGOWANIA");
            return;
        }

        App app = new App(userRepository.getUser(result),userRepository);

        app.mainProgram();

        userRepository.save();
    }
}