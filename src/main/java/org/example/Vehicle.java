package org.example;

abstract class Vehicle implements Cloneable {
    private int id;
    private String brand;
    private String model;
    private int year;
    private int price;
    private Boolean rented;
    private Categories category;
    private String type;
    private String hashCode;
    public Vehicle(int id,String brand, String model, int year, int price, boolean rented){
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.rented = rented;
        category = Categories.B;
        type = "Car";

        hashCode = String.valueOf(this.hashCode());
    }
    public Vehicle(int id,String brand, String model, int year, int price, boolean rented,Categories category,String type){
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.rented = rented;
        this.category = category;
        this.type = type;

        hashCode = String.valueOf(this.hashCode());
    }
    void print(){
        System.out.println(
                id + " " + brand + " " + model + " " + year + " " + price + " " + rented
        );
    }
    String toStr(){
        return  String.format("%s %s",brand,model);
    }

    public int getId() {
        return id;
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

    public Categories getCategory() {
        return category;
    }

    public String getType() {
        return type;
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
    public String getHashCode(){
        return hashCode;
    }
}
