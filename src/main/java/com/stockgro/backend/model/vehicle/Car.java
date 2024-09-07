package com.stockgro.backend.model.vehicle;

import com.stockgro.backend.model.enums.Color;
import com.stockgro.backend.model.enums.VehicleType;

public class Car extends Vehicle {
    public Car(String licensePlate, Color color) {
        super(licensePlate, VehicleType.CAR, color);
    }
}
