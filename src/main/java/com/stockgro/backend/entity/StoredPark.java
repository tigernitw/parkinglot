package com.stockgro.backend.entity;

import com.stockgro.backend.model.vehicle.Vehicle;

public class StoredPark {
    private int id;
    private Vehicle vehicle;
    private int parkingSpotNumber;
    private long parkingStartTime;
    private long getParkingEndTime;

    public StoredPark(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(int parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public long getParkingStartTime() {
        return parkingStartTime;
    }

    public void setParkingStartTime(long parkingStartTime) {
        this.parkingStartTime = parkingStartTime;
    }

    public long getGetParkingEndTime() {
        return getParkingEndTime;
    }

    public void setGetParkingEndTime(long getParkingEndTime) {
        this.getParkingEndTime = getParkingEndTime;
    }
}
