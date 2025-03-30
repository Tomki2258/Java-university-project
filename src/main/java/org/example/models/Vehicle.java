package org.example.models;

import org.example.Categories;
import org.example.Types;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Vehicle implements Cloneable {
    private int id;
    private String type;
    private String brand;
    private String model;
    private int year;
    private Boolean rented;
    private String category;
    private String hashCode;

    private String plate;
    private int price;
    private HashMap<String, Object> attributes;

    public Vehicle(int id,String brand,String model,int year,String type,String plate,HashMap<String,Object> attributes){
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.type = type;
        this.plate = plate;
        this.attributes = attributes;
    }
    public Vehicle(int id,String brand,String model,int year,String type,String plate){
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.type = type;
        this.plate = plate;
    }

    public String toStr(){
        return  String.format("%s %s",brand,model);
    }

    public int getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getPrice() {
        return price;
    }

    public Boolean getRented() {
        return rented;
    }

    public String getCategory() {
        return category;
    }
    public void setRended(boolean state){
        rented = state;
    }
    public Vehicle clone(){
        Vehicle vehicle;

        try {
            vehicle = (Vehicle) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        return vehicle;
    }
    public void Describe(){
        System.out.println(id + " " + category + " " + brand + " " +model + " " + year + " " + plate);
    }
    public String getHashCode(){
        return hashCode;
    }
    public HashMap<String,Object> getAttributes(){
        return attributes;
    }
}
