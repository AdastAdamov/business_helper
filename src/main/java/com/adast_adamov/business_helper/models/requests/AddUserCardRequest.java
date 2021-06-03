package com.adast_adamov.business_helper.models.requests;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddUserCardRequest {
    Long productId;
    LocalDate lastOperationDate;
}
