package com.nnamdi.gpi.drones.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnamdi.gpi.drones.dto.DroneDto;
import com.nnamdi.gpi.drones.request.RegisterDroneDto;
import com.nnamdi.gpi.drones.model.Drone;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@ApplicationScoped
@Slf4j
public class EntityMapper {
    @Inject
    ObjectMapper mapper;

    public Drone toEntity(RegisterDroneDto dto) {
        System.out.println(dto);
       return Drone.builder().name(dto.name()).direction(dto.direction()).coordinateX(dto.coordinateX()).coordinateY(dto.coordinateY()).build();
    }

    public DroneDto toDto(Drone entity) {
        return  mapper.convertValue(entity, DroneDto.class);
    }

    public List<DroneDto> mapDroneListToDto(List<Drone> droneList) {
        return droneList.stream()
                .map(this::toDto)
                .toList();
    }

    public String convertRegisterDtoToJson(RegisterDroneDto dto) throws JsonProcessingException {
        return  mapper.writeValueAsString(dto);
    }

    public String convertDtoToJson(DroneDto dto) throws JsonProcessingException {
        return  mapper.writeValueAsString(dto);
    }
}
