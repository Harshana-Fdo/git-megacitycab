package com.example.megacitycab.model;

public class Vehicle {

    private int vehicleId;
    private String driverName;
    private String vehicleNumber;
    private String model;
    private String status; // AVAILABLE, BOOKED, IN_MAINTENANCE

    public Vehicle() {}

    public Vehicle(String driverName, String vehicleNumber, String model, String status) {
        this.driverName = driverName;
        this.vehicleNumber = vehicleNumber;
        this.model = model;
        this.status = status;
    }
    public Vehicle(int vehicleId, String driverName, String vehicleNumber, String model, String status) {
        this.vehicleId = vehicleId;
        this.driverName = driverName;
        this.vehicleNumber = vehicleNumber;
        this.model = model;
        this.status = status;
    }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }

    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
