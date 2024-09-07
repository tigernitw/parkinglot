package com.stockgro.backend.dao.impl;

import com.stockgro.backend.dao.ParkingDao;
import com.stockgro.backend.entity.StoredPark;
import com.stockgro.backend.model.vehicle.Vehicle;
import com.stockgro.backend.utils.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class ParkingDaoImpl implements ParkingDao {

    private final Map<Integer, StoredPark> parkingManager;
    private IdGenerator idGenerator;

    public ParkingDaoImpl() {
        this.parkingManager = new HashMap<>();
        this.idGenerator = new IdGenerator();
    }

    public StoredPark save(Vehicle vehicle, int parkingSpotNumber) {

        StoredPark storedPark = new StoredPark(idGenerator.getNextId());
        storedPark.setParkingSpotNumber(parkingSpotNumber);
        storedPark.setVehicle(vehicle);
        storedPark.setParkingStartTime(System.currentTimeMillis());

        parkingManager.put(storedPark.getId(), storedPark);
        return storedPark;
    }

    public StoredPark get(int id) {
        return parkingManager.get(id);
    }

    @Override
    public StoredPark update(StoredPark storedPark) {
        storedPark.setGetParkingEndTime(System.currentTimeMillis());
        parkingManager.put(storedPark.getId(), storedPark);
        return storedPark;
    }

}
