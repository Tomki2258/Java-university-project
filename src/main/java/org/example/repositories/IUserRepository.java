package org.example.repositories;

import org.example.User;

import java.util.List;

public interface IUserRepository {
    User getUser(int index);
    List<User> getUsers();
    void save();
    void add(User user);
    boolean userExist(String nick);
    void saveJson();
}
