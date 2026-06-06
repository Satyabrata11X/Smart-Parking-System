package com.Satya.SmartParkingSystem.slot;

import com.Satya.SmartParkingSystem.exception.BusinessException;
import com.Satya.SmartParkingSystem.exception.ResourceNotFoundException;
import com.Satya.SmartParkingSystem.vehicle.Vehicle;
import com.Satya.SmartParkingSystem.vehicle.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSlotService {

    private final ParkingSlotRepository parkingSlotRepository;
    private final VehicleRepository vehicleRepository;

    public ParkingSlotService(
            ParkingSlotRepository parkingSlotRepository,
            VehicleRepository vehicleRepository) {

        this.parkingSlotRepository = parkingSlotRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public ParkingSlot saveSlot(ParkingSlot slot) {
        return parkingSlotRepository.save(slot);
    }

    public List<ParkingSlot> getAllSlots() {
        return parkingSlotRepository.findAllByOrderByIdAsc();
    }

    public ParkingSlot getSlotById(Long id) {

        return parkingSlotRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Parking Slot not found with id: " + id
                        ));
    }

    public void deleteSlot(Long id) {
        parkingSlotRepository.deleteById(id);
    }

    public ParkingSlot updateSlot(Long id, ParkingSlot updatedSlot) {

        ParkingSlot slot = parkingSlotRepository.findById(id).orElse(null);

        if (slot != null) {
            slot.setSlotNumber(updatedSlot.getSlotNumber());
            slot.setStatus(updatedSlot.getStatus());

            return parkingSlotRepository.save(slot);
        }

        return null;
    }

    public ParkingSlot assignVehicle(Long slotId, Long vehicleId) {

        ParkingSlot slot =
                parkingSlotRepository.findById(slotId).orElse(null);

        Vehicle vehicle =
                vehicleRepository.findById(vehicleId).orElse(null);

        if(slot == null || vehicle == null){
            return null;
        }

        if(slot.getStatus() == SlotStatus.OCCUPIED){
            throw new BusinessException("Slot is already occupied");
        }

        if(parkingSlotRepository.existsByVehicleId(vehicleId)){
            throw new BusinessException("Vehicle is already parked");
        }

        slot.setVehicle(vehicle);
        slot.setStatus(SlotStatus.OCCUPIED);

        return parkingSlotRepository.save(slot);
    }

    public ParkingSlot releaseSlot(Long slotId) {

        ParkingSlot slot =
                parkingSlotRepository.findById(slotId).orElse(null);

        if (slot == null) {
            return null;
        }

        if(slot.getStatus() == SlotStatus.AVAILABLE){
            throw new BusinessException("Slot is already available");
        }

        slot.setVehicle(null);
        slot.setStatus(SlotStatus.AVAILABLE);

        return parkingSlotRepository.save(slot);
    }

    public long getAvailableSlotsCount() {

        return parkingSlotRepository.findAll()
                .stream()
                .filter(slot -> slot.getStatus() == SlotStatus.AVAILABLE)
                .count();
    }

    public long getOccupiedSlotsCount() {

        return parkingSlotRepository.findAll()
                .stream()
                .filter(slot -> slot.getStatus() == SlotStatus.OCCUPIED)
                .count();
    }
}