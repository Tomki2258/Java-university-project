package org.example.repositories.Json;

import com.google.gson.reflect.TypeToken;
import org.example.User;

import java.util.ArrayList;
import java.util.List;

public class UsersJsonRepository {
    private final String USERS_PATH = "src/data/users.json";
    private final com.umcsuser.carrent.utils.JsonFileStorage storage =
            new com.umcsuser.carrent.utils.JsonFileStorage<>(USERS_PATH, new TypeToken<List<User>>(){}.getType());
    private final List<User> users;

    public UsersJsonRepository(){
        this.users = new ArrayList<>(storage.load());
    }
    public List<User> getUsers(){
        return users;
    }
    public void saveUsers(){
        storage.save(this.users);
    }
}
