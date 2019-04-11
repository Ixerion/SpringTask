package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.EventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

@Component
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    @Nullable
    @Override
    public Event getEventByName(@Nonnull String name) {
        return eventDao.getAll().stream()
                .filter(event -> event.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Event save(@Nonnull Event object) {
        eventDao.add(object);
        return object;
    }

    @Override
    public void remove(@Nonnull Event object) {
        eventDao.remove(object);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return eventDao.getAll().stream()
                .filter(event -> event.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventDao.getAll();
    }

    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }
}
