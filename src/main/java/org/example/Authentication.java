package org.example;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.repositories.UserService;

public class Authentication {
    private final UserService userService;

    public Authentication(UserService userService){
        this.userService = userService;
    }
    public void DescribeUsers(){
        for (User user : userService.getUsers()){
            String hashed = DigestUtils.sha256Hex(user.getPassword());
            System.out.println(hashed);
        }
    }
    public int CheckForUser(String nick){
        for (User user : userService.getUsers()){
            if(user.GetNick().equals(nick)){
                return userService.getUsers().indexOf(user);
            }
        }
        return -1;
    }
    public boolean CheckPassword(String password){
        for (User user : userService.getUsers()){
            if(user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}

/*
String value = "haselko";
String sha256hex = DigestUtils.sha256Hex(value);
System.out.println(sha256hex);
 */
