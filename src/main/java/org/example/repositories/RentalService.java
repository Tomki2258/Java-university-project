package org.example.repositories;

import org.example.User;
import org.example.app.Main;
import org.example.models.Rental;
import org.example.models.Vehicle;
import org.example.repositories.Jdbc.RentalJdbcRepository;
import org.example.repositories.Json.RentalJsonRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class RentalService {
    public List<Rental> rentalList;
    private RentalJdbcRepository rentalJdbcRepository;
    private RentalJsonRepository rentalJsonRepository;

    public RentalService()
    {
        if(Main.jsonMode){
            this.rentalJsonRepository = new RentalJsonRepository();
            rentalList = rentalJsonRepository.getRentals();
        }
        else{
            this.rentalJdbcRepository = new RentalJdbcRepository();
            rentalList = rentalJdbcRepository.getRentals();
        }
        //printRentals();
    }
    public void printRentals() {
        for (int i = 0; i < rentalList.size(); i++) {
            rentalList.get(i).describe();
        }
    }
    public boolean checkUserRent(User user) {
        String id = user.getId();

        Optional<Rental> found = rentalList.stream().filter(rental -> rental.getUserID().equals(id) && rental.getReturnDate().isEmpty()).findFirst();
        return found.isPresent();
    }
    public boolean isVehicleRented(Vehicle vehicle){
        Optional<Rental> found = rentalList.stream().filter(rental -> rental.getVehicleId().equals(vehicle.getId()) && !rental.getReturnDate().isEmpty()).findFirst();

        return found.isPresent();
    }
    public void returnVehicle(User user) {
        if(Main.jsonMode){
            rentalJsonRepository.returnVehicle(user.getId());
        }
        else{
            rentalJdbcRepository.returnVehicle(user.getId())    ;
        }

        //rentalList.remove(toRemove);
    }
    public void rentVehicle(User user,Vehicle vehicle){
        String rentalSize = String.valueOf(rentalList.size());
        String currentDate = LocalDateTime.now().toString();
        Rental rental = new Rental(rentalSize,
                user.getId(),
                vehicle.getId(),
                currentDate,
                "");
        if(Main.jsonMode) {
            rentalJsonRepository.add(rental);
        }
        else{
            rentalJdbcRepository.add(rental);
        }
    }
    public Optional<Rental> findByVehicleIdAndReturnDateIsNull(String vehicleId) {
        return rentalList.stream()
                .filter(r -> r.getVehicleId().equals(vehicleId))
                .filter(r -> r.getReturnDate() == null)
                .findFirst();
    }
}
