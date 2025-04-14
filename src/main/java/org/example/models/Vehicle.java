package org.example.models;

import lombok.*;
import org.example.Categories;
import org.example.Types;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Data
@AllArgsConstructor
@Builder
public class Vehicle implements Cloneable {
    private String id;
    private String type;
    private String brand;
    private String model;
    private int year;
    private Boolean rented;
    private String category;
    private String hashCode;

    private String plate;
    private int price;
    @lombok.Builder.Default
    private Map<String, Object> attributes = Map.of();

    public Vehicle(String id,String brand,String model,int year,String type,String plate,HashMap<String,Object> attributes){
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.type = type;
        this.plate = plate;
        this.attributes = attributes;
    }
    public Vehicle(String id,String brand,String model,int year,String type,String plate){
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

    public String getId() {
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
    public Map<String,Object> getAttributes(){
        return attributes;
    }
    public String getPlate(){
        return plate;
    }
}
