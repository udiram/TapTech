package com.example.firsttestapp.data;

public class ReportData {
    private String modelNumber;
    private String make;
    private String address;
    private String description;
    private String serialNumber;
    private String meterRead;
    private String modelType;
    private String requestType;
    private String timestamp;


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ReportData() {

    }

    public ReportData(String modelNumber, String serialNumber, String meterRead, String requestType) {
        this.modelNumber = modelNumber;
        this.serialNumber = serialNumber;
        this.meterRead = meterRead;
        this.requestType = requestType;
    }

    public ReportData(String modelNumber, String make, String address, String description,
                      String serialNumber, String meterRead, String modelType, String requestType) {
        this.modelNumber = modelNumber;
        this.make = make;
        this.address = address;
        this.description = description;
        this.serialNumber = serialNumber;
        this.meterRead = meterRead;
        this.modelType = modelType;
        this.requestType = requestType;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMeterRead() {
        return meterRead;
    }

    public void setMeterRead(String meterRead) {
        this.meterRead = meterRead;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) { this.requestType = requestType;
    }



}
