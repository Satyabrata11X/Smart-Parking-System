package com.Satya.SmartParkingSystem.slot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSlotRepository
        extends JpaRepository<ParkingSlot, Long> {
    boolean existsByVehicleId(Long vehicleId);

}