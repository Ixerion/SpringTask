package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return userDao.getEntries().stream()
                .filter(entry -> entry.getValue().getEmail().equals(email))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(null);
    }

    @Override
    public User save(@Nonnull User object) {
        userDao.add(object.getId(), object);
        return object;
    }

    @Override
    public void remove(@Nonnull User object) {
        userDao.remove(object.getId());
    }

    @Override
    public User getById(@Nonnull Long id) {
        return userDao.get(id);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return userDao.getAllUsers();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
