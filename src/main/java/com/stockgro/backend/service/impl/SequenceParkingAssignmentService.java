package com.stockgro.backend.service.impl;

import com.stockgro.backend.dao.ParkingSpotDao;
import com.stockgro.backend.entity.StoredParkingSpot;
import com.stockgro.backend.exception.BaseException;
import com.stockgro.backend.model.InventoryFilters;
import com.stockgro.backend.model.enums.AssignmentType;
import com.stockgro.backend.model.enums.ParkingStatus;
import com.stockgro.backend.model.vehicle.Vehicle;
import com.stockgro.backend.service.ParkingAssignmentService;
import com.stockgro.backend.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service("SequenceParkingAssignmentService")
public class SequenceParkingAssignmentService extends ParkingSpaceService implements ParkingAssignmentService {

    @Autowired
    public SequenceParkingAssignmentService(ParkingSpotDao parkingSpotDao) {
        super(parkingSpotDao);
    }

    @Override
    public AssignmentType fetch() {
        return AssignmentType.SEQUENCE;
    }

    @Override
    public int assignParking(Vehicle vehicle) {
        List<StoredParkingSpot> storedParkingSpots = parkingSpotDao.fetchAll();
        storedParkingSpots.sort(Comparator.comparingInt(StoredParkingSpot::getSpotNumber));
        Optional<StoredParkingSpot> freeSpot = storedParkingSpots.stream()
                .filter(spot -> spot.getParkingStatus() == ParkingStatus.FREE)
                .findFirst();
        if (freeSpot.isPresent()) {
            parkingSpotDao.enterSpotParking(freeSpot.get().getSpotNumber(), vehicle);
            return freeSpot.get().getSpotNumber();
        } else {
            throw new BaseException("No Parking available", 400);
        }
    }

    @Override
    public void removeParking(int spotNumber) {
        super.removeParking(spotNumber);
    }

    @Override
    public List<StoredParkingSpot> showAllParking(InventoryFilters filters) {
        return super.showAllParking(filters);
    }
}
