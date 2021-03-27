package com.example.firsttestapp.data;

public class StapleDataHolder {
    private String modelType;
    private String make;
    private String address;
    private String modelNumber;
    private String stapleType;
    private String finisher;
    private int quantity;

    private StapleDataHolder() {
    }

    private static StapleDataHolder instance;

    public static synchronized StapleDataHolder getInstance() {
        if (instance == null) {
            instance = new StapleDataHolder();
        }
        return instance;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getStapleType() {
        return stapleType;
    }

    public void setStapleType(String stapleType) {
        this.stapleType = stapleType;
    }

    public String getFinisher() {
        return finisher;
    }

    public void setFinisher(String finisher) {
        this.finisher = finisher;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static void setInstance(StapleDataHolder instance) {
        StapleDataHolder.instance = instance;
    }


}
