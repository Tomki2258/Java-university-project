package org.example;

import java.util.List;

public interface IUserRepository {
    User getUser(int index);
    List<User> getUsers();
    void save();
}
