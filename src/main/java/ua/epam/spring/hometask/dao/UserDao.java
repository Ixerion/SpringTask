package ua.epam.spring.hometask.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Component
public class UserDao {

    private static final String SQL_ERROR_STATE_SCHEMA_EXISTS = "X0Y68";
    private static final String SQL_ERROR_STATE_TABLE_EXISTS = "X0Y32";

    @Resource(name="registeredUsers")
    private Map<Long, User> registeredUsers;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Value("${jdbc.username}")
    private String schema;

    @PostConstruct
    public void init() {
        createDBSchema();
        createTableIfNotExists();
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

    private void createTableIfNotExists() {
        try {
            jdbcTemplate.update("CREATE TABLE t_user (" + "id BIGINT NOT NULL PRIMARY KEY," + "name VARCHAR(255),"
                    + "email VARCHAR(255)" + ")");

            System.out.println("Created table t_user");
        } catch (DataAccessException e) {
            Throwable causeException = e.getCause();
            if (causeException instanceof SQLException) {
                SQLException sqlException = (SQLException) causeException;
                if (sqlException.getSQLState().equals(SQL_ERROR_STATE_TABLE_EXISTS)) {
                    System.out.println("t_user table already exists!");
                } else {
                    throw e;
                }
            } else {
                throw e;
            }
        }
    }

    public User get(Long id) {
        return registeredUsers.get(id);
    }

    public void add(Long id, User user) {
        jdbcTemplate.update("INSERT INTO t_user (id, name, email) VALUES (?,?,?)", id, user.getFirstName(),
                user.getEmail());
        System.out.println("Saved to DB User with name " + user.getFirstName());
    }

    public void remove(Long id) {
        registeredUsers.remove(id);
    }

    public Collection<User> getAllUsers() {
        Collection<User> users = jdbcTemplate.query("select * from t_user", (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setFirstName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            return user;
        });
        return users;
    }

    public Set<Map.Entry<Long, User>> getEntries() {
        return registeredUsers.entrySet();
    }

    public void setRegisteredUsers(Map<Long, User> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }
}
