package com.stockgro.backend.model.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.stockgro.backend.model.enums.Color;
import com.stockgro.backend.model.enums.VehicleType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = VehicleType.CAR_TEXT, value = Car.class),
        @JsonSubTypes.Type(
                name = VehicleType.BIKE_TEXT,
                value = Bike.class),
        @JsonSubTypes.Type(
                name = VehicleType.TRUCK_TEXT,
                value = Truck.class)
})
public abstract class Vehicle {

    @NotNull
    protected VehicleType type;

    @NotEmpty
    @JsonProperty("license_plate")
    protected String licensePlate;

    @NotNull
    protected Color color;

    public Vehicle(String licensePlate, VehicleType type, Color color) {
        this.licensePlate = licensePlate;
        this.type = type;
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
