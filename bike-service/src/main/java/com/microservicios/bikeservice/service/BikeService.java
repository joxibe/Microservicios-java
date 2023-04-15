package com.microservicios.bikeservice.service;



import com.microservicios.bikeservice.entity.Bike;
import com.microservicios.bikeservice.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    @Autowired
    BikeRepository bikeRepository;

    public List<Bike> getALL(){
        return bikeRepository.findAll();
    }

    public Bike getBikeById(int id){
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike save(Bike bike){
        //creamos una variable y guardamos el bike en ella
        Bike bikeNew = bikeRepository.save(bike);
        return bikeNew;
    }

    public List<Bike> findByUserId(int userId){
        return bikeRepository.findByUserId(userId);
    }
}
