package com.nnamdi.gpi.drones.dto;

public enum ResponseCodes {
    SUCCESS(0),

    INVALID_REQUEST(1), NOT_FOUND(2);


    private final Integer code;

    ResponseCodes(Integer code) {
        this.code = code;
    }

    public Integer code() {
        return code;
    }
}
