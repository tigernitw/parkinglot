package com.stockgro.backend.dao.impl;

import com.stockgro.backend.config.CapacityConfig;
import com.stockgro.backend.dao.ParkingSpotDao;
import com.stockgro.backend.entity.StoredParkingSpot;
import com.stockgro.backend.model.enums.ParkingStatus;
import com.stockgro.backend.model.vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParkingSpotDaoImpl implements ParkingSpotDao {

    private final List<StoredParkingSpot> spotManager;

    @Autowired
    public ParkingSpotDaoImpl(CapacityConfig capacityConfig) {
        this.spotManager = new ArrayList<>(capacityConfig.getCapacity());
        for (int i = 0; i < capacityConfig.getCapacity(); i++) {
            spotManager.add(new StoredParkingSpot(i));
        }
    }

    @Override
    public synchronized void enterSpotParking(int spot, Vehicle vehicle) {
        StoredParkingSpot storedParkingSpot = this.spotManager.get(spot);
        storedParkingSpot.setParkedVehicle(vehicle);
        storedParkingSpot.setParkingStatus(ParkingStatus.OCCUPIED);
    }

    @Override
    public void endSpotParking(int spot) {
        StoredParkingSpot storedParkingSpot = this.spotManager.get(spot);
        storedParkingSpot.setParkedVehicle(null);
        storedParkingSpot.setParkingStatus(ParkingStatus.FREE);
    }

    @Override
    public List<StoredParkingSpot> fetchAll() {
        return this.spotManager;
    }
}
