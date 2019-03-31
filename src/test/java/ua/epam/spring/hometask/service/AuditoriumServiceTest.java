package ua.epam.spring.hometask.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.config.AppConfiguration;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfiguration.class)
public class AuditoriumServiceTest {

    private static final String SMALL_AUDITORIUM_NAME = "Small auditorium";

    @Autowired
    private AuditoriumService auditoriumService;

    @Test
    public void auditoriumServiceAuditoriumAmountTest() {
        int actualSize = auditoriumService.getAll().size();
        Assert.assertEquals(2, actualSize);
    }

    @Test
    public void auditoriumServiceAuditoriumSeatsTest() {
        int seatsAmount = auditoriumService.getByName(SMALL_AUDITORIUM_NAME).getAllSeats().size();
        Assert.assertEquals(20, seatsAmount);
    }

    @Test
    public void auditoriumServiceAuditoriumVipTest() {
        long vipId = 3;
        Set<Long> vipSeats = auditoriumService.getByName(SMALL_AUDITORIUM_NAME).getVipSeats();
        Assert.assertTrue(vipSeats.contains(vipId));
    }

}
