package ua.epam.spring.hometask.service.impl;

import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountStrategy;

import java.time.LocalDateTime;

public class DiscountStrategyBirthday implements DiscountStrategy {

    private static final int BIRTHDAY_DISCOUNT = 5;

    @Override
    public int getDiscountStrategy(User user, LocalDateTime date, long numberOfTickets) {
        int userBirthday = user.getBirthday().getDayOfYear();
        int plannedDay = date.getDayOfYear();
        if (plannedDay - userBirthday >= 0 && plannedDay - userBirthday < 5) {
            return BIRTHDAY_DISCOUNT;
        }
        return 100;
    }
}
