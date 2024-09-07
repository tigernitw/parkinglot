package com.stockgro.backend.dao;

import com.stockgro.backend.entity.StoredParkingSpot;
import com.stockgro.backend.model.vehicle.Vehicle;

import java.util.List;

public interface ParkingSpotDao {

    void enterSpotParking(int spot, Vehicle vehicle);

    void endSpotParking(int spot);

    List<StoredParkingSpot> fetchAll();
}
