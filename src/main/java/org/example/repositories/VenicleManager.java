package org.example.repositories;

import org.example.Categories;
import org.example.models.Car;
import org.example.models.Motocycle;
import org.example.models.Vehicle;

import java.io.*;
import java.util.*;

public class VenicleManager implements IVehicleRepository {
    public ArrayList<Vehicle> vehicles = new ArrayList<>();
    public ArrayList<Vehicle> deepVehicles = new ArrayList<>();
    public VenicleManager() {
        loadCSV();
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
    public void rentVehicle(int index) {
        vehicles.get(index).setRended(true);
        save();
    }

    @Override
    public void returnVehicle(int index) {
        vehicles.get(index).setRended(false);
        save();
    }

    @Override
    public void getVehicles(List <Vehicle> list) {
        StringBuilder vehiclesString = new StringBuilder();
        int index = 1;
        for (Vehicle vehicle : list) {
            String vehicleLine = String.format("ID %d : Brand %s : Model %s : Year %d : Price %d : Status %b : Category %s\n",
                    index, vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getPrice(), vehicle.getRented(), vehicle.getCategory(),vehicle.getType());
            vehiclesString.append(vehicleLine);
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
        try {
            File myObj = new File("cars.csv");
            Scanner myReader = new Scanner(myObj);
            int index = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                List<String> splitted = List.of(data.split(";"));
                vehicles.add(new Vehicle(
                        index
                        , splitted.get(1)
                        , splitted.get(2)
                        , Integer.parseInt(splitted.get(3))
                        , Integer.parseInt(splitted.get(4))
                        , Boolean.parseBoolean(splitted.get(5))
                        , Categories.valueOf(splitted.get(6))
                        , splitted.get(7)));
                index++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
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
        setList();
    }

    @Override
    public void removeVehicle(int index) {
        vehicles.remove(index);
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
            if(!vehicle.getRented()){
                String vehicleLine = String.format("ID %d : Brand %s : Model %s : Year %d : Price %d : Status %b : Category %s\n",
                        index, vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getPrice(), vehicle.getRented(), vehicle.getCategory(),vehicle.getType());
                vehiclesString.append(vehicleLine);
            }
            index++;
        }
        System.out.println(vehiclesString);
    }
}
