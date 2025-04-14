package org.example;

import lombok.*;

@AllArgsConstructor
@Data
@Builder
public class User {
    private String id;
    private String login;
    private String password;
    private int rendedVehicle;
    private UserType role;

    public User(String nick, String password, UserType userType, int rendedVehicle) {
        this.login = nick;
        this.password = password;
        this.role = userType;
        this.rendedVehicle = rendedVehicle;
    }
    public User(String id,String nick, String password){
        this.id = id;
        this.login = nick;
        this.password = password;
        role = UserType.USER;
        rendedVehicle = -1;
    }
    public User(String uid,String nick,String password,String role){
        this.id = uid;
        this.login = nick;
        this.password = password;
        this.role = UserType.valueOf(role);
    }

    public void RentVehicle(int vehicle) {
        rendedVehicle = vehicle;
    }

    public void RemoveVehicle() {
        rendedVehicle = -1;
    }

    public int GetRendedVehicle() {
        return rendedVehicle;
    }
    public UserType GetUserType(){
        return role;
    }
    public void Describeuser(){
        System.out.println(this.id + " " +this.login + " " + this.password + " " + this.role);
    }
    public String GetNick(){
        return login;
    }
    public String getPassword(){
        return password;
    }
    public String getId(){
        return  id;
    }

}
