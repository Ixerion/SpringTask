package ua.epam.spring.hometask.aspects;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.config.AppConfiguration;
import ua.epam.spring.hometask.config.DBConfiguration;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.service.EventService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class, DBConfiguration.class})
public class DBAspectTest {

    @Autowired
    private EventService eventService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void aspectDatabaseCounterTest() {
        String eventName = "Presentation";
        Event event = new Event(eventName, 150.0, EventRating.MID);
        eventService.save(event);
        eventService.getEventByName(eventName);
        eventService.getEventByName(eventName);
        eventService.getEventByName(eventName);
        String query = "SELECT counter FROM t_counter_event_aspect WHERE name='" + eventName + "'";
        int counter = jdbcTemplate.queryForObject(query, Integer.class);
        Assert.assertEquals(counter, 3);
    }
}
