package com.nnamdi.gpi.drones.mock;

import com.nnamdi.gpi.drones.dto.DroneDto;
import com.nnamdi.gpi.drones.request.RegisterDroneDto;
import com.nnamdi.gpi.drones.model.Drone;
import com.nnamdi.gpi.drones.util.Direction;

import java.time.ZonedDateTime;

public class TestMock {
    public static final String ID = "8D19B947443D4C1BB2700337527BC251";
    public static final String DRONE_NAME = "DRONE";

    public static RegisterDroneDto registerDroneDto() {
        return new RegisterDroneDto(4, 4, DRONE_NAME, Direction.WEST);

    }

    public static RegisterDroneDto registerDroneBadRequestDto() {
        return new RegisterDroneDto(-10, 10, DRONE_NAME, Direction.WEST);
    }


    public static DroneDto buildDroneDto() {
        return new DroneDto(ID,10,10,DRONE_NAME,Direction.WEST);
    }



    public static Drone buildDrone(RegisterDroneDto droneDto) {
        Drone drone = Drone.builder().name(droneDto.name()).direction(droneDto.direction()).coordinateX(droneDto.coordinateX()).coordinateY(droneDto.coordinateY()).build();
        drone.setId(ID);
        drone.setCreatedDate(ZonedDateTime.now());
        drone.setLastModifiedDate(ZonedDateTime.now());
        return drone;
    }

}
