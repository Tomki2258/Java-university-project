package org.example;

public class Motocycle extends Vehicle implements Cloneable{
    Categories category;

    public Motocycle(int id, String brand, String model, int year, int price, boolean rented, Categories category,String type) {
        super(id, brand, model, year, price, rented, category,type);
    }
}
