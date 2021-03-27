package com.example.firsttestapp.data;

public class PaperDataHolder {

    public int gsm;
    public int paperSize;
    public int quantity;
    public String paperType;
    public String address;

    private static PaperDataHolder instance;

    public static synchronized PaperDataHolder getInstance() {
        if (instance == null) {
            instance = new PaperDataHolder();
        }
        return instance;
    }
    public int getGsm() {
        return gsm;
    }

    public void setGsm(int gsm) {
        this.gsm = gsm;
    }

    public int getPaperSize() {
        return paperSize;
    }

    public void setPaperSize(int paperSize) {
        this.paperSize = paperSize;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
