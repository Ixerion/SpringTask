package ua.epam.spring.hometask.config;

import org.omg.PortableInterceptor.LOCATION_FORWARD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountStrategy;
import ua.epam.spring.hometask.service.impl.DiscountStrategyBirthday;
import ua.epam.spring.hometask.service.impl.DiscountStrategyTenthTicket;

import java.time.LocalDate;
import java.util.*;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("ua.epam.spring")
@PropertySource("classpath:auditoriums.properties")
public class AppConfiguration {

    @Autowired
    private DiscountStrategyBirthday discountStrategyBirthday;
    @Autowired
    private DiscountStrategyTenthTicket discountStrategyTenthTicket;
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
        Auditorium smallAuditorum = new Auditorium();
        smallAuditorum.setName(smallAuditoriumName);
        smallAuditorum.setNumberOfSeats(smallAuditoriumSeats);
        smallAuditorum.setVipSeats(smallAuditoriumVip);
        return smallAuditorum;
    }

    @Bean
    public Auditorium bigAuditorium() {
        Auditorium bigAuditorum = new Auditorium();
        bigAuditorum.setName(bigAuditoriumName);
        bigAuditorum.setNumberOfSeats(bigAuditoriumSeats);
        bigAuditorum.setVipSeats(bigAuditoriumVip);
        return bigAuditorum;
    }

    @Bean
    public Set<Auditorium> auditoriums() {
        /*Auditorium smallAuditorum = new Auditorium();
        smallAuditorum.setName(smallAuditoriumName);
        smallAuditorum.setNumberOfSeats(smallAuditoriumSeats);
        smallAuditorum.setVipSeats(smallAuditoriumVip);*/
        /*Auditorium bigAuditorum = new Auditorium();
        bigAuditorum.setName(bigAuditoriumName);
        bigAuditorum.setNumberOfSeats(bigAuditoriumSeats);
        bigAuditorum.setVipSeats(bigAuditoriumVip);*/
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
        /*User existedUser = new User();
        existedUser.setId(1L);
        existedUser.setFirstName("Ivan");
        existedUser.setEmail("ivanov@gmail.com");
        existedUser.setBirthday(LocalDate.of(2003, 10, 10));*/
        users.put(1L, existedUser());
        return users;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


}
