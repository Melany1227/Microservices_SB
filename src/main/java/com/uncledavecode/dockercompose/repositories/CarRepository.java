package com.uncledavecode.dockercompose.repositories;

import com.uncledavecode.dockercompose.model.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
