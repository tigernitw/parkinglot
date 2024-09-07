package com.stockgro.backend.model.vehicle;

import com.stockgro.backend.model.enums.Color;
import com.stockgro.backend.model.enums.VehicleType;

public class Truck extends Vehicle {

    public Truck(String licensePlate, Color color) {
        super(licensePlate, VehicleType.TRUCK, color);
    }
}
