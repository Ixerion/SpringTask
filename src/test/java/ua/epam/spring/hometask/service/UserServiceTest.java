package ua.epam.spring.hometask.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.config.AppConfiguration;
import ua.epam.spring.hometask.domain.User;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfiguration.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void userServiceSaveAlreadyExistedUserTest() {
        List<User> users = new ArrayList<>(userService.getAll());
        User user = users.get(0);
        userService.save(user);
        List<User> updatedUsers = new ArrayList<>(userService.getAll());
        Assert.assertEquals("User should be unique", users.size(), updatedUsers.size());
    }

    @Test
    public void userServiceSaveUserTest() {
        User user = new User();
        userService.save(user);
        Assert.assertTrue("Can't register user", userService.getAll().contains(user));
    }

    @Test
    public void userServiceRemoveUserTest() {
        List<User> users = new ArrayList<>(userService.getAll());
        userService.remove(users.get(0));
        Assert.assertTrue("Can't remove user", userService.getAll().isEmpty());
    }

}
