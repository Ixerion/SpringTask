package ua.epam.spring.hometask.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.config.AppConfiguration;
import ua.epam.spring.hometask.config.DBConfiguration;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class, DBConfiguration.class})
public class DBServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;
    @Autowired
    private User existedUser;

    @Test
    public void databaseSaveAndGetUserTest() {
        User user = new User();
        user.setId(10L);
        user.setFirstName("Nick");
        user.setEmail("qwe@mail.com");
        userService.save(user);
        userService.save(existedUser);
        Assert.assertEquals(userService.getAll().size(), 2);
    }

    @Test
    public void databaseSaveAndGedEventTest() {
        Event event = new Event();
        event.setName("Presentation");
        event.setBasePrice(345);
        eventService.save(event);
        Assert.assertEquals(eventService.getAll().size(), 1);
    }
}
