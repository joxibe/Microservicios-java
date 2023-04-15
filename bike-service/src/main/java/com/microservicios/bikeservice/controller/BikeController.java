package com.microservicios.bikeservice.controller;

import com.microservicios.bikeservice.entity.Bike;
import com.microservicios.bikeservice.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll(){
        //creamos una lista bikes y guardamos los usuarios obtenidos
        List<Bike> bikes = bikeService.getALL();
        if(bikes.isEmpty()) //si esta vacio devolvemos noContent
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(bikes); //si no, devolvemos la lista de bikes

    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getById(@PathVariable("id") int id){
        // creamos una instancia de Bike y guardamos el encontrado por id
        Bike bike = bikeService.getBikeById(id);
        if(bike == null) //
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bike); //

    }

    @PostMapping
    public ResponseEntity<Bike> save(@RequestBody Bike bike){
        // creamos una instancia de Bike y guardamos el encontrado por id
        Bike bikeNew = bikeService.save(bike);
        return ResponseEntity.ok(bikeNew); //

    }

    //conectamos con user este metodo http
    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable("userId") int userId){
        //creamos una lista bikes y guardamos los usuarios obtenidos
        List<Bike> bikes = bikeService.findByUserId(userId);
//        if(bikes.isEmpty()) //si esta vacio devolvemos noContent
//            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(bikes); //si no, devolvemos la lista de bikes

    }

}
