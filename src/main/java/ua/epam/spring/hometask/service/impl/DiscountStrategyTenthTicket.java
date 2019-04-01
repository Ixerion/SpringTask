package ua.epam.spring.hometask.service.impl;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountStrategy;

import java.time.LocalDateTime;

@Component
public class DiscountStrategyTenthTicket implements DiscountStrategy {

    private static final byte TENTH_TICKET_DISCOUNT = 50;

    @Override
    public byte getDiscountStrategy(User user, LocalDateTime date, long numberOfTickets) {
        long discountTicketAmount = 0;
        while (numberOfTickets > 0) {
            numberOfTickets -= 10;
            discountTicketAmount++;
        }
        long totalPlaces = numberOfTickets - (discountTicketAmount / 2);

        return numberOfTickets % 10 == 0 ? 0 : TENTH_TICKET_DISCOUNT;

    }
}
