package com.example.megacitycab.service;

import com.example.megacitycab.model.Vehicle;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleServiceTest {

    @Test
    public void testGetAvailableVehicles() {
        VehicleService vehicleService = new VehicleService();

        // Fetch available vehicles
        List<Vehicle> availableVehicles = vehicleService.getAvailableVehicles();

        // Assert: Check that the list is not null
        assertNotNull(availableVehicles);

        // Print output for debugging
        System.out.println("Available Vehicles: " + availableVehicles.size());
    }

    @Test
    public void testGetAllVehicles() {
        VehicleService vehicleService = new VehicleService();

        // Fetch all vehicles
        List<Vehicle> allVehicles = vehicleService.getAllVehicles();

        // Assert: Check that the list is not null
        assertNotNull(allVehicles);

        // Print output for debugging
        System.out.println("All Vehicles: " + allVehicles.size());
    }

    @Test
    public void testAddVehicle() {
        VehicleService vehicleService = new VehicleService();

        // Create a new vehicle object
        Vehicle newVehicle = new Vehicle(1, "John3", "ABC-2223", "Toyota Prius BXX", "AVAILABLE");

        // Attempt to add vehicle
        boolean result = vehicleService.addVehicle(newVehicle);

        // Assert that vehicle addition was successful
        assertTrue(result);
    }

    @Test
    public void testUpdateVehicle() {
        VehicleService vehicleService = new VehicleService();

        // Assume we update vehicle with ID 1
        boolean result = vehicleService.updateVehicle(9, "John Deo12", "ABCD-123", "AVAILABLE");

        // Assert: Update should be successful
        assertTrue(result);
    }

    @Test
    public void testDeleteVehicle() {
        VehicleService vehicleService = new VehicleService();

        // Assume we delete vehicle with ID 1
        boolean result = vehicleService.deleteVehicle(10);

        // Assert: Deletion should be successful
        assertTrue(result);
    }
}