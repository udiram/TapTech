package com.example.firsttestapp.data;

public class ReportDataHolder {

    private ReportData reportData = new ReportData();
    private static ReportDataHolder instance;

    private ReportDataHolder() {}

    public static synchronized ReportDataHolder getInstance() {
        if (instance == null) {
            instance = new ReportDataHolder();
        }
        return instance;
    }

    public ReportData getReportData() {
        return reportData;
    }

    public void setReportData(ReportData reportData) {
        this.reportData = reportData;
    }
}

