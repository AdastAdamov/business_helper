package com.adast_adamov.business_helper.models.responses;

import lombok.Data;

@Data
//TODO Replace with ResponseStatusException
public class AuthorizationResponse {

    private Boolean isAuthorized;
    private Integer errorCode;
    private String reason;

    public AuthorizationResponse(Boolean isAuthorized, Integer errorCode, String reason) {
        this.isAuthorized = isAuthorized;
        this.errorCode = errorCode;
        this.reason = reason;
    }
}
