package ua.epam.spring.hometask.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.config.AppConfiguration;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfiguration.class)
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    @Test
    public void eventServiceSaveEventTest() {
        Event event = new Event("Presentation", 150.0, EventRating.MID);
        eventService.save(event);
        Assert.assertTrue("Can't save event", eventService.getAll().contains(event));
    }

}
