package com.Satya.SmartParkingSystem.vehicle;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {

        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);

        if (vehicle != null) {

            vehicle.setVehicleNumber(updatedVehicle.getVehicleNumber());
            vehicle.setOwnerName(updatedVehicle.getOwnerName());
            vehicle.setVehicleType(updatedVehicle.getVehicleType());

            return vehicleRepository.save(vehicle);
        }

        return null;
    }

}
