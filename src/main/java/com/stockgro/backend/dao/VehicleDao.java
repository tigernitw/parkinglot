package com.stockgro.backend.dao;

import com.stockgro.backend.entity.StoredVehicle;

public interface VehicleDao {

    void addVehicle(String vehicleNumber, StoredVehicle storedVehicle);

    StoredVehicle getVehicle(String vehicleNumber);

    void removeVehicle(String vehicleNumber);

}
