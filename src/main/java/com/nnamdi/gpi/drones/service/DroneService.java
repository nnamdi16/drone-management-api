package com.nnamdi.gpi.drones.service;

import com.nnamdi.gpi.drones.dto.DroneDto;
import com.nnamdi.gpi.drones.dto.PaginatedResponse;
import com.nnamdi.gpi.drones.request.RegisterDroneDto;
import com.nnamdi.gpi.drones.dto.UpdateDroneDto;
import com.nnamdi.gpi.drones.model.Drone;
import io.smallrye.mutiny.Uni;

public interface DroneService {
    Uni<DroneDto> registerDrone(RegisterDroneDto drone);
    Uni<DroneDto> moveDrone(String id, UpdateDroneDto updateDroneDto);

    Uni<Drone> getDronePosition(String id);

    Uni<PaginatedResponse<DroneDto>> getDrones(int page, int limit);


}
