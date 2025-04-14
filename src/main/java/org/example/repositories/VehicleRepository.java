package org.example.repositories;

import org.example.models.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository {
    List<Vehicle> findAll();
    Optional<Vehicle> findById(String id);
    Vehicle add(Vehicle vehicle);
    void deleteById(String id);
    void save();
}