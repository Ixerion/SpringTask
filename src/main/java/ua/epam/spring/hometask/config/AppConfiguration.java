package ua.epam.spring.hometask.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountStrategy;

import java.time.LocalDate;
import java.util.*;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("ua.epam.spring")
@PropertySource("classpath:auditoriums.properties")
public class AppConfiguration {

    @Autowired
    private DiscountStrategy discountStrategyBirthday;
    @Autowired
    private DiscountStrategy discountStrategyTenthTicket;
    @Value("${auditorum.small.name}")
    private String smallAuditoriumName;
    @Value("${auditorum.small.seats}")
    private Integer smallAuditoriumSeats;
    @Value("#{'${auditorum.small.vip}'.split(',')}")
    private Set<Long> smallAuditoriumVip;
    @Value("${auditorum.big.name}")
    private String bigAuditoriumName;
    @Value("${auditorum.big.seats}")
    private Integer bigAuditoriumSeats;
    @Value("#{'${auditorum.big.vip}'.split(',')}")
    private Set<Long> bigAuditoriumVip;

    @Bean
    public List<DiscountStrategy> discountStrategies() {
        List<DiscountStrategy> discountStrategies = new ArrayList<>();
        discountStrategies.add(discountStrategyBirthday);
        discountStrategies.add(discountStrategyTenthTicket);
        return discountStrategies;
    }

    @Bean
    public Auditorium smallAuditorium() {
        Auditorium smallAuditorium = new Auditorium();
        smallAuditorium.setName(smallAuditoriumName);
        smallAuditorium.setNumberOfSeats(smallAuditoriumSeats);
        smallAuditorium.setVipSeats(smallAuditoriumVip);
        return smallAuditorium;
    }

    @Bean
    public Auditorium bigAuditorium() {
        Auditorium bigAuditorium = new Auditorium();
        bigAuditorium.setName(bigAuditoriumName);
        bigAuditorium.setNumberOfSeats(bigAuditoriumSeats);
        bigAuditorium.setVipSeats(bigAuditoriumVip);
        return bigAuditorium;
    }

    @Bean
    public Set<Auditorium> auditoriums() {
        Set<Auditorium> auditoriums = new HashSet<>();
        auditoriums.add(smallAuditorium());
        auditoriums.add(bigAuditorium());
        return auditoriums;
    }

    @Bean
    public User existedUser() {
        User existedUser = new User();
        existedUser.setId(1L);
        existedUser.setFirstName("Ivan");
        existedUser.setEmail("ivanov@gmail.com");
        existedUser.setBirthday(LocalDate.of(2003, 10, 10));
        return existedUser;
    }

    @Bean
    public Map<Long, User> registeredUsers() {
        Map<Long, User> users = new HashMap<>();
        users.put(1L, existedUser());
        return users;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


}
