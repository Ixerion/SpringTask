package ua.epam.spring.hometask.aspects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.config.AppConfiguration;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.EventService;
import ua.epam.spring.hometask.service.UserService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfiguration.class)
public class CounterAspectTest {

    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookingService bookingService;

    private Event event;
    private User user;

    @Before
    public void init() {
        String eventName = "Presentation";
        event = new Event(eventName, 150.0, EventRating.MID);
        user = userService.getUserByEmail("ivanov@gmail.com");
    }

    @Test
    public void eventCounterAspectTest() {
        String eventName = "Presentation";
        Event event = new Event(eventName, 150.0, EventRating.MID);
        eventService.save(event);
        eventService.getEventByName(eventName);
        eventService.getEventByName(eventName);
        eventService.getEventByName(eventName);
        Assert.assertEquals(CounterAspect.eventAccessCounter.get(eventName).intValue(), 3);
    }

    @Test
    public void ticketBookingAspectTest() {
        Set<Ticket> tickets = new HashSet<>();
        tickets.add(new Ticket(user, event, LocalDateTime.now(), 1L));
        bookingService.bookTickets(tickets);
        Assert.assertEquals(CounterAspect.ticketBookingAccessCounter.get(event).intValue(), 1);

    }
}
