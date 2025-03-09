package com.example.megacitycab.service;

import com.example.megacitycab.dao.VehicleDAO;
import com.example.megacitycab.model.Vehicle;

import java.util.List;

public class VehicleService {

    private VehicleDAO vehicleDAO;

    public VehicleService() {
        this.vehicleDAO = new VehicleDAO();
    }

    // Adds a new vehicle

    // Retrieves all available vehicles
    public List<Vehicle> getAvailableVehicles() {
        return vehicleDAO.getAvailableVehicles();
    }
    // Updates vehicle status (Admin feature)
    public boolean updateVehicleStatus(int vehicleId, String status) {
        return vehicleDAO.updateVehicleStatus(vehicleId, status);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleDAO.getAllVehicles();
    }

    public boolean addVehicle(Vehicle vehicle) {
        return vehicleDAO.addVehicle(vehicle);
    }

    public boolean updateVehicle(int vehicleId, String driverName, String model, String status) {
        return vehicleDAO.updateVehicle(vehicleId, driverName, model, status);
    }

    public boolean deleteVehicle(int vehicleId) {
        return vehicleDAO.deleteVehicle(vehicleId);
    }


}
