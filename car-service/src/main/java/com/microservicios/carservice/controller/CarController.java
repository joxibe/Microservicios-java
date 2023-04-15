package com.microservicios.carservice.controller;

import com.microservicios.carservice.entity.Car;
import com.microservicios.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAll(){
        //creamos una lista cars y guardamos los usuarios obtenidos
        List<Car> cars = carService.getALL();
        if(cars.isEmpty()) //si esta vacio devolvemos noContent
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cars); //si no, devolvemos la lista de cars

    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable("id") int id){
        // creamos una instancia de Car y guardamos el encontrado por id
        Car car = carService.getCarById(id);
        if(car == null) //
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(car); //

    }

    @PostMapping
    public ResponseEntity<Car> save(@RequestBody Car car){
        // creamos una instancia de Car y guardamos el encontrado por id
        Car carNew = carService.save(car);
        return ResponseEntity.ok(carNew); //

    }

    //conectamos con user este metodo http
    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Car>> getByUserId(@PathVariable("userId") int userId){
        //creamos una lista cars y guardamos los usuarios obtenidos
        List<Car> cars = carService.byUserId(userId);
//        if(cars.isEmpty()) //si esta vacio devolvemos noContent
//            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cars); //si no, devolvemos la lista de cars
    }

}
