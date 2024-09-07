package com.stockgro.backend.mapper;

import com.stockgro.backend.entity.StoredPark;
import com.stockgro.backend.model.ParkingInfo;

public class ParkingMapper {

    public static ParkingInfo createParkingInfo(StoredPark storedPark) {
        ParkingInfo parkingInfo = new ParkingInfo();
        parkingInfo.setId(storedPark.getId());
        parkingInfo.setParkingSpotNumber(storedPark.getParkingSpotNumber());
        parkingInfo.setVehicle(storedPark.getVehicle());
        parkingInfo.setParkingStartTime(storedPark.getParkingStartTime());
        parkingInfo.setGetParkingEndTime(storedPark.getGetParkingEndTime());
        return parkingInfo;
    }



}
