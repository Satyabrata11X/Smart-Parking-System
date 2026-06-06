package com.Satya.SmartParkingSystem.dashboard;

import com.Satya.SmartParkingSystem.slot.ParkingSlotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final ParkingSlotService parkingSlotService;

    public DashboardController(ParkingSlotService parkingSlotService) {
        this.parkingSlotService = parkingSlotService;
    }

    @GetMapping("/total-slots")
    public long getTotalSlots() {
        return parkingSlotService.getAllSlots().size();
    }

    @GetMapping("/available-slots")
    public long getAvailableSlots() {
        return parkingSlotService.getAvailableSlotsCount();
    }

    @GetMapping("/occupied-slots")
    public long getOccupiedSlots() {
        return parkingSlotService.getOccupiedSlotsCount();
    }

    @GetMapping("/summary")
    public DashboardSummary getSummary() {

        long totalSlots = parkingSlotService.getAllSlots().size();
        long availableSlots = parkingSlotService.getAvailableSlotsCount();
        long occupiedSlots = parkingSlotService.getOccupiedSlotsCount();

        return new DashboardSummary(
                totalSlots,
                availableSlots,
                occupiedSlots
        );
    }
}