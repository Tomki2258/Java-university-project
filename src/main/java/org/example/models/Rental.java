package org.example.models;

public class Rental {
    private int id;
    private String userID;
    private String vehicleID;

    public Rental(int id, String userID, String vehicleID){
        this.id = id;
        this.userID = userID;
        this.vehicleID = vehicleID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getUserID() {
        return userID;
    }

    public String getVehicleID() {
        return vehicleID;
    }
    public int getId(){
        return id;
    }
    public void describe(){
        System.out.println(userID + "/" + vehicleID);
    }
}
