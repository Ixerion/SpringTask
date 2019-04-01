package ua.epam.spring.hometask.service.impl;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountStrategy;

import java.time.LocalDateTime;

@Component
public class DiscountStrategyBirthday implements DiscountStrategy {

    private static final byte BIRTHDAY_DISCOUNT = 5;

    @Override
    public byte getDiscountStrategy(User user, LocalDateTime date, long numberOfTickets) {
        int userBirthday = user.getBirthday().getDayOfYear();
        int plannedDay = date.getDayOfYear();
        if (plannedDay - userBirthday >= 0 && plannedDay - userBirthday < 5) {
            return BIRTHDAY_DISCOUNT;
        }
        return 100;
    }
}
