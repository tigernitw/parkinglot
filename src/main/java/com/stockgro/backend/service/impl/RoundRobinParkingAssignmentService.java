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
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RoundRobinParkingAssignmentService")
public class RoundRobinParkingAssignmentService extends ParkingSpaceService implements ParkingAssignmentService {

    private int nextIndex;
    @Autowired
    public RoundRobinParkingAssignmentService(ParkingSpotDao parkingSpotDao) {
        super(parkingSpotDao);
        nextIndex = 0;
    }

    @Override
    public AssignmentType fetch() {
        return AssignmentType.ROUND_ROBIN;
    }

    @Override
    public int assignParking(Vehicle vehicle) {
        List<StoredParkingSpot> storedParkingSpots = parkingSpotDao.fetchAll();
        for (int i = 0; i < storedParkingSpots.size(); i++) {
            int currentIndex = (nextIndex + i) % storedParkingSpots.size();
            if (storedParkingSpots.get(currentIndex).getParkingStatus().equals(ParkingStatus.FREE)) {
                nextIndex = (currentIndex + 1) % storedParkingSpots.size();
                parkingSpotDao.enterSpotParking(storedParkingSpots.get(currentIndex).getSpotNumber(), vehicle);
                return storedParkingSpots.get(currentIndex).getSpotNumber();
            }
        }
        throw new BaseException("No Parking available", 400);
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
