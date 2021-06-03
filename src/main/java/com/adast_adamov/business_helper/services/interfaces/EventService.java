package com.adast_adamov.business_helper.services.interfaces;

import com.adast_adamov.business_helper.models.Day;
import com.adast_adamov.business_helper.models.Week;

import java.util.List;

public interface EventService {

    List<Day> getCalendar(int year, int month);

    List<Week> getWeeks(int year, int month);

}
