package com.nnamdi.gpi.drones.service;

import com.nnamdi.gpi.drones.dto.DroneDto;
import com.nnamdi.gpi.drones.request.RegisterDroneDto;
import com.nnamdi.gpi.drones.exception.ModelAlreadyExistException;
import com.nnamdi.gpi.drones.model.Drone;
import com.nnamdi.gpi.drones.repository.DroneRepository;
import com.nnamdi.gpi.drones.service.impl.DroneServiceImpl;
import com.nnamdi.gpi.drones.util.EntityMapper;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static com.nnamdi.gpi.drones.mock.TestMock.buildDrone;
import static com.nnamdi.gpi.drones.mock.TestMock.registerDroneDto;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@QuarkusTest
public class DroneServiceTest {

    @InjectMock
    DroneService droneService;

    @InjectMock
    DroneRepository droneRepository;

    @InjectMock
    EntityMapper entityMapper;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        droneService = new DroneServiceImpl(droneRepository, entityMapper);
    }

    @Test
    public void testRegisterDrone() {
        RegisterDroneDto droneDto = registerDroneDto();
        Drone drone = buildDrone(droneDto);
        when(entityMapper.toEntity(any(RegisterDroneDto.class))).thenReturn(drone);
        when(droneRepository.findByCoordinatesOrName(anyInt(), anyInt(), anyString()))
                .thenReturn(Uni.createFrom().nullItem());
        when(droneRepository.persist(any(Drone.class))).thenReturn(Uni.createFrom().item(drone));

        Uni<DroneDto> result = droneService.registerDrone(droneDto);
        assertNotNull(result);


    }


    @Test
    public void testRegisterDrone_throws_ModelAlreadyExist() {
        RegisterDroneDto droneDto = registerDroneDto();
        Drone drone = buildDrone(droneDto);
        when(entityMapper.toEntity(any(RegisterDroneDto.class))).thenReturn(drone);
        Uni<Drone> uniDrone = Uni.createFrom().item(drone);
        when(droneRepository.findByCoordinatesOrName(anyInt(), anyInt(), anyString()))
                .thenReturn(uniDrone);
        assertThatThrownBy(() -> droneService.registerDrone(droneDto).await().indefinitely())
                .isInstanceOf(ModelAlreadyExistException.class)
                .hasMessageContaining("Drone already exists");


    }
}
