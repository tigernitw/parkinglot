package com.stockgro.backend.service;

import com.stockgro.backend.config.CapacityConfig;
import com.stockgro.backend.dao.ParkingSpotDao;
import com.stockgro.backend.entity.StoredParkingSpot;
import com.stockgro.backend.exception.BaseException;
import com.stockgro.backend.model.InventoryFilters;
import com.stockgro.backend.model.enums.ParkingStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public abstract class ParkingSpaceService {

    protected final ParkingSpotDao parkingSpotDao;

    @Autowired
    private CapacityConfig capacityConfig;

    @Autowired
    public ParkingSpaceService(ParkingSpotDao parkingSpotDao) {
        this.parkingSpotDao = parkingSpotDao;
    }

    protected void removeParking(int spotNumber) {
        parkingSpotDao.endSpotParking(spotNumber);
    }

    protected List<StoredParkingSpot> showAllParking(InventoryFilters filters) {
        List<StoredParkingSpot> storedParkingSpots = parkingSpotDao.fetchAll();
        if (Objects.isNull(filters)) {
            return storedParkingSpots;
        } else {
            if (Objects.nonNull(filters.getColor())) {
                return storedParkingSpots.stream().filter(storedParkingSpot -> storedParkingSpot.getParkingStatus().equals(ParkingStatus.OCCUPIED))
                        .filter(storedParkingSpot -> storedParkingSpot.getParkedVehicle().getColor().equals(filters.getColor()))
                        .toList();
            } else {
                throw new BaseException("invalid request without filter", 400);
            }
        }
    }


}
