package com.stockgro.backend.model.vehicle;

import com.stockgro.backend.model.enums.Color;
import com.stockgro.backend.model.enums.VehicleType;

public class Bike extends Vehicle {

    public Bike(String licensePlate, Color color) {
        super(licensePlate, VehicleType.BIKE, color);
    }
}
