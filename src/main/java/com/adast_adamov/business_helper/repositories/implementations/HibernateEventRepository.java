package com.adast_adamov.business_helper.repositories.implementations;

import com.adast_adamov.business_helper.models.Day;
import com.adast_adamov.business_helper.repositories.interfaces.EventRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.LinkedList;
import java.util.List;

@Repository
public class HibernateEventRepository implements EventRepository {

    @Override
    public List<Day> getCalendar(int year, int month) {
        return getEmptyCalendar(year, month);
    }

    private List<Day> getEmptyCalendar(int year, int month) {
        LocalDate date = LocalDate.of(year, month, 1);
        int dayOfWeek = date.getDayOfWeek().getValue();
        date = date.minusDays(dayOfWeek - 1);

        List<Day> days = new LinkedList<>();
        while(date.getMonth().getValue() == month || days.size() == 0 || days.size() % 7 != 0) {
            days.add(new Day(date));
            date = date.plusDays(1);
        }

        return days;
    }
}
