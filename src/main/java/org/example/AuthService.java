package org.example;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.repositories.UserRepository;

import java.util.Scanner;

public class AuthService {
    private Authentication authentication;
    private UserRepository userRepository;

    public AuthService(UserRepository userRepository,Authentication authentication){
        this.authentication = authentication;
        this.userRepository = userRepository;
    }

    public User login(){

        Scanner scanner = new Scanner(System.in);
        String loginInput = scanner.nextLine();

        int result = authentication.CheckForUser(loginInput);
        if(result == -1){
            System.out.println("BŁĄD LOGOWANIA");
            return null;
        }
        String passwordInput = scanner.nextLine();

        boolean passwordResult = false;

        passwordResult = authentication.CheckPassword(DigestUtils.sha256Hex(passwordInput));

        if(!passwordResult) {
            System.out.println("BŁĄD LOGOWANIA");
            return null;
        }

        return userRepository.getUser(result);
    }
    public boolean register(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Login");
        String loginString = scanner.nextLine();

        if(userRepository.userExist(loginString)){
            System.out.println("TAKI UZYTKOWNIK ISTNIEJE");
            return false;
        }

        System.out.println("Haslo");
        String passwordString = scanner.nextLine();
        if(passwordString.equals("")) {
            System.out.println("HASLO NIE MOZE BYC PUSTE");
            return false;
        }
        String hashed = DigestUtils.sha256Hex(passwordString);
        User user = new User(loginString,hashed);
        userRepository.add(user);
        return true;
    }
}
