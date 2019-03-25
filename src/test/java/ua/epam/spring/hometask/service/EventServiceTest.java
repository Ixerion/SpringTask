package ua.epam.spring.hometask.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    @Test
    public void eventServiceSaveEventTest() {
        Set<Event> events = new HashSet<>(eventService.getAll());
        Event event = new Event("Presentation", 150.0, EventRating.MID);
        eventService.save(event);
        Set<Event> updatedEvents = new HashSet<>(eventService.getAll());
        Assert.assertEquals("Event saved successfully", events.size(), updatedEvents.size());
    }

}
