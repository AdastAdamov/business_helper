package com.adast_adamov.business_helper.models.requests;

import lombok.Data;

@Data
public class AuthorizationRequest {
    String login;
    String password;
}
