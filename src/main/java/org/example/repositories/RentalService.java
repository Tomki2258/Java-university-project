package org.example.repositories;

import org.example.User;
import org.example.app.Main;
import org.example.models.Rental;
import org.example.models.Vehicle;
import org.example.repositories.Jdbc.RentalJdbcRepository;
import org.example.repositories.Json.RentalJsonRepository;

import java.util.List;
import java.util.Optional;

public class RentalService {
    public List<Rental> rentalList;
    private final RentalJdbcRepository rentalJdbcRepository;
    private final RentalJsonRepository rentalJsonRepository;

    public RentalService()
    {
        this.rentalJdbcRepository = new RentalJdbcRepository();
        this.rentalJsonRepository = new RentalJsonRepository();

        if(Main.jsonMode){
            rentalList = rentalJsonRepository.getRentals();
        }
        else{
            rentalList = rentalJdbcRepository.getRentals();
        }
        printRentals();
    }
    public void printRentals() {
        for (int i = 0; i < rentalList.size(); i++) {
            rentalList.get(i).describe();
        }
    }
    public int checkUserRent(User user) {
        String id = user.getId();

        for (Rental rental : rentalList) {
            if (rental.getUserID().equals(id)) {
                return rentalList.indexOf(rental);
            }
        }

        return -1;               // ':)'
    }
    public boolean isVehicleRented(Vehicle vehicle){
        for (Rental rental : rentalList) {
            if (rental.getVehicleId().equals(vehicle.getId())) {
                return true;
            }
        }
        return false;
    }
    public void returnVehicle(User user) {
        String id = user.getId();
        Rental toRemove = null;
        for (Rental rental : rentalList) {
            if (rental.getUserID().equals(id)) {
                toRemove = rental;
            }
        }

        rentalList.remove(toRemove);
    }
    public Optional<Rental> findByVehicleIdAndReturnDateIsNull(String vehicleId) {
        return rentalList.stream()
                .filter(r -> r.getVehicleId().equals(vehicleId))
                .filter(r -> r.getReturnDate() == null)
                .findFirst();
    }
}
