package com.api.apicontrol.controllers;

import com.api.apicontrol.services.ParkingSpotService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/control")
public class ParkingSpotController {
    final ParkingSpotService parkingSpotService;

    // Construtor. Um ponto de injeção para ParkingSpotService
    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }
}
