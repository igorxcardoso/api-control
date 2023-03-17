package com.api.apicontrol.services;

import com.api.apicontrol.models.ParkingSpotModel;
import com.api.apicontrol.repositories.ParkingSpotRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ParkingSpotService {
    final ParkingSpotRepository parkingSpotRepository;

    // Indica para o spring que ele vai ter injetar uma dependência de ParkingSpotRepository dentro de ParkingSpotService
    // Construtor. Injeção para ParkingSpotRepository
    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @Transactional
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
        return parkingSpotRepository.save(parkingSpotModel);
    }
}
