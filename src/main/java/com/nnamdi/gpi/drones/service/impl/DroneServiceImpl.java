package com.nnamdi.gpi.drones.service.impl;

import com.nnamdi.gpi.drones.dto.DroneDto;
import com.nnamdi.gpi.drones.dto.RegisterDroneDto;
import com.nnamdi.gpi.drones.dto.UpdateDroneDto;
import com.nnamdi.gpi.drones.exception.ModelAlreadyExistException;
import com.nnamdi.gpi.drones.model.Drone;
import com.nnamdi.gpi.drones.repository.DroneRepository;
import com.nnamdi.gpi.drones.service.DroneService;
import com.nnamdi.gpi.drones.util.EntityMapper;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.RequestScoped;
import lombok.extern.slf4j.Slf4j;

@RequestScoped
@Slf4j
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;
    private final EntityMapper mapper;

    public DroneServiceImpl(DroneRepository droneRepository, EntityMapper mapper) {
        this.droneRepository = droneRepository;
        this.mapper = mapper;
    }

    @Override
    @WithTransaction
    @WithSession
    public Uni<DroneDto> registerDrone(RegisterDroneDto droneDto) {
        log.info("about to register a drone {}", droneDto);
       Drone drone = mapper.toEntity(droneDto);
       log.info("Drone maker {}", drone);
        return checkIfDroneExists(drone)
                .onItem().transformToUni(Unchecked.function(existingDrone -> {
                    if (existingDrone != null) {
                        log.error("Drone already exists: {}", existingDrone);
                        throw new ModelAlreadyExistException("Drone already exists");
                    } else {
                        return persistDrone(drone);
                    }
                }));

    }



    private Uni<Drone> checkIfDroneExists(Drone drone) {

        return droneRepository.findByCoordinatesOrName(drone.getCoordinateX(), drone.getCoordinateY(), drone.getName());
    }

    private Uni<DroneDto> persistDrone(Drone drone) {
        return droneRepository.persist(drone)
                .onItem().transform(persistedDrone -> {
                    log.info("Drone persisted successfully: {}", persistedDrone);
                    return mapper.toDto(persistedDrone);
                });
    }

    @Override
    public Uni<DroneDto> moveDrone(String id, UpdateDroneDto updateDroneDto) {
        return null;
    }

    @Override
    public Uni<Drone> getDronePosition(String id) {
        return null;
    }

    @Override
    public Multi<DroneDto> getDrones(int page, int limit) {
        return null;
    }
}
