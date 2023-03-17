package com.api.apicontrol.controllers;

import com.api.apicontrol.dtos.ParkingSpotDto;
import com.api.apicontrol.models.ParkingSpotModel;
import com.api.apicontrol.services.ParkingSpotService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/control") // A URI foi definida a nível de classe
public class ParkingSpotController {
    final ParkingSpotService parkingSpotService;

    // Construtor. Um ponto de injeção para ParkingSpotService
    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @PostMapping
    public ResponseEntity<Object> createParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto) {
        // O @RequestBody é para receber a requisição como json
        // O @Valid para fazer as validações dos DTOs. Se tiver algum erro com os dados na requisição, nem entra no método.


        if(parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use!");
        }
        if(parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!");
        }
        if(parkingSpotService.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot already registered for this apartment/block!");
        }

        var parkingSpotModel = new ParkingSpotModel();

        // Conversão de DTO para model, pois estamos recendo um DTO no método, mas persistimos um model.
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);

        // Faz o set da data de registro
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

        // ResponseEntity para construir a resposta
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
    }
}
