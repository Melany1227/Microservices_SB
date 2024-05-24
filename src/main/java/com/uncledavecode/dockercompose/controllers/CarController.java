package com.uncledavecode.dockercompose.controllers;

import com.uncledavecode.dockercompose.model.dtos.CarResult;
import com.uncledavecode.dockercompose.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping

    public List<CarResult> getAllCars(){
        return carService.getAllCars();
    }
}
