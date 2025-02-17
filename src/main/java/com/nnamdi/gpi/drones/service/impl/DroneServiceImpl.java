package com.nnamdi.gpi.drones.service.impl;

import com.nnamdi.gpi.drones.dto.DroneDto;
import com.nnamdi.gpi.drones.dto.PaginatedResponse;
import com.nnamdi.gpi.drones.request.RegisterDroneDto;
import com.nnamdi.gpi.drones.dto.UpdateDroneDto;
import com.nnamdi.gpi.drones.exception.ModelAlreadyExistException;
import com.nnamdi.gpi.drones.model.Drone;
import com.nnamdi.gpi.drones.repository.DroneRepository;
import com.nnamdi.gpi.drones.service.DroneService;
import com.nnamdi.gpi.drones.util.AppUtil;
import com.nnamdi.gpi.drones.util.EntityMapper;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Page;
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
        Log.infof("about to register a drone %s", droneDto);
       Drone drone = mapper.toEntity(droneDto);
       Log.infof("Drone maker %s", drone);
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
    @WithTransaction
    @WithSession
    public Uni<PaginatedResponse<DroneDto>> getDrones(int page, int limit) {
        AppUtil.validatePageRequest(page, limit);
        Page pagination = Page.of(page - 1, limit);

        return droneRepository.count().flatMap(count -> {
            int totalPages = (int) Math.ceil((double) count / limit);
            boolean last = page-1 == totalPages - 1;
            boolean first = page-1 == 0;

            return droneRepository.findAll().page(pagination)
                    .list()
                    .map(drones -> new PaginatedResponse<>(mapper.mapDroneListToDto(drones), totalPages, count, last, first, drones.size(), limit, page));
        });
    }
}

