package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.*;

@Aspect
@Component
public class CounterAspect {

    public static Map<String, Integer> eventAccessCounter = new HashMap<>();
    public static Map<Event, Integer> eventPriceAccessCounter = new HashMap<>();
    public static Map<Event, Integer> ticketBookingAccessCounter = new HashMap<>();

    private static Set<Event> events = new HashSet<>();

    private static final String SQL_ERROR_STATE_SCHEMA_EXISTS = "X0Y68";
    private static final String SQL_ERROR_STATE_TABLE_EXISTS = "X0Y32";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${jdbc.username}")
    private String schema;

    @PostConstruct
    public void init() {
        createDBSchema();
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        try {
            jdbcTemplate.update("CREATE TABLE t_counter_event_aspect (" + "name VARCHAR(255)," + "counter INT" + ")");

            System.out.println("Created table t_counter_event_aspect");
        } catch (DataAccessException e) {
            Throwable causeException = e.getCause();
            if (causeException instanceof SQLException) {
                SQLException sqlException = (SQLException) causeException;
                if (sqlException.getSQLState().equals(SQL_ERROR_STATE_TABLE_EXISTS)) {
                    System.out.println("t_counter_event_aspect table already exists");
                } else {
                    throw e;
                }
            } else {
                throw e;
            }
        }
    }

    private void createDBSchema() {
        try {
            jdbcTemplate.update("CREATE SCHEMA " + schema);
        } catch (DataAccessException e) {
            Throwable causeException = e.getCause();
            if (causeException instanceof SQLException) {
                SQLException sqlException = (SQLException) causeException;
                if (sqlException.getSQLState().equals(SQL_ERROR_STATE_SCHEMA_EXISTS)) {
                    System.out.println("Schema already exists");
                } else {
                    throw e;
                }
            } else {
                throw e;
            }
        }
    }

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
        String query = "SELECT * FROM t_counter_event_aspect WHERE name='" + eventName + "'";
        Collection<Event> events = jdbcTemplate.query(query, (rs, rowNum) -> {
            Event event = new Event();
            event.setName(rs.getString("name"));
            return event;
        });
        if (!events.isEmpty()) {
            jdbcTemplate.update("UPDATE t_counter_event_aspect SET counter = counter + 1 WHERE name=?", eventName);
            System.out.println("updated counter for event: " + eventName);
        } else {
            jdbcTemplate.update("INSERT INTO t_counter_event_aspect (name, counter) VALUES (?, ?)", eventName, 1);
            System.out.println("started counter for event: " + eventName);
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
