package com.adast_adamov.business_helper.repositories.interfaces;

import com.adast_adamov.business_helper.models.Day;

import java.time.Month;
import java.time.Year;
import java.util.List;

public interface EventRepository {
    List<Day> getCalendar(int year, int month);
}
