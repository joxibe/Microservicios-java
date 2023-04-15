package com.microservicios.carservice.service;


import com.microservicios.carservice.entity.Car;
import com.microservicios.carservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> getALL(){
        return carRepository.findAll();
    }

    public Car getCarById(int id){
        return carRepository.findById(id).orElse(null);
    }

    public Car save(Car car){
        //creamos una variable y guardamos el car en ella
        Car carNew = carRepository.save(car);
        return carNew;
    }

    public List<Car> byUserId(int userId){
        return carRepository.findByUserId(userId);
    }
}
