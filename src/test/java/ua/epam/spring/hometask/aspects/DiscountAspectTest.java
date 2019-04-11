package ua.epam.spring.hometask.aspects;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.config.AppConfiguration;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountService;
import ua.epam.spring.hometask.service.UserService;

import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfiguration.class)
public class DiscountAspectTest {

    @Autowired
    private DiscountService discountService;
    @Autowired
    private UserService userService;

    @Test
    public void discountServiceAspectTest() {
        Event event = new Event("Presentation", 150.0, EventRating.MID);
        User user = userService.getUserByEmail("ivanov@gmail.com");
        discountService.getDiscount(user, event, LocalDateTime.now(), 1);
        discountService.getDiscount(user, event, LocalDateTime.now(), 12);
        discountService.getDiscount(user, event, LocalDateTime.now(), 45);
        discountService.getDiscount(user, event, LocalDateTime.now().plusDays(3), 10);
        Assert.assertEquals(DiscountAspect.userDiscountsAccessCounter.get(user).intValue(), 4);
    }
}
