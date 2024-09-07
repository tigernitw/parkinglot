package com.stockgro.backend.entity;

import com.stockgro.backend.model.enums.Color;
import com.stockgro.backend.model.enums.VehicleType;

public class StoredVehicle {
    private VehicleType type;
    private String licensePlate;
    private Color color;

    public StoredVehicle(VehicleType type, String licensePlate, Color color) {
        this.type = type;
        this.licensePlate = licensePlate;
        this.color = color;
    }

    public VehicleType getType() {
        return type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Color getColor() {
        return color;
    }
}
