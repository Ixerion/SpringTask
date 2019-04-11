package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Aspect
@Component
public class CounterAspect {

    public static Map<String, Integer> eventAccessCounter = new HashMap<>();
    public static Map<Event, Integer> eventPriceAccessCounter = new HashMap<>();
    public static Map<Event, Integer> ticketBookingAccessCounter = new HashMap<>();

    @Pointcut("execution(* *.getEventByName(..))")
    private void eventAccess() {
    }

    @Pointcut("execution(* *.getTicketsPrice(..))")
    private void eventPriceAccess() {
    }

    @Pointcut("execution(* *.bookTickets(..))")
    private void ticketBookingAccess() {

    }

    @Before("eventAccess()")
    public void countEventAccess(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String eventName = (String) args[0];
        if (eventAccessCounter.containsKey(eventName)) {
            eventAccessCounter.put(eventName, eventAccessCounter.get(eventName) + 1);
        } else {
            eventAccessCounter.put(eventName, 1);
        }
    }

    @Before("eventPriceAccess()")
    public void countPriceAccess(JoinPoint joinPoint) {
        System.out.println("countPriceAccess called!");
        Object[] args = joinPoint.getArgs();
        Event event = (Event) args[0];
        if (eventPriceAccessCounter.containsKey(event)) {
            eventPriceAccessCounter.put(event, eventPriceAccessCounter.get(event) + 1);
            System.out.println("Current amount of price calls for event : "  + event.getName() + " = "
                    + eventPriceAccessCounter.get(event));
        } else {
            eventPriceAccessCounter.put(event, 1);
        }
    }

    @Before("ticketBookingAccess()")
    public void countTicketBookingAccess(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        HashSet<Ticket> tickets = (HashSet<Ticket>) args[0];
        Event event = tickets.stream().findFirst().get().getEvent();
        if (ticketBookingAccessCounter.containsKey(event)) {
            ticketBookingAccessCounter.put(event, ticketBookingAccessCounter.get(event) + 1);
            System.out.println("Current amount of booking calls for event : "  + event.getName() + " = "
                    + ticketBookingAccessCounter.get(event));
        } else {
            ticketBookingAccessCounter.put(event, 1);
        }
    }
}
