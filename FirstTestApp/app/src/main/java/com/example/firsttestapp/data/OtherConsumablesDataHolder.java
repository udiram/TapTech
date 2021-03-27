package com.example.firsttestapp.data;

public class OtherConsumablesDataHolder {
    private String modelType;
    private String make;
    private String address;
    private String modelNumber;
    private String serialNumber;
    private String request;

    private OtherConsumablesDataHolder() {}
    private static OtherConsumablesDataHolder instance;

    public static synchronized OtherConsumablesDataHolder getInstance() {
        if (instance == null) {
            instance = new OtherConsumablesDataHolder();
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }



}
