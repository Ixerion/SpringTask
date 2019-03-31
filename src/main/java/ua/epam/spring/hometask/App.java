package ua.epam.spring.hometask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.config.AppConfiguration;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.*;
import ua.epam.spring.hometask.service.impl.AuditoriumServiceImpl;
import ua.epam.spring.hometask.service.impl.BookingServiceImpl;
import ua.epam.spring.hometask.service.impl.DiscountServiceImpl;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

@Component
public class App {

    @Autowired
    public AuditoriumService auditoriumServiceImpl;

    @Autowired
    public BookingService bookingServiceImpl;

    @Autowired
    public DiscountServiceImpl discountServiceImpl;

    @Autowired
    public EventService eventServiceImpl;

    @Autowired
    public UserService userServiceImpl;

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
        App app = ctx.getBean(App.class);
        Set<Auditorium> auditorums = app.auditoriumServiceImpl.getAll();
        Collection<User> users = app.userServiceImpl.getAll();
        List<DiscountStrategy> discountStrategyList = app.discountServiceImpl.getDiscountStrategies();
        Collection<Event> events = app.eventServiceImpl.getAll();
        System.out.println();
        /*DiscountServiceImpl discountService = (DiscountServiceImpl) ctx.getBean("discountService");
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
        System.out.println(price);*/
    }

    public void setAuditoriumServiceImpl(AuditoriumService auditoriumServiceImpl) {
        this.auditoriumServiceImpl = auditoriumServiceImpl;
    }

    public void setBookingServiceImpl(BookingService bookingServiceImpl) {
        this.bookingServiceImpl = bookingServiceImpl;
    }

    public void setEventServiceImpl(EventService eventServiceImpl) {
        this.eventServiceImpl = eventServiceImpl;
    }

    public void setUserServiceImpl(UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    public AuditoriumService getAuditoriumServiceImpl() {
        return auditoriumServiceImpl;
    }

    public BookingService getBookingServiceImpl() {
        return bookingServiceImpl;
    }

    public DiscountService getDiscountServiceImpl() {
        return discountServiceImpl;
    }

    public EventService getEventServiceImpl() {
        return eventServiceImpl;
    }

    public UserService getUserServiceImpl() {
        return userServiceImpl;
    }
}
