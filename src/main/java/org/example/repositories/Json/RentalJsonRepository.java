package org.example.repositories.Json;

import com.google.gson.reflect.TypeToken;
import org.example.User;
import org.example.models.Rental;
import org.example.models.Vehicle;
import org.example.repositories.IRentalRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentalJsonRepository implements IRentalRepository {
    private List<Rental> rentalList = new ArrayList<>();
    private final String RENTAL_PATH = "src/data/rentals.json";
    private final com.umcsuser.carrent.utils.JsonFileStorage storage =
            new com.umcsuser.carrent.utils.JsonFileStorage<>(RENTAL_PATH, new TypeToken<List<Rental>>() {
            }.getType());
    public RentalJsonRepository(){
        load();
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

    private void load() {
        this.rentalList = new ArrayList<>(storage.load());
    }

    @Override
    public void fn() {

    }

    public List<Rental> getRentals(){
        return  rentalList;
    }

    @Override
    public void add(Rental rental) {
        rentalList.add(rental);
        storage.save(rentalList);
    }

    @Override
    public void returnVehicle(String userUD) {
        Optional<Rental> found = rentalList.stream().filter(rental -> rental.getUserID().equals(userUD) && rental.getReturnDate().isEmpty()).findFirst();
        found.get().setReturnDate(String.valueOf(LocalDateTime.now()));

        save();
    }
}
