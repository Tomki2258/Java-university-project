package org.example;

import org.apache.commons.codec.digest.DigestUtils;

public class Authentication {
    private final UserRepository userRepository;

    Authentication(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public void DescribeUsers(){
        for (User user :userRepository.getUsers()){
            String hashed = DigestUtils.sha256Hex(user.GetPassword());
            System.out.println(hashed);
        }
    }
    public int CheckForUser(String nick){
        for (User user : userRepository.getUsers()){
            if(user.GetNick().equals(nick)){
                return userRepository.getUsers().indexOf(user);
            }
        }
        return -1;
    }
    public boolean CheckPassword(String password){
        for (User user : userRepository.getUsers()){
            if(user.GetPassword().equals(password)){
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
