package com.stockgro.backend.service;

import com.stockgro.backend.entity.StoredParkingSpot;
import com.stockgro.backend.model.InventoryFilters;
import com.stockgro.backend.model.enums.AssignmentType;
import com.stockgro.backend.model.vehicle.Vehicle;

import java.util.List;

public interface ParkingAssignmentService {

    AssignmentType fetch();
    int assignParking(Vehicle vehicle);

    void removeParking(int spotNumber);

    List<StoredParkingSpot> showAllParking(InventoryFilters filters);


}
