package org.example.repositories;

import org.example.models.Vehicle;

import java.io.IOException;
import java.util.List;

public interface IVehicleRepository {
    void rentVehicle(int index);

    void returnVehicle(int index);

    void getVehicles(List <Vehicle> list);

    void save() throws IOException;

    void loadCSV();

    boolean equals(Vehicle a,Vehicle b);
    void addVehicle(Vehicle vehicle);
    void removeVehicle(int index);
    Vehicle getVehicle(int index);
    void getAvailableVehicles(List <Vehicle> vehicles);
}
