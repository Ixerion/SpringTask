package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Event;

import java.util.*;

public class EventDao {

    private static Set<Event> events = new HashSet<>();

    public void add(Event object) {
        events.add(object);
    }

    public void remove(Event object) {
        events.remove(object);
    }

    public Collection<Event> getAll() {
        return events;
    }

    public static void setEvents(Set<Event> events) {
        EventDao.events = events;
    }
}
