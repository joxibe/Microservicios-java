package com.microservicios.bikeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data   //para constructores
@NoArgsConstructor //constructor sin argumentos
@AllArgsConstructor  //constructor con argumentos
public class Bike {

    //Lombok

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private String model;

    private int userId;

}
