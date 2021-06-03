package com.adast_adamov.business_helper.models;

import lombok.Data;

import java.util.List;

@Data
public class Week {
    List<Day> days;

    public Week(List<Day> days) {
        this.days = days;
    }
}
