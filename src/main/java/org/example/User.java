package org.example;
public class User {
    private String nick;
    private String password;
    private int rendedVehicle;
    private UserType userType;

    User(String nick, String password, UserType userType,int rendedVehicle) {
        this.nick = nick;
        this.password = password;
        this.userType = userType;
        this.rendedVehicle = rendedVehicle;
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
        return userType;
    }
    public void Describeuser(){
        System.out.println(this.nick + " " + this.password);
    }
    public String GetNick(){
        return nick;
    }
    public String GetPassword(){
        return password;
    }
}
