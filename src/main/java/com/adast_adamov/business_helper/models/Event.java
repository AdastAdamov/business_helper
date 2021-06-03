package com.adast_adamov.business_helper.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Event {

    LocalDate date;
    String description;
}
