package com.adast_adamov.business_helper.services.implementations;

import com.adast_adamov.business_helper.models.Day;
import com.adast_adamov.business_helper.models.Week;
import com.adast_adamov.business_helper.repositories.interfaces.EventRepository;
import com.adast_adamov.business_helper.services.interfaces.EventService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Day> getCalendar(int year, int month) {
        return eventRepository.getCalendar(year, month);
    }

    @Override
    public List<Week> getWeeks(int year, int month) {
        List<Day> days = getCalendar(year, month);
        List<Week> weeks = new LinkedList<>();

        for (int i = 0; i < days.size(); i = i + 7) {
            weeks.add(new Week(days.subList(i, i + 7)));
        }

        return weeks;
    }
}
