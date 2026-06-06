package com.Satya.SmartParkingSystem.dashboard;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DashboardSummary {

    private long totalSlots;
    private long availableSlots;
    private long occupiedSlots;
}