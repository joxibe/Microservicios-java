package com.microservicios.userservice.controller;

import com.microservicios.userservice.entity.User;
import com.microservicios.userservice.model.Bike;
import com.microservicios.userservice.model.Car;
import com.microservicios.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        //creamos una lista users y guardamos los usuarios obtenidos
        List<User> users = userService.getALL();
        if(users.isEmpty()) //si esta vacio devolvemos noContent
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users); //si no, devolvemos la lista de users

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id){
        // creamos una instancia de User y guardamos el encontrado por id
        User user = userService.getUserById(id);
        if(user == null) //
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user); //

    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){
        // creamos una instancia de User y guardamos el encontrado por id
        User userNew = userService.save(user);
        return ResponseEntity.ok(userNew); //
    }


    //metodo para obtener los objetos de car por http
    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int userId){
        User user = userService.getUserById(userId);
        if(user == null)
            return ResponseEntity.notFound().build();
        List<Car> cars = userService.getCars(userId);
        return ResponseEntity.ok(cars);
    }

    //metodo para obtener los objetos de bike por http
    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int userId){
        User user = userService.getUserById(userId);
        if(user == null)
            return ResponseEntity.notFound().build();
        List<Bike> bikes = userService.getBikes(userId);
        return ResponseEntity.ok(bikes);
    }

    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable("userId") int userId, @RequestBody Car car){
        if(userService.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        Car carNew = userService.saveCar(userId, car);
        return ResponseEntity.ok(carNew);
    }

    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike> saveBike(@PathVariable("userId") int userId, @RequestBody Bike bike){
        if(userService.getUserById(userId) == null)
            return ResponseEntity.notFound().build();
        Bike bikeNew = userService.saveBike(userId, bike);
        return ResponseEntity.ok(bikeNew);
    }

    @GetMapping("/getAll/{userId}")
    public ResponseEntity<Map<String, Object>> getAll(@PathVariable("userId") int userId){
        Map<String, Object> result = userService.getUserAndVehicles(userId);
        return ResponseEntity.ok(result);
    }

}
