package com.nnamdi.gpi.drones.dto;


import lombok.Data;

@Data
public class Error {

    private Integer code;
    private String message;
    private String descriptiveMessage;

    public Error(ResponseCodes code, String message, String descriptiveMessage) {
        this.code = code.code();
        this.message = message;
        this.descriptiveMessage = descriptiveMessage;
    }
}
