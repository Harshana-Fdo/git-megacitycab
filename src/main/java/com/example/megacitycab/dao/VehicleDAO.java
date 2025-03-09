package com.example.megacitycab.dao;

import com.example.megacitycab.model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    private Connection conn;

    public VehicleDAO() {
        this.conn = DatabaseConnection.getConnection();
    }

    // Insert a new vehicle into the database
    public boolean insertVehicle(Vehicle vehicle) {
        try {
            String sql = "INSERT INTO vehicles (driver_name, vehicle_number, model, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, vehicle.getDriverName());
            ps.setString(2, vehicle.getVehicleNumber());
            ps.setString(3, vehicle.getModel());
            ps.setString(4, vehicle.getStatus());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get all available vehicles for booking


    // Update vehicle status (e.g., Available, Booked, In Maintenance)
    public boolean updateVehicleStatus(int vehicleId, String status) {
        try {
            String sql = "UPDATE vehicles SET status = ? WHERE vehicle_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, vehicleId);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Add a new vehicle


    // ✅ Update vehicle details

    // ✅ Delete a vehicle


    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        try {
            String sql = "SELECT * FROM vehicles WHERE status = 'AVAILABLE'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Vehicle vehicle = new Vehicle(
                        rs.getInt("vehicle_id"),
                        rs.getString("model"),
                        rs.getString("vehicle_number"),
                        rs.getString("driver_name"),
                        rs.getString("status")
                );
                vehicles.add(vehicle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        try {
            String sql = "SELECT * FROM vehicles";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Vehicle vehicle = new Vehicle(
                        rs.getInt("vehicle_id"),
                        rs.getString("driver_name"),
                        rs.getString("vehicle_number"),
                        rs.getString("model"),
                        rs.getString("status")
                );
                vehicles.add(vehicle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public boolean addVehicle(Vehicle vehicle) {
        try {
            String sql = "INSERT INTO vehicles (driver_name, vehicle_number, model, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, vehicle.getDriverName());
            ps.setString(2, vehicle.getVehicleNumber());
            ps.setString(3, vehicle.getModel());
            ps.setString(4, vehicle.getStatus());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateVehicle(int vehicleId, String driverName, String model, String status) {
        try {
            String sql = "UPDATE vehicles SET driver_name = ?, model = ?, status = ? WHERE vehicle_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, driverName);
            ps.setString(2, model);
            ps.setString(3, status);
            ps.setInt(4, vehicleId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteVehicle(int vehicleId) {
        try {
            String sql = "DELETE FROM vehicles WHERE vehicle_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, vehicleId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

