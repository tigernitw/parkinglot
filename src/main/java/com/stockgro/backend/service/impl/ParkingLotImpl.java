package com.stockgro.backend.service.impl;

import com.stockgro.backend.config.CapacityConfig;
import com.stockgro.backend.dao.ParkingDao;
import com.stockgro.backend.dao.VehicleDao;
import com.stockgro.backend.entity.StoredPark;
import com.stockgro.backend.entity.StoredParkingSpot;
import com.stockgro.backend.entity.StoredVehicle;
import com.stockgro.backend.exception.BaseException;
import com.stockgro.backend.factory.AssignmentFactory;
import com.stockgro.backend.mapper.ParkingMapper;
import com.stockgro.backend.model.InventoryFilters;
import com.stockgro.backend.model.ParkingInfo;
import com.stockgro.backend.model.enums.AssignmentType;
import com.stockgro.backend.model.vehicle.Vehicle;
import com.stockgro.backend.service.ParkingAssignmentService;
import com.stockgro.backend.service.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;


@Component
public class ParkingLotImpl implements ParkingLot {

    private final VehicleDao vehicleDao;
    private final ParkingDao parkingDao;
    private final ParkingAssignmentService assignmentService;

    @Autowired
    public ParkingLotImpl(VehicleDao vehicleDao, ParkingDao parkingDao, AssignmentFactory assignmentFactory, CapacityConfig capacityConfig) {
        this.vehicleDao = vehicleDao;
        this.parkingDao = parkingDao;
        this.assignmentService = assignmentFactory.getService(capacityConfig.getType());
    }

    @Override
    public ParkingInfo parkVehicle(Vehicle vehicle) {
        StoredVehicle storedVehicle = vehicleDao.getVehicle(vehicle.getLicensePlate());
        if (storedVehicle == null) {
            StoredVehicle parkVehicle = new StoredVehicle(vehicle.getType(), vehicle.getLicensePlate(), vehicle.getColor());
            vehicleDao.addVehicle(parkVehicle.getLicensePlate(), parkVehicle);
            int spotNumber = assignmentService.assignParking(vehicle);
            StoredPark storedPark = parkingDao.save(vehicle, spotNumber);
            return ParkingMapper.createParkingInfo(storedPark);
        } else {
            throw new BaseException("vehicle already parked", 400);
        }
    }

    @Override
    public ParkingInfo unParkVehicle(int parkId, Vehicle vehicle) {
        StoredPark storedPark = parkingDao.get(parkId);
        if (storedPark == null) {
            throw new BaseException("invalid parking to be exit", 400);
        } else {
            if (!storedPark.getVehicle().getLicensePlate().equals(vehicle.getLicensePlate())) {
                throw new BaseException("invalid vehicle to be exit", 400);
            }
            assignmentService.removeParking(storedPark.getParkingSpotNumber());
            vehicleDao.removeVehicle(storedPark.getVehicle().getLicensePlate());
            storedPark = parkingDao.update(storedPark);
            return ParkingMapper.createParkingInfo(storedPark);
        }
    }

    @Override
    public List<StoredParkingSpot> displayAvailability(InventoryFilters filters) {
        return assignmentService.showAllParking(filters);
    }

    @Override
    public StoredVehicle fetchVehicle(String licenseId) {
        StoredVehicle storedVehicle = vehicleDao.getVehicle(licenseId);
        if (storedVehicle == null) {
            throw new BaseException("invalid licenseId", 400);
        } else {
            return storedVehicle;
        }
    }
}
