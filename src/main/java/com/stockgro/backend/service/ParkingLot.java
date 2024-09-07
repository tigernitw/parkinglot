package com.stockgro.backend.service;

import com.stockgro.backend.entity.StoredParkingSpot;
import com.stockgro.backend.entity.StoredVehicle;
import com.stockgro.backend.model.InventoryFilters;
import com.stockgro.backend.model.ParkingInfo;
import com.stockgro.backend.model.vehicle.Vehicle;

import java.util.List;

public interface ParkingLot {

    ParkingInfo parkVehicle(Vehicle vehicle);

    ParkingInfo unParkVehicle(int parkId, Vehicle vehicle);

    List<StoredParkingSpot> displayAvailability(InventoryFilters filters);


    StoredVehicle fetchVehicle(String licenseId);
}
