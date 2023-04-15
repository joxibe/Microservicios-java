package com.microservicios.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data   //para constructores
@NoArgsConstructor //constructor sin argumentos
@AllArgsConstructor  //constructor con argumentos
//@Table(name = "users")
public class User {

    //Lombok

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;

}
