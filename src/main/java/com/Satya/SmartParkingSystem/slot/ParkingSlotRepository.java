package com.Satya.SmartParkingSystem.slot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSlotRepository
        extends JpaRepository<ParkingSlot, Long> {

    boolean existsByVehicleId(Long vehicleId);

    List<ParkingSlot> findAllByOrderByIdAsc();
}