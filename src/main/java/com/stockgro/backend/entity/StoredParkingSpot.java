package com.stockgro.backend.entity;

import com.stockgro.backend.model.enums.ParkingStatus;
import com.stockgro.backend.model.vehicle.Vehicle;

public class StoredParkingSpot {

    private int spotNumber;
    private ParkingStatus parkingStatus;
    private Vehicle parkedVehicle;

    public StoredParkingSpot(int spotNumber) {
        this.spotNumber = spotNumber;
        this.parkingStatus = ParkingStatus.FREE;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public ParkingStatus getParkingStatus() {
        return parkingStatus;
    }

    public void setParkingStatus(ParkingStatus parkingStatus) {
        this.parkingStatus = parkingStatus;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void setParkedVehicle(Vehicle parkedVehicle) {
        this.parkedVehicle = parkedVehicle;
    }
}
