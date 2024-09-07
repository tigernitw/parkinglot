package com.stockgro.backend.dao.impl;

import com.stockgro.backend.dao.VehicleDao;
import com.stockgro.backend.entity.StoredVehicle;
import com.stockgro.backend.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class VehicleDaoImpl implements VehicleDao {

    private final Map<String, StoredVehicle> vehicleManager;

    @Autowired
    public VehicleDaoImpl() {
        this.vehicleManager = new HashMap<>();
    }

    @Override
    public void addVehicle(String vehicleNumber, StoredVehicle storedVehicle) {
        try {
            vehicleManager.put(vehicleNumber, storedVehicle);
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, String.format("error while adding storedVehicle with vehicleNumber : %s", vehicleNumber));
            throw new BaseException("storedVehicle parking creation failed", 500);
        }
    }

    @Override
    public StoredVehicle getVehicle(String vehicleNumber) {
        try {
            return vehicleManager.get(vehicleNumber);
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, String.format("error while fetching storedVehicle with vehicleNumber : %s", vehicleNumber));
            throw new BaseException("storedVehicle parking fetch failed", 500);
        }
    }

    @Override
    public void removeVehicle(String vehicleNumber) {
        try {
            vehicleManager.remove(vehicleNumber);
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, String.format("error while removing storedVehicle with vehicleNumber : %s", vehicleNumber));
            throw new BaseException("storedVehicle parking remove failed", 500);
        }
    }
}
