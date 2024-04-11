package com.nnamdi.gpi.drones.util;


import com.nnamdi.gpi.drones.dto.Error;
import com.nnamdi.gpi.drones.dto.Response;
import com.nnamdi.gpi.drones.dto.ResponseCodes;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResponseUtil {
    public Response getSuccessResponse(Object data) {
        return new Response(ResponseCodes.SUCCESS.code(), ConstantsUtil.SUCCESSFUL, data, null);
    }

    public Response getErrorResponse(Error err) {
        return new Response(err.getCode(), err.getMessage(), null, err.getDescriptiveMessage());
    }
}
