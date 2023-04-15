package com.microservicios.userservice.service;

import com.microservicios.userservice.entity.User;
import com.microservicios.userservice.feignclients.BikeFeignClients;
import com.microservicios.userservice.feignclients.CarFeignClients;
import com.microservicios.userservice.model.Bike;
import com.microservicios.userservice.model.Car;
import com.microservicios.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeignClients carFeignClients;

    @Autowired
    BikeFeignClients bikeFeignClients;


    public List<User> getALL(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user){
        //creamos una variable y guardamos el user en ella
        User userNew = userRepository.save(user);
        return userNew;
    }

    //Conectamos la peticion getByUserId de car
    public List<Car> getCars(int userId){
        List<Car> cars = restTemplate.getForObject("http://car-service/car/byuser/" + userId, List.class);
        return cars;
    }

    //Conectamos la peticion getByUserId de bike
    public List<Bike> getBikes(int userId){
        List<Bike> bikes = restTemplate.getForObject("http://bike-service/bike/byuser/" + userId, List.class);
        return bikes;
    }

    public Car saveCar(int userId, Car car){
        car.setUserId(userId);
        Car carNew = carFeignClients.save(car);
        return carNew;
    }

    public Bike saveBike(int userId, Bike bike){
        bike.setUserId(userId);
        Bike bikeNew = bikeFeignClients.save(bike);
        return bikeNew;
    }

    public Map<String, Object> getUserAndVehicles(int userId){
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            result.put("Mensaje", "No existe el usuario");
            return result;
        }
        result.put("User", user);

        List<Car> cars = carFeignClients.getCars(userId);
        if(cars.isEmpty())
            result.put("Cars", "usario no tiene carros");
        else
            result.put("Cars", cars);

        List<Bike> bikes = bikeFeignClients.getBikes(userId);
        if(bikes.isEmpty())
            result.put("Bikes", "usuario no tiene motos");
        else
            result.put("Bikes", bikes);

        return result;
    }
}
