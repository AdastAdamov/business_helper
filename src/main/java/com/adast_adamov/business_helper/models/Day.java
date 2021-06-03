package com.adast_adamov.business_helper.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Day {

    int dayOfWeek;
    LocalDate date;
    List<Event> events;

    public Day(LocalDate date) {
        this.date = date;
        dayOfWeek = date.getDayOfWeek().getValue();
    }
}
