package com.api.apicontrol.repositories;

import com.api.apicontrol.models.ParkingSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {
    // JpaRepository: Traz queries para facilitar a implementação
    
}
