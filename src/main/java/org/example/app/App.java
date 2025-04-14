package org.example.app;

import org.example.*;
import org.example.models.Vehicle;
import org.example.repositories.Json.RentalJsonRepository;
import org.example.repositories.RentalService;
import org.example.repositories.UserService;
import org.example.repositories.VenicleManager;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class App {
    private User user;
    private final UserService userService;
    private final VenicleManager venicleManager;
    //private final RentalJsonRepository rentalJsonRepository;
    private final RentalJsonRepository rentalJsonRepository;

    private final RentalService rentalService = new RentalService();
    App(User user, UserService userService) {
        this.user = user;
        this.userService = userService;
        this.rentalJsonRepository = new RentalJsonRepository();
        //rentalJsonRepository = new RentalJsonRepository();
        venicleManager = new VenicleManager(rentalService);
    }

    void mainProgram() {

        boolean isWorking = true;
        /*
        String value = "haselko";
        String sha256hex = DigestUtils.sha256Hex(value);
        System.out.println(sha256hex);
        */
        while (isWorking) {
            System.out.print("1:Wyporzycz\n2:Zwrot\n3:Wypisz pojazdy\n4:Wyjdz");

            if (user.GetUserType().equals(UserType.ADMIN)) {
                System.out.print("\n5:Dodaj pojazd\n6:Usun pojazd\n7:Sprawdz uzytkowników");
            }

            System.out.println();
            Scanner scanner = new Scanner(System.in);
            String operation = scanner.nextLine();

            switch (operation) {
                case "1":
                    //if(rentalJsonRepository.checkUserRent(user) != -1){
                    //    System.out.println("MASZ JUZ WYPORZYCZONY POJAZD");
                    //    return;
                    //}

                    int rentIndex = Integer.parseInt(scanner.nextLine());
                    System.out.println("Wyporzyczono pojazd\n" + venicleManager.vehicles.get(rentIndex - 1).toStr());
                    venicleManager.rentVehicle(rentIndex - 1, user);
                    user.RentVehicle(rentIndex - 1);
                    userService.save();

                    break;
                case "2":
                    if(rentalService.checkUserRent(user) == -1){
                        System.out.println("NIE MASZ WYPORZYCZONEGO POJAZDU");
                        return;
                    }
                        System.out.println("Zwrocono pojaz\n");
                        //TODO:returnVehicle do RentalRepository
                        //rentalJsonRepository.returnVehicle(user);
                        user.RemoveVehicle();
                        userService.save();
                    break;
                case "3":
                    switch (user.GetUserType()){
                        case USER -> venicleManager.getAvailableVehicles(venicleManager.vehicles);
                        case ADMIN -> venicleManager.getVehicles(venicleManager.vehicles);
                    }

                    //System.out.println("DEEP KOPIA");
                    //venicleManager.getVehicles(venicleManager.deepVehicles);
                    break;
                case "4":
                    System.out.println("narka");

                    isWorking = false;

                    venicleManager.saveJson();
                    userService.save();
                    //TODO:Dodanie zapisywania do bazy danych
                    //rentalJsonRepository.save();
                    break;
                case "5":
                    addingVehile();
                    break;
                case "6":
                    if (user.GetUserType() != UserType.ADMIN) {
                        System.out.println("NIE MASZ PRAWA DO WYKONANIA TEJ OPERACJI");
                        break;
                    }

                    venicleManager.getVehicles(venicleManager.vehicles);
                    System.out.println("Podaj identyfikator pojazdu do usunięcia");
                    int input = scanner.nextInt();

                    if (input > venicleManager.vehicles.stream().count()) {
                        System.out.println("ZŁY INDEX");
                        break;
                    }

                    venicleManager.removeVehicle(input - 1);
                    System.out.println("POJAZD USUNIETY");
                    break;
                case "7":
                    for (User user : userService.getUsers()) {
                        if (user.GetUserType() != UserType.ADMIN) {
                            user.Describeuser();
                        }
                    }
                    break;
            }
        }
    }

    private void addingVehile() {
        Scanner scanner = new Scanner(System.in);
        if (user.GetUserType() != UserType.ADMIN) {
            System.out.println("NIE MASZ PRAWA DO WYKONANIA TEJ OPERACJI");
            return;
        }
        System.out.println("Podaj dane pojazdu\nMARKA:MODEL:ROK:KATEGORIA:REJESTRACJA");
        scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        List<String> splitted = List.of(line.split(":"));
        String id = String.valueOf(UUID.randomUUID());
        String brand = splitted.get(0);
        String model = splitted.get(1);
        int year = Integer.valueOf(splitted.get(2));
        String category = splitted.get(3);
        String plate = splitted.get(4);
        Vehicle vehicle = new Vehicle(
                id,
                brand,
                model,
                year,
                category,
                plate
        );

        venicleManager.addVehicle(vehicle);
    }
}
