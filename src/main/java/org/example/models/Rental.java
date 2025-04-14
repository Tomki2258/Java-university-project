package org.example.models;
import lombok.*;


@AllArgsConstructor
@Data
@Builder
public class Rental {
    private String id;
    private String userID;
    private String vehicleID;
    private String rentDate;
    private String returnDate;

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getUserID() {
        return userID;
    }

    public String getVehicleId() {
        return vehicleID;
    }
    public String getId(){
        return id;
    }
    public void describe(){
        System.out.println(userID + " / " + vehicleID + " / " + rentDate + " / " +returnDate);
    }
}
