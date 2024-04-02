package com.nnamdi.gpi.drones.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Provider
@Slf4j
public class ExceptionHandler implements ExceptionMapper<Exception> {

    public static final String BAD_REQUEST_EXCEPTION = "BadRequestException";
    public static final String MODEL_ALREADY_EXIST_EXCEPTION = "ModelAlreadyExistException";
    @Inject
    ObjectMapper objectMapper;

    @Override
    public Response toResponse(Exception exception) {
        log.error("Failed to handle request", exception);


        var code = switch (exception.getClass().getSimpleName()) {
            case BAD_REQUEST_EXCEPTION -> Response.Status.BAD_REQUEST;
            case MODEL_ALREADY_EXIST_EXCEPTION -> Response.Status.CONFLICT;
            default -> Response.Status.INTERNAL_SERVER_ERROR;
        };


        ObjectNode exceptionJson = objectMapper.createObjectNode();
        exceptionJson.put("exceptionType", exception.getClass().getName());
        exceptionJson.put("code", code.getStatusCode());

        if (exception.getMessage() != null) {
            exceptionJson.put("error", exception.getMessage());
        }

        return Response.status(code)
                .entity(exceptionJson)
                .build();
    }
}
