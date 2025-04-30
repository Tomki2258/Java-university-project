package org.example.repositories;

import org.example.models.Rental;

import java.util.List;

public interface IRentalRepository {
    void fn();
    List<Rental> getRentals();
    void add(Rental rental);
    void returnVehicle(String userUD);
}
