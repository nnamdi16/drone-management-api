package com.nnamdi.gpi.drones.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.nnamdi.gpi.drones.dto.DroneDto;
import com.nnamdi.gpi.drones.request.RegisterDroneDto;
import com.nnamdi.gpi.drones.mock.TestMock;
import com.nnamdi.gpi.drones.service.DroneService;
import com.nnamdi.gpi.drones.util.EntityMapper;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@QuarkusTest
public class DroneControllerTest {
    @Inject
    EntityMapper entityMapper;

    @InjectMock
    DroneService droneService;



    @Test
    public void testRegisterDrone() throws JsonProcessingException {
        DroneDto droneDto = TestMock.buildDroneDto();

        String droneResponse = entityMapper.convertDtoToJson(droneDto);

        when(droneService.registerDrone(Mockito.any(RegisterDroneDto.class)))
                .thenReturn(Uni.createFrom().item(droneDto));
        String requestBody = entityMapper.convertRegisterDtoToJson(TestMock.registerDroneDto());
        given().
                contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/v1/drone")
                .then().statusCode(200).body(containsString(droneResponse));
    }


    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello from RESTEasy Reactive"));
    }
}
