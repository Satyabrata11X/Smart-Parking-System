package com.Satya.SmartParkingSystem.slot;


import com.Satya.SmartParkingSystem.vehicle.Vehicle;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "parking_slots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Slot number cannot be blank")
    private String slotNumber;

    @Enumerated(EnumType.STRING)
    private SlotStatus status;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}
