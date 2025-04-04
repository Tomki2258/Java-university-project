package org.example.repositories;

import org.example.User;
import org.example.UserType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserRepository implements IUserRepository{

    private List<User> users = new ArrayList<>();
    private final String USER_FILE = "users.csv";
    private final UsersJsonRepository usersJsonRepository = new UsersJsonRepository();
    public UserRepository(){
        users = usersJsonRepository.getUsers();
//        users.add(new User("admin","kox",UserType.ADMIN));
//        users.add(new User("user","skibidi",UserType.USER));

        for(User user:users){
            user.Describeuser();
        }
    }
    @Override
    public User getUser(int index) {
        return users.get(index);
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void save() {
        StringBuilder usersString = new StringBuilder();
        for (User user: users) {
            String vehicleLine = String.format("%s;%s;%s;%d\n",
                    user.GetNick(), user.GetPassword(), user.GetUserType(), user.GetRendedVehicle());
            usersString.append(vehicleLine);
        }

        try {
            FileWriter fileWriter = new FileWriter(USER_FILE);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.print(usersString);
            printWriter.close();

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    @Override
    public void add(User user) {
        users.add(user);
        save();
    }

    @Override
    public boolean userExist(String nick) {
        for (User user: users){
            if(user.GetNick().equals(nick)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void saveJson() {
        usersJsonRepository.saveUsers();
    }

    private List<User> load(){
        File myObj = new File(USER_FILE);
        Scanner myReader = null;
        try {
            myReader = new Scanner(myObj);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<User> loadedUsers = new ArrayList<>();
        while(myReader.hasNextLine()){
            String data = myReader.nextLine();
            List<String> splitted = List.of(data.split(";"));

            String nick = splitted.getFirst();
            String password = splitted.get(1);
            UserType userType =UserType.valueOf(splitted.get(2));
            int rentedVehicle = Integer.valueOf(splitted.getLast());

            User user = new User(nick,password,userType,rentedVehicle);

            loadedUsers.add(user);
        }

        return loadedUsers;
    }
}
