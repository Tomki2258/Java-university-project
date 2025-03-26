package org.example.app;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.AuthService;
import org.example.Authentication;
import org.example.User;
import org.example.repositories.UserRepository;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();

        Authentication authentication = new Authentication(userRepository);
        AuthService authService = new AuthService(userRepository,authentication);

        System.out.println("1:Logowanie\n2:Rejestracja");
        Scanner scanner = new Scanner(System.in);
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
        App app = new App(user,userRepository);

        app.mainProgram();

        userRepository.save();
    }
}