package com.microservicios.userservice.feignclients;

import com.microservicios.userservice.model.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "bike-service")
@RequestMapping("/bike")
public interface BikeFeignClients {

    @PostMapping
    Bike save(@RequestBody Bike bike);

    @GetMapping("/byuser/{userId}")
    List<Bike> getBikes(@PathVariable("userId") int userId);
}