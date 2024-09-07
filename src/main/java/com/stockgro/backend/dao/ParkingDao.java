package com.stockgro.backend.dao;

import com.stockgro.backend.entity.StoredPark;
import com.stockgro.backend.model.vehicle.Vehicle;

public interface ParkingDao {

    StoredPark save(Vehicle vehicle, int parkingSpotNumber);

    StoredPark get(int id);

    StoredPark update(StoredPark storedPark);

}
