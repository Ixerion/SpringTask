package ua.epam.spring.hometask.dao;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class UserDao {

    @Resource(name="registeredUsers")
    private Map<Long, User> registeredUsers = new HashMap<>();

    public User get(Long id) {
        return registeredUsers.get(id);
    }

    public void add(Long id, User user) {
        registeredUsers.put(id, user);
    }

    public void remove(Long id) {
        registeredUsers.remove(id);
    }

    public Collection<User> getAllUsers() {
        return registeredUsers.values();
    }

    public Set<Map.Entry<Long, User>> getEntries() {
        return registeredUsers.entrySet();
    }

    public void setRegisteredUsers(Map<Long, User> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }
}
