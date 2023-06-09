package com.microservicios.userservice.feignclients;

import com.microservicios.userservice.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service", url = "http://localhost:8002")
@RequestMapping("/car")
public interface CarFeignClients {

    @PostMapping
     Car save(@RequestBody Car car);

    @GetMapping("/byuser/{userId}")
    List<Car> getCars(@PathVariable("userId") int userId);

}
