package com.stockgro.backend.controller;

import com.stockgro.backend.entity.StoredParkingSpot;
import com.stockgro.backend.entity.StoredVehicle;
import com.stockgro.backend.model.InventoryFilters;
import com.stockgro.backend.model.ParkingInfo;
import com.stockgro.backend.model.vehicle.Vehicle;
import com.stockgro.backend.service.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/parking_lot")
public class ParkingController {

    private final ParkingLot parkingLot;

    @Autowired
    public ParkingController(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @PostMapping("/park")
    public ParkingInfo parkVehicle(@RequestBody @Valid Vehicle vehicle) {
        return parkingLot.parkVehicle(vehicle);
    }

    @PostMapping("/unpark/{park_id}")
    public void unParkVehicle(@PathVariable("park_id") int parkId, @RequestBody @Valid Vehicle vehicle) {
        parkingLot.unParkVehicle(parkId, vehicle);
    }

    @GetMapping()
    public List<StoredParkingSpot> fetchInventory(@RequestBody(required = false) @Valid InventoryFilters filters) {
        return parkingLot.displayAvailability(filters);
    }

    @GetMapping("/vehicle/{license_id}")
    public StoredVehicle fetchVehicle(@PathVariable("license_id") String licenseId) {
        return parkingLot.fetchVehicle(licenseId);
    }
}
