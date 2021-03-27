package com.example.firsttestapp.data;

public class TonerDataHolder {
    private int waste;
    private int cyan;
    private int magenta;
    private int yellow;
    private int black;
    private int meterRead;
    private String modelType;
    private String make;
    private String address;
    private String modelNumber;
    private String serialNumber;

    private TonerDataHolder() {}
    private static TonerDataHolder instance;

    public static synchronized TonerDataHolder getInstance() {
        if (instance == null) {
            instance = new TonerDataHolder();
        }
        return instance;
    }

    public int getWaste() {
        return waste;
    }

    public void setWaste(int waste) {
        this.waste = waste;
    }

    public int getCyan() {
        return cyan;
    }

    public void setCyan(int cyan) {
        this.cyan = cyan;
    }

    public int getMagenta() {
        return magenta;
    }

    public void setMagenta(int magenta) {
        this.magenta = magenta;
    }

    public int getYellow() {
        return yellow;
    }

    public void setYellow(int yellow) {
        this.yellow = yellow;
    }

    public int getBlack() {
        return black;
    }

    public void setBlack(int black) {
        this.black = black;
    }

    public int getMeterRead() {
        return meterRead;
    }

    public void setMeterRead(int meterRead) {
        this.meterRead = meterRead;
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
}
