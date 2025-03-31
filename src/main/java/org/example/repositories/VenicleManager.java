package org.example.repositories;

import org.example.Categories;
import org.example.Types;

import org.example.User;
import org.example.UserType;
import org.example.models.Vehicle;

import java.io.*;
import java.util.*;

public class VenicleManager implements IVehicleRepository {
    public List<Vehicle> vehicles = new ArrayList<>();
    public List<Vehicle> deepVehicles = new ArrayList<>();

    private RentalRepository rentalRepository;
    private VehiclesJsonRepository vehiclesJsonRepository;

    public VenicleManager(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
        vehiclesJsonRepository = new VehiclesJsonRepository();
        vehicles = vehiclesJsonRepository.getVehicles();
        setList();
    }

    private void setList(){
        deepVehicles.clear();
        for (Vehicle veh : vehicles){
            deepVehicles.add(veh.clone());
        }
    }

//    private void setList() {
//        for (Vehicle veh : vehicles){
//            if(veh.getType().equals("Car")){
//                deepVehicles.add(
//                        new Car(
//                                veh.getId(),
//                                veh.getBrand(),
//                                veh.getModel(),
//                                veh.getYear(),
//                                veh.getPrice(),
//                                veh.getRented()
//                        )
//                );
//            }
//            else{
//                deepVehicles.add(
//                        new Motocycle(
//                                veh.getId(),
//                                veh.getBrand(),
//                                veh.getModel(),
//                                veh.getYear(),
//                                veh.getPrice(),
//                                veh.getRented(),
//                                veh.getCategory().toString()
//                        )
//                );
//            }
//        }
//    }

    @Override
    public void rentVehicle(int index, User user) {
        vehicles.get(index).setRended(true);

        rentalRepository.addRental(user,vehicles.get(index));
        //save();
        rentalRepository.printRentals();
    }

    @Override
    public void returnVehicle(int index) {
        vehicles.get(index).setRended(false);
        //save();
    }

    @Override
    public void getVehicles(List <Vehicle> list) {
        StringBuilder vehiclesString = new StringBuilder();
        int index = 1;
        for (Vehicle vehicle : vehicles) {
            String line = String.format("ID:%d Brand:%s Model:%s Year:%d"
                    ,index,vehicle.getBrand(),vehicle.getModel(),vehicle.getYear());
            if(rentalRepository.isVehicleRented(vehicle)){
                vehiclesString.append(" RENDTED\n");
            }
            else{
                vehiclesString.append("\n");
            }
            vehiclesString.append(line);
            index++;
        }
        System.out.println(vehiclesString);
    }

    @Override
    public void save() {
        try {
            FileWriter fileWriter = new FileWriter("cars.csv");
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.print(toCSV());
            printWriter.close();

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
    public String toCSV() {
        StringBuilder vehiclesString = new StringBuilder();
        for (Vehicle vehicle : vehicles) {
            String vehicleLine = String.format("%d;%s;%s;%d;%d;%b;%s;%s\n",
                    vehicle.getId(), vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getPrice(), vehicle.getRented(), vehicle.getCategory(),vehicle.getType());
            vehiclesString.append(vehicleLine);
        }
        return vehiclesString.toString();
    }
    @Override
    public void loadCSV() {

    }

    @Override
    public boolean equals(Vehicle a, Vehicle b) {
        if(!Objects.equals(a.getType(), b.getType()))
            return false;

        if(a.getBrand().equals(b.getBrand())
                && a.getModel().equals(b.getModel())
                && a.getPrice() == b.getPrice()
                && a.getYear() == b.getYear()
                && a.getId() == b.getId()
        )
            return true;

        return false;
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        saveJson();
        setList();
    }

    @Override
    public void removeVehicle(int index) {
        vehicles.remove(index);
        saveJson();
        setList();
    }

    @Override
    public Vehicle getVehicle(int index) {
        return null;
    }

    @Override
    public void getAvailableVehicles(List<Vehicle> vehicles) {
        StringBuilder vehiclesString = new StringBuilder();
        int index = 1;
        for (Vehicle vehicle : vehicles) {
            if(rentalRepository.isVehicleRented(vehicle)){
                continue;
            }
            String line = String.format("ID:%d Brand:%s Model:%s Year:%d\n"
                    ,index,vehicle.getBrand(),vehicle.getModel(),vehicle.getYear());
            vehiclesString.append(line);
            index++;
        }
        System.out.println(vehiclesString);
    }

    @Override
    public void loadJson() {

    }

    @Override
    public void saveJson() {
        vehiclesJsonRepository.save(vehicles);
    }
    public int getVehicleCount(){
        return vehicles.size();
    }
}
