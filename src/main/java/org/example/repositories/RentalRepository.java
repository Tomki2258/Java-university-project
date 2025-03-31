package org.example.repositories;

import com.google.gson.reflect.TypeToken;
import org.example.User;
import org.example.models.Rental;
import org.example.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class RentalRepository {
    private List<Rental> rentalList = new ArrayList<>();
    private final String RENTAL_PATH = "src/data/rentals.json";
    private final com.umcsuser.carrent.utils.JsonFileStorage storage =
            new com.umcsuser.carrent.utils.JsonFileStorage<>(RENTAL_PATH, new TypeToken<List<Rental>>() {
            }.getType());
    public RentalRepository(){
        load();
    }
    public void addRental(User user, Vehicle vehicle) {
        Rental rental = new Rental(rentalList.size(), user.getId(), vehicle.getId());

        rentalList.add(rental);
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

    public void save() {
        storage.save(rentalList);
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

    private void load() {
        this.rentalList = new ArrayList<>(storage.load());
    }
    public boolean isVehicleRented(Vehicle vehicle){
        for (Rental rental : rentalList) {
            if (rental.getVehicleID().equals(vehicle.getId())) {
                return true;
            }
        }
        return false;
    }
}
