package ua.epam.spring.hometask.service.impl;

import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountStrategy;

import java.time.LocalDateTime;

public class DiscountStrategyTenthTicket implements DiscountStrategy {

    private static final int TENTH_TICKET_DISCOUNT = 50;

    @Override
    public int getDiscountStrategy(User user, LocalDateTime date, long numberOfTickets) {
        return numberOfTickets % 10 == 0 ? 0 : TENTH_TICKET_DISCOUNT;

    }
}
