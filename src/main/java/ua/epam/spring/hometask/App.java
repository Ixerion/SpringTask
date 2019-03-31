package ua.epam.spring.hometask;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.DiscountService;
import ua.epam.spring.hometask.service.DiscountStrategy;
import ua.epam.spring.hometask.service.impl.BookingServiceImpl;
import ua.epam.spring.hometask.service.impl.DiscountServiceImpl;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        DiscountServiceImpl discountService = (DiscountServiceImpl) ctx.getBean("discountService");
        List<DiscountStrategy> discountStrategyList = discountService.getDiscountStrategies();
        BookingServiceImpl bookingService = (BookingServiceImpl) ctx.getBean("bookingService");
        LocalDateTime dateTime = LocalDateTime.of(1995, Month.DECEMBER, 1, 1, 1);
        User user = (User) ctx.getBean("user");
        user.setBirthday(dateTime.toLocalDate());
        Event event = (Event) ctx.getBean("event");
        Auditorium auditorium = (Auditorium) ctx.getBean("smallAuditorium");
        NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();
        auditoriums.put(dateTime, auditorium);
        event.setAuditoriums(auditoriums);
        Set<Long> seats = new HashSet<>(Arrays.asList(12L, 2L));
        double price = bookingService.getTicketsPrice(event, dateTime, user, seats);
        System.out.println(price);
    }
}
