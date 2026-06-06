package com.Satya.SmartParkingSystem.slot;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/slots")
public class ParkingSlotController {

    private final ParkingSlotService parkingSlotService;

    public ParkingSlotController(ParkingSlotService parkingSlotService) {
        this.parkingSlotService = parkingSlotService;
    }

    @GetMapping
    public List<ParkingSlot> getAllSlots() {
        return parkingSlotService.getAllSlots();
    }

    @GetMapping("/{id}")
    public ParkingSlot getSlotById(@PathVariable Long id) {
        return parkingSlotService.getSlotById(id);
    }


    @PostMapping
    public ParkingSlot createSlot(@Valid @RequestBody ParkingSlot slot) {
        return parkingSlotService.saveSlot(slot);
    }

    @PutMapping("/{id}")
    public ParkingSlot updateSlot(
            @PathVariable Long id,
            @RequestBody ParkingSlot updatedSlot) {

        return parkingSlotService.updateSlot(id, updatedSlot);
    }

    @DeleteMapping("/{id}")
    public void deleteSlot(@PathVariable Long id) {
        parkingSlotService.deleteSlot(id);
    }

    @PostMapping("/{slotId}/assign/{vehicleId}")
    public ParkingSlot assignVehicle(
            @PathVariable Long slotId,
            @PathVariable Long vehicleId) {

        return parkingSlotService.assignVehicle(slotId, vehicleId);
    }

    @PostMapping("/{slotId}/release")
    public ParkingSlot releaseSlot(@PathVariable Long slotId) {

        return parkingSlotService.releaseSlot(slotId);
    }
}