package com.uncledavecode.dockercompose.model.dtos;

import com.uncledavecode.dockercompose.model.entities.Car;

public record CarResult(long id, String brand, String model, Integer year, String color) {

    public static CarResult from (Car car){
        return new CarResult(car.getId(), car.getBrand(), car.getModel(), car.getYear(), car.getColor());
    }
}
