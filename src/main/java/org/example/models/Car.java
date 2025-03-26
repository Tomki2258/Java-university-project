package org.example.models;

import org.example.Categories;

public class Car extends Vehicle implements Cloneable{
    public Car(int id, String brand, String model, int price, int year, boolean rented, Categories categories, String type) {
        super(id,brand,model,price,year,rented,categories,type);
    }
}
