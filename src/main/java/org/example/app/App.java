package org.example.app;

import org.example.*;
import org.example.models.Vehicle;
import org.example.repositories.UserRepository;
import org.example.repositories.VenicleManager;

import java.util.List;
import java.util.Scanner;

public class App {
    private User user;
    private final UserRepository userRepository;
    private final VenicleManager venicleManager;
    App(User user, UserRepository userRepository){
        this.user = user;
        this.userRepository = userRepository;
        venicleManager = new VenicleManager();
    }
    void mainProgram(){

        boolean isWorking = true;
        /*
        String value = "haselko";
        String sha256hex = DigestUtils.sha256Hex(value);
        System.out.println(sha256hex);
        */
        while (isWorking) {
            System.out.print("1:Wyporzycz\n2:Zwrot\n3:Wypisz pojazdy\n4:Wyjdz");

            if(user.GetUserType().equals(UserType.ADMIN)){
                System.out.print("\n5:Dodaj pojazd\n6:Usun pojazd\n7:Sprawdz uzytkowników");
            }

            System.out.println();
            Scanner scanner = new Scanner(System.in);
            String operation = scanner.nextLine();

            switch (operation) {
                case "1":
                    int rentIndex = Integer.parseInt(scanner.nextLine());
                    if (!venicleManager.vehicles.get(rentIndex - 1).getRented()) {
                        System.out.println("Wyporzyczono pojazd\n" + venicleManager.vehicles.get(rentIndex - 1).toStr());
                        venicleManager.rentVehicle(rentIndex - 1);
                        user.RentVehicle(rentIndex - 1);
                        userRepository.save();
                    } else {
                        System.out.println("JUZ WYPORZYCZONE");
                    }
                    break;
                case "2":
                    if(user.GetRendedVehicle() == -1){
                        System.out.println("NIE MASZ WYPOZYCZONYCH POJAZDOW");
                        break;
                    }
                    int choosenIndex = Integer.parseInt(scanner.nextLine());
                    if(venicleManager.vehicles.get(choosenIndex - 1).getRented()){
                        System.out.println("Zwrocono pojaz\n" + venicleManager.vehicles.get(choosenIndex - 1).toStr());
                        venicleManager.returnVehicle(choosenIndex - 1);
                        user.RemoveVehicle();
                        userRepository.save();
                    }else{
                        System.out.println("POJAZD NIE WYPORZYCZONY");
                    }
                    break;
                case "3":
                    if(user.GetUserType() == UserType.ADMIN){
                        venicleManager.getVehicles(venicleManager.vehicles);
                    }
                    else{
                        venicleManager.getAvailableVehicles(venicleManager.vehicles);
                    }
                    //System.out.println("DEEP KOPIA");
                    //venicleManager.getVehicles(venicleManager.deepVehicles);
                    break;
                case "4":
                    System.out.println("narka");

                    isWorking = false;

                    venicleManager.saveJson();
                    userRepository.saveJson();
                    break;
                case "5":
                    addingVehile();
                    break;
                case "6":
                    if(user.GetUserType() != UserType.ADMIN) {
                        System.out.println("NIE MASZ PRAWA DO WYKONANIA TEJ OPERACJI");
                        break;
                    }

                    venicleManager.getVehicles(venicleManager.vehicles);
                    System.out.println("Podaj identyfikator pojazdu do usunięcia");
                    int input = scanner.nextInt();

                    if(input > venicleManager.vehicles.stream().count()){
                        System.out.println("ZŁY INDEX");
                        break;
                    }

                    venicleManager.removeVehicle(input - 1);
                    System.out.println("POJAZD USUNIETY");
                    break;
                case "7":
                    for (User user : userRepository.getUsers()){
                        if(user.GetUserType() != UserType.ADMIN){
                            user.Describeuser();
                        }
                    }
                    break;
            }
        }
    }
    private void addingVehile(){
        Scanner scanner = new Scanner(System.in);
        if(user.GetUserType() != UserType.ADMIN) {
            System.out.println("NIE MASZ PRAWA DO WYKONANIA TEJ OPERACJI");
            return;
        }
        System.out.println("Podaj dane pojazdu\nMARKA:MODEL:ROK:KATEGORIA:REJESTRACJA");
        scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        List<String> splitted = List.of(line.split(":"));

        String brand = splitted.getFirst();
        String model = splitted.get(1);
        int year = Integer.valueOf(splitted.get(2));
        String category = splitted.get(3);
        String plate = splitted.get(4);
        Vehicle vehicle = new Vehicle(
                venicleManager.getVehicleCount() + 1,
                brand,
                model,
                year,
                category,
                plate
        );

        venicleManager.addVehicle(vehicle);
    }
}
