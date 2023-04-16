package com.api.apicontrol.controllers;

import com.api.apicontrol.dtos.ParkingSpotDto;
import com.api.apicontrol.models.ParkingSpotModel;
import com.api.apicontrol.services.ParkingSpotService;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/control") // A URI foi definida a nível de classe
public class ParkingSpotController {
    final ParkingSpotService parkingSpotService;

    // Construtor. Um ponto de injeção para ParkingSpotService
    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    // POST  /control
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

    // GET /control
    @GetMapping
    public ResponseEntity<List<ParkingSpotModel>> indexParkingSpots() {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());
    }

    // GET  /control/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Object> showParkingSpots(@PathVariable(value = "id") UUID id) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);

        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
    }

    // DELETE   /control/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroyParkingSpot(@PathVariable(value = "id") UUID id) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);

        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }

        parkingSpotService.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot deleted successfully.");
    }

    // DELETE  /control
    @DeleteMapping
    public ResponseEntity<Object> destroyAllParkingSpot() {
        parkingSpotService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("All of the Parking Spots were deleted.");
    }

    // PUT  /control/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id, @RequestBody @Valid ParkingSpotDto parkingSpotDto) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById((id));

        if(!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }

        // Campo a campo
        // var parkingSpotModel = parkingSpotModelOptional.get();
        // parkingSpotModel.setParkingSpotNumber(parkingSpotDto.getParkingSpotNumber());
        // parkingSpotModel.setLicensePlateCar(parkingSpotDto.getLicensePlateCar());
        // parkingSpotModel.setModelCar(parkingSpotDto.getModelCar());
        // parkingSpotModel.setBrandCar(parkingSpotDto.getBrandCar());
        // parkingSpotModel.setColorCar(parkingSpotDto.getColorCar());
        // parkingSpotModel.setResponsibleName(parkingSpotDto.getResponsibleName());
        // parkingSpotModel.setApartment(parkingSpotDto.getApartment());
        // parkingSpotModel.setBlock(parkingSpotDto.getBlock());

        // Pegar todos campo, e só carregando o id e a data deregistro
        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setId(parkingSpotModelOptional.get().getId());
        parkingSpotModel.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());

        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));
    }
}
