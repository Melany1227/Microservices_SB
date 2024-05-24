package com.uncledavecode.dockercompose.services;

import com.uncledavecode.dockercompose.model.dtos.CarResult;
import com.uncledavecode.dockercompose.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<CarResult> getAllCars(){
        return carRepository.findAll()
                .stream()
                .map(CarResult::from)
                .collect(Collectors.toList());
    }
}
