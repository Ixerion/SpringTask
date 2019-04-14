package ua.epam.spring.hometask.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Event;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class EventDao {

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
            jdbcTemplate.update("CREATE TABLE t_event (" + "name VARCHAR(255)," + "price INT)");

            System.out.println("Created table t_event");
        } catch (DataAccessException e) {
            Throwable causeException = e.getCause();
            if (causeException instanceof SQLException) {
                SQLException sqlException = (SQLException) causeException;
                if (sqlException.getSQLState().equals(SQL_ERROR_STATE_TABLE_EXISTS)) {
                    System.out.println("t_event table already exists");
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

    public void add(Event object) {
        jdbcTemplate.update("INSERT INTO t_event (name, price) VALUES (?,?)", object.getName(),
                object.getBasePrice());
        System.out.println("Saved to DB Event with name " + object.getName());
    }

    public void remove(Event object) {
        events.remove(object);
    }

    public Collection<Event> getAll() {
        Collection<Event> events = jdbcTemplate.query("select * from t_event", (rs, rowNum) -> {
            Event event = new Event();
            event.setName(rs.getString("name"));
            event.setBasePrice(rs.getDouble("price"));
            return event;
        });
        return events;
    }

    public static void setEvents(Set<Event> events) {
        EventDao.events = events;
    }
}
